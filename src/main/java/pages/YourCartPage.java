package pages;

import org.openqa.selenium.By;
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

public class YourCartPage {

    WebDriver driver;
    CheckoutPage checkoutPage;

    @FindBy(xpath = "//span[@class='title']/self::span[contains(.,'Your Cart')]")
    private WebElement pageLogo;

    @FindBy(xpath = "//button[@id='checkout']/self::button[contains(.,'Checkout')]")
    private WebElement checkout;

    public YourCartPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
        WebDriverWait driverWait = new WebDriverWait(this.driver, Duration.ofMinutes(3));
        driverWait.until(ExpectedConditions.visibilityOf(pageLogo));
    }

    public boolean isProductDisplayed(String productName, String productPrice){
        WebElement element = driver.findElement(By.xpath("//div[@class='cart_item_label']//a[contains(.,'"+productName+"')]/following-sibling::div[@class='item_pricebar']//" +
                "self::div[contains(.,'$')][contains(.,'"+productPrice+"')]"));
        return element.isDisplayed();
    }

    public CheckoutPage clickOnCheckoutButton(){
        checkout.click();
        checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }
}
