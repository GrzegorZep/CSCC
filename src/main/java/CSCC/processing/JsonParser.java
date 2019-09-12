package CSCC.processing;

import CSCC.data.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonParser {

    private Logger logger = LoggerFactory.getLogger(JsonParser.class);
    private ObjectMapper mapper = new ObjectMapper();

    public Event retrieve(String eventInJson) {
        Event event = new Event();
        try {
            event = mapper.readValue(eventInJson, Event.class);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return event;
    }
}
