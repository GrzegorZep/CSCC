package CSCC.processing;

import CSCC.data.Event;
import CSCC.persistance.HSQLDBController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

public class EventHandler {

    private static final Logger logger = LoggerFactory.getLogger(EventHandler.class);
    private static EventHandler INSTANCE = new EventHandler();
    private final int alertThreshold = 4;
    private ObjectMapper mapper;
    private HSQLDBController hsqldbController;
    private Event event;
    private ResultSet resultSet;

    private EventHandler() {
        mapper = new ObjectMapper();
        event = new Event();
        hsqldbController = new HSQLDBController();
    }

    public static EventHandler getInstance() {
        return INSTANCE;
    }

    public void createEventFromJson(String json) {
        try {
            event = mapper.readValue(json, Event.class);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void createEventTable() {
        try {
            hsqldbController.updateTable("CREATE TABLE IF NOT EXISTS events_table (" +
                    "id VARCHAR(256) NOT NULL PRIMARY KEY, " +
                    "type VARCHAR(256), " +
                    "host INTEGER, " +
                    "time_started VARCHAR(256), " +
                    "time_finished VARCHAR(256), " +
                    "alert BOOLEAN)");
            logger.debug("Table \"events\" created");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void insertCurrentEventIntoDB() {
        try {
            if (isEventInHSQLDB())
                hsqldbController.updateTable(updateExistingEventSQL());
            else
                hsqldbController.updateTable(insertNewEventSQL());
            logger.debug("Event stored in db");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public void commitChangesAndShutdownDB() {
        try {
            hsqldbController.shutdown();
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }

    public Event getEvent() {
        return event;
    }

    private boolean isEventInHSQLDB() throws SQLException {
        String sql = "SELECT * FROM events_table WHERE id='" + event.getId() + '\'';
        resultSet = hsqldbController.queryTable(sql);
        return resultSet.next();
    }

    private String insertNewEventSQL() {
        String timeStarted = "";
        String timeFinished = "";
        if (event.getState().equals("STARTED"))
            timeStarted = event.getTimestamp();
        else
            timeFinished = event.getTimestamp();
        return "INSERT INTO events_table(id,type,host,time_started,time_finished,alert) " +
                "VALUES(" +
                '\'' + event.getId() + "','" +
                event.getType() + "'," +
                event.getHost() + ",'" +
                timeStarted + "','" +
                timeFinished + "'," +
                "FALSE)";
    }

    private String updateExistingEventSQL() throws SQLException {
        String alert = isDurationMoreThanThreshold();
        String timeStarted = "";
        String timeFinished = "";
        if (event.getState().equals("STARTED")) {
            timeStarted = event.getTimestamp();
            timeFinished = resultSet.getString("time_finished");
        } else {
            timeFinished = event.getTimestamp();
            timeStarted = resultSet.getString("time_started");
        }
        return "UPDATE events_table " +
                "SET type='" + event.getType() + "'," +
                "host=" + event.getHost() + ',' +
                "time_started='" + timeStarted + "'," +
                "time_finished='" + timeFinished + "'," +
                "alert=" + alert +
                " WHERE id='" + event.getId() + '\'';
    }

    private String isDurationMoreThanThreshold() throws SQLException {
        try {
            Instant timestamp = Instant.ofEpochMilli(Long.parseLong(event.getTimestamp()));
            String duration = resultSet.getString("time_started");
            if (duration.equals(""))
                duration = resultSet.getString("time_finished");
            Instant timeFromDB = Instant.ofEpochMilli(Long.parseLong(duration));
            if (Duration.between(timeFromDB,timestamp).toNanos() > (long) alertThreshold)
                return "TRUE";
        } catch (NumberFormatException ex) {
            logger.error(ex.getMessage());
        }
        return "FALSE";
    }
}
