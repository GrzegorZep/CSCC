package CSCC.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class HSQLDBManager {

    private static final Logger logger = LoggerFactory.getLogger(HSQLDBManager.class);
    private String url = "jdbc:hsqldb:file:logfiledb";
    private String user = "SA";
    private String password = "";
    private Connection connection;
    private Statement statement;

    public HSQLDBManager() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            createEventsTable();
            logger.debug("Connection to db has been established");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void shutdown() throws SQLException {
        statement.execute("SHUTDOWN");
        connection.close();
    }

    private void createEventsTable() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS events_table (" +
                "id VARCHAR(20) NOT NULL PRIMARY KEY, " +
                "type VARCHAR(10), " +
                "host INTEGER, " +
                "time_started VARCHAR(10), " +
                "time_finished VARCHAR(10), " +
                "alert BOOLEAN)");
    }
}
