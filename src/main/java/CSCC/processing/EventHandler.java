package CSCC.processing;

import CSCC.data.Event;
import CSCC.persistance.HSQLDBManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class EventHandler {

    private static final EventHandler INSTANCE = new EventHandler();
    private static final Logger logger = LoggerFactory.getLogger(EventHandler.class);
    private ObjectMapper mapper;
    private HSQLDBManager hsqldbManager;
    private Event event;

    private EventHandler() {
        mapper = new ObjectMapper();
        event = new Event();
        hsqldbManager = new HSQLDBManager();
    }

    public static EventHandler getInstance() {
        return INSTANCE;
    }

    public void createEvent(String json) {
        try {
            event = mapper.readValue(json, Event.class);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void insertEvent() {
        try {
            hsqldbManager.shutdown();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void commitEventsAndShutdown() {
        try {
            hsqldbManager.shutdown();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public Event getEvent() {
        return event;
    }
}
