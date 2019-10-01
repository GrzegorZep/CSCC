package CSCC.persistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class HSQLDBController {

    private static final Logger logger = LoggerFactory.getLogger(HSQLDBController.class);
    private String url = "jdbc:hsqldb:file:logfiledb";
    private String user = "SA";
    private String password = "";
    private Connection connection;
    private Statement statement;

    public HSQLDBController() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            logger.debug("Connection to db has been established");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void updateTable(String sql) throws SQLException {
        statement.executeUpdate(sql);
    }

    public ResultSet queryTable(String sql) throws SQLException {
        ResultSet rs = statement.executeQuery(sql);
        return rs;
    }


    public void shutdown() throws SQLException {
        statement.execute("SHUTDOWN");
        connection.close();
        logger.debug("Db has been shutdown successfully");
    }

}
