package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.loginPage;

/**
 * Created by Ajagdale on 26-04-2026
 */

public class Browser extends loginPage{

    public static loginPage page;

    public Browser(WebDriver driver) {
        super(driver);
    }

    public static WebDriver launchChrome(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\lenovo\\OneDrive\\Documents\\Chromedriver\\ChromeDrivers\\chromedriver147.exe");
//        ChromeOptions options = new ChromeOptions();
//
//        // 🔥 Disable password manager + leak detection
//        Map<String, Object> prefs = new HashMap<>();
//        prefs.put("credentials_enable_service", false);
//        prefs.put("profile.password_manager_enabled", false);
//        prefs.put("profile.password_manager_leak_detection", false);
//
//        options.setExperimentalOption("prefs", prefs);
//
//        // 🔥 Must-have flags (Chrome 147 ke liye)
//        options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding");
//        options.addArguments("--incognito");
//
//        // 🔥 VERY IMPORTANT → fresh profile
//        options.addArguments("user-data-dir=C:\\temp\\fresh-profile");
//
//        WebDriver driver = new ChromeDriver(options);
//        page = new loginPage(driver);
//        return driver;
        WebDriver driver = new ChromeDriver();
        page = new loginPage(driver);
        return driver;
    }

    public static WebDriver launchFirefox(){
        System.setProperty("webdriver.gecko.driver","C:\\Users\\lenovo\\OneDrive\\Documents\\Chromedriver\\FirefoxDrivers\\geckodriver127.exe");
        WebDriver driver = new FirefoxDriver();
        page = new loginPage(driver);
        return driver;
    }

    public static WebDriver launchMsEdge(){
        System.setProperty("webdriver.edge.driver","C:\\Users\\lenovo\\OneDrive\\Documents\\Chromedriver\\msEdgeDrivers\\msedgedriver147.exe");
        WebDriver driver = new EdgeDriver();
        page = new loginPage(driver);
        return driver;
    }
}
