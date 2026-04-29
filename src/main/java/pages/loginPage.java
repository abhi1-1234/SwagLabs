package pages;

import base.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.time.Duration;

import static base.Browser.*;

/**
 * Created by Ajagdale on 26-04-2026
 */

public class loginPage {

    private static final Logger log = LoggerFactory.getLogger(loginPage.class);
    @FindBy(xpath = "//div[@class='login_logo']/self::div[text()='Swag Labs']")
    private static WebElement loginPageLogo;

    @FindBy(id = "user-name")
    private static WebElement userName;

    @FindBy(id = "password")
    private static WebElement password;

    @FindBy(id = "login-button")
    private static WebElement loginButton;

    private static WebDriver driver;
    private static WebDriverWait driverWait;
    private static homePage home;
    private Browser browser;

    public loginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofMinutes(2));
    }

    private static void setUserName(String name){
        driverWait.until(ExpectedConditions.visibilityOf(userName));
        userName.sendKeys(name);
    }

    private static void setPassword(String value){
        driverWait.until(ExpectedConditions.visibilityOf(password));
        password.sendKeys(value);
    }

    private static void clickLoginButton(){
        driverWait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
    }

    public static homePage loginIntoSwagLabs(String browser, String user, String pass) throws AWTException {
        if (browser.equalsIgnoreCase("chrome")){
            logInfo("Launch Chrome browser");
            driver = launchChrome();
            logInfo("Browser launched successfully");

            logInfo("Enter URL in browser and maximize window");
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();
            logInfo("URL entered successfully and window maximized");
        }
        if (browser.equalsIgnoreCase("firefox")){
            logInfo("Launch Firefox browser");
            driver = launchFirefox();
            logInfo("Browser launched successfully");

            logInfo("Enter URL in browser and maximize window");
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();
            logInfo("URL entered successfully and window maximized");
        }
        if (browser.equalsIgnoreCase("msedge")){
            logInfo("Launch Microsoft Edge browser");
            driver = launchMsEdge();
            logInfo("Browser launched successfully");

            logInfo("Enter URL in browser and maximize window");
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();
            logInfo("URL entered successfully and window maximized");
        }


        logInfo("Validate that Login page displayed successfully");
        driverWait.until(ExpectedConditions.visibilityOf(loginPageLogo));
        logInfo("Validation passed successfully");

        logInfo("Enter Username as [ "+user+" ] and Password as [ "+pass+" ]");
        setUserName(user);
        setPassword(pass);
        logInfo("Username and Password entered successfully");

        logInfo("Click on 'Login' button");
        clickLoginButton();
        logInfo("Clicked on login button");

        home = new homePage(driver);
        return home;
    }

    public static void teardown(){ driver.quit(); }

    public static void logInfo(String message){ log.info(message); }

}
