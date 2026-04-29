package pages;

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

    protected static WebDriver driver;
    private static WebDriverWait driverWait;
    private static homePage home;
    protected static String browserName;

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
        browserName = browser;
        if (browserName.equalsIgnoreCase("chrome")){
            driver = launchChrome();
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();
        }
        if (browserName.equalsIgnoreCase("firefox")){
            driver = launchFirefox();
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();
        }
        if (browserName.equalsIgnoreCase("msedge")){
            driver = launchMsEdge();
            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();
        }
        driverWait.until(ExpectedConditions.visibilityOf(loginPageLogo));
        setUserName(user);
        setPassword(pass);
        clickLoginButton();
        home = new homePage(driver);
        return home;
    }

    public static void teardown(){ driver.quit(); }

}
