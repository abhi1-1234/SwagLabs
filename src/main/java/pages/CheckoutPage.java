package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Created by Ajagdale on 26-04-2026
 */

public class CheckoutPage {

    @FindBy(xpath = "//span[@class='title']/self::span[contains(.,'Checkout: Your Information')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@class='checkout_info']//input[@placeholder='First Name']")
    private WebElement firstName;

    @FindBy(xpath = "//div[@class='checkout_info']//input[@placeholder='Last Name']")
    private WebElement lastName;

    @FindBy(xpath = "//div[@class='checkout_info']//input[@placeholder='Zip/Postal Code']")
    private WebElement postalCode;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement continueButton;

    WebDriver driver;
    WebDriverWait driverWait;

    public CheckoutPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        driverWait = new WebDriverWait(this.driver, Duration.ofMinutes(3));
        driverWait.until(ExpectedConditions.visibilityOf(pageTitle));
    }

    /**
     * Created by Ajagdale on 26-04-2026
     * This method is used to set 'First Name' field
     * @param fName
     */
    public void setFirstName(String fName){
        driverWait.until(ExpectedConditions.visibilityOf(firstName));
        firstName.sendKeys(fName);
    }

    /**
     * Created by Ajagdale on 26-04-2026
     * This method is use to set 'Last Name' field
     * @param lName
     */
    public void setLastName(String lName){
        driverWait.until(ExpectedConditions.visibilityOf(lastName));
        lastName.sendKeys(lName);
    }

    /**
     * Created by Ajagdale on 26-04-2026
     * This method is use to set 'Postal Code' field
     * @param code
     */
    public void setPostalCode(String code){
        driverWait.until(ExpectedConditions.visibilityOf(postalCode));
        postalCode.sendKeys(code);
    }

    /**
     *  Created by Ajagdale on 26-04-2026
     *  This method is used to click on 'Continue' button
     */
    public void clickOnContinueButton(){
        driverWait.until(ExpectedConditions.visibilityOf(continueButton));
        continueButton.click();
    }


}
