package br.com.girardon.tibia.utils;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadLogFileUtils {
    private static final String LOG_FILE_PATH = "src/main/resources/log/tibia_server_log.txt";
    private static final Logger logger = LoggerFactory.getLogger(ReadLogFileUtils.class);


    public static boolean isLogFileExists() {
        File file = new File(LOG_FILE_PATH);
        return file.exists();
    }

    public static String readLogFile() {
        StringBuilder content = new StringBuilder();

        if (isLogFileExists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            } catch (IOException e) {
                logger.info("Error reading log file.", e);
            }
        } else {
            logger.info("Log file does not exist.");
        }

        return content.toString();
    }
}
