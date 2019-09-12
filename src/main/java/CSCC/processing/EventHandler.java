package CSCC.processing;

import CSCC.data.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EventHandler {

    private Logger logger = LoggerFactory.getLogger(EventHandler.class);

    public void process(Event event) {
        try {
            Connection c = DriverManager.getConnection("jdbc:hsqldb:file:logdb", "sa", "");
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
    }
}
