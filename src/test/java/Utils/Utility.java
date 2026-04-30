package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.v85.runtime.model.StackTrace;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;
import pages.loginPage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ajagdale on 26-04-2026
 */

public class Utility extends loginPage {

    public Utility(WebDriver driver) {
        super(driver);
    }

    public static void log(String message) {
        String time = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Reporter.log(time + " - " + message, true);
    }

    public static void TakeScreenShotOf(String className, String TestID) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;

        File src = ts.getScreenshotAs(OutputType.FILE);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-mm-yyyy hh_mm_ss");

        LocalDateTime d = LocalDateTime.now();

        File dest = new File("test-output\\Failed_Test_Screenshots \\"+browserName+"\\"+className+"\\"+ TestID + "\\" + dtf.format(d) + ".jpeg");
        if (!dest.exists()){
            dest.mkdirs();
        }
        try {
            FileHandler.copy(src, dest);
        } catch (Exception e) {
            System.out.println("Screenshot captured successfully");
        }

    }
}
