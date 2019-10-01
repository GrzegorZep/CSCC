/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package CSCC;

import CSCC.processing.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App {

    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private static String logFilePath = "logfile.txt";

    public static void main(String[] args) {
        logger.info("Reading user input.");
        readFilePath();
        logger.info("Opening logfile.");
        try (FileInputStream fis = new FileInputStream(logFilePath)) {
            Scanner sc = new Scanner(fis);
            logger.info("Processing logfile entries.");
            EventHandler eventHandler = EventHandler.getInstance();
            eventHandler.createEventTable();
            while (sc.hasNextLine()) {
                String fileLine = sc.nextLine();
                logger.debug("Line read from file: " + fileLine);
                eventHandler.createEventFromJson(fileLine);
                logger.debug("Event after parsing: " + eventHandler.getEvent().toString());
                eventHandler.insertCurrentEventIntoDB();
            }
            eventHandler.commitChangesAndShutdownDB();
        } catch (IOException ioe) {
            logger.error(ioe.getMessage());
        }
        logger.info("Processing has been finished.");
    }

    private static void readFilePath() {
        String filePath = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            logger.info("Please provide logfile.txt file path or press Enter for default one");
            filePath = reader.readLine();
            logger.debug("File path read: " + filePath);
            if (!filePath.isEmpty())
                logFilePath = filePath;
            else
                logger.info("Processing project's default logfile.");
        } catch (IOException ioe) {
            logger.error(ioe.getMessage());
        }
    }
}
