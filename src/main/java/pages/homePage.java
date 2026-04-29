package pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;

/**
 * Created by Ajagdale on 26-04-2026
 */

public class homePage {

    @FindBy(xpath = "//div[@class='app_logo']/self::div[contains(.,'Swag Labs')]")
    private WebElement homePageLogo;

    @FindBy(xpath = "//div[@class='shopping_cart_container']//a[@class='shopping_cart_link']//span[@class='shopping_cart_badge']")
    private WebElement shoppingCartBadge;

    WebDriver driver;
    YourCartPage yourCartPage;

    public homePage(WebDriver driver) throws AWTException {
        PageFactory.initElements(driver,this);
        this.driver = driver;
        WebDriverWait driverWait = new WebDriverWait(this.driver, Duration.ofMinutes(3));
        driverWait.until(ExpectedConditions.visibilityOf(homePageLogo));
    }

    public void selectProduct(String productName, String price){
        WebElement element = driver.findElement(By.xpath("//div[@class='inventory_item_description']//div[@class='inventory_item_label']/" +
                "self::div[contains(.,'"+productName+"')]/following-sibling::div[@class='pricebar']/self::div[contains(.,'"+price+"')][contains(.,'$')]//button[contains(.,'Add to cart')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public String getBadgeCount(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", shoppingCartBadge);
        return shoppingCartBadge.getText(); }

    public YourCartPage clickOnCartIcon(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", shoppingCartBadge);
        shoppingCartBadge.click();
        yourCartPage = new YourCartPage(driver);
        return yourCartPage;
    }
}
