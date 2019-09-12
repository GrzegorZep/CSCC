package CSCC.processing;

import CSCC.data.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class EventHandler {

    private static Logger logger = LoggerFactory.getLogger(EventHandler.class);

    public void process(Event event) {
        try (Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:logdb", "sa", "")) {
            String sql = "SELECT * FROM events WHERE id=" + event.getId();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}
