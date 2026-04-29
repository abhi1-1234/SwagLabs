package Utils;

import org.testng.Reporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ajagdale on 26-04-2026
 */

public class Utility {

    public static void log(String message) {
        String time = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Reporter.log(time + " - " + message, true);
    }
}
