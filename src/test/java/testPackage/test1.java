package testPackage;

import dataFile.test1Data;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CheckoutPage;
import pages.YourCartPage;
import pages.homePage;
import pages.loginPage.*;
import java.awt.*;
import static pages.loginPage.loginIntoSwagLabs;
import static pages.loginPage.teardown;
import static Utils.Utility.log;

/**
 * Created by Ajagdale on 26-04-2026
 *
 */

public class test1 implements test1Data {

    private homePage home;
    private YourCartPage yourCartPage;
    private CheckoutPage checkoutPage;

    @Parameters("browser")
    @BeforeTest
    public void launchBrowser(@Optional("Chrome") String browser) throws AWTException {
        if (browser.equalsIgnoreCase(chrome)){
            home = loginIntoSwagLabs(chrome, userName, password);
        }
        if ((browser.equalsIgnoreCase(firefox))){
            home = loginIntoSwagLabs(firefox, userName, password);
        }
        if ((browser.equalsIgnoreCase(msEdge))){
            home = loginIntoSwagLabs(msEdge, userName, password);
        }
    }

    @Test(priority = 1, description = "Select Any Two Products")
    public void Step1(){

        log("Select Product [ "+product1+" ] with price [ "+priceOfProduct1+" ] and click on 'Add to cart' button");
        home.selectProduct(product1,priceOfProduct1);
        log("Product [ "+product1+" ] is selected successfully and clicked on 'Add to cart' button");

        log("Select Product [ "+product2+" ] with price [ "+priceOfProduct2+" ] and click on 'Add to cart' button");
        home.selectProduct(product2,priceOfProduct2);
        log("Product [ "+product2+" ] is selected successfully and clicked on 'Add to cart' button");
    }

    @Test(priority = 2, description = "Validation of Cart Icon")
    public void Step2(){

        log("Validate that Cart badge count is displayed as [ "+count2+" ]");
        String count = home.getBadgeCount();
        Assert.assertEquals(count,count2,"Badge count of Cart icon is NOT displayed as [ "+count2+" ], expected that it should be displayed");
        log("Validation passed successfully");

        log("Click on 'Cart' icon");
        yourCartPage = home.clickOnCartIcon();
        log("Clicked on 'Cart' icon");
    }

    @Test(priority = 3, description = "Validate that added Products are visible on 'Your Cart' Page")
    public void Step3(){

        log("Validate that product [ "+product1+" ] is displayed with price [ "+priceOfProduct1+" ]");
        Assert.assertTrue(yourCartPage.isProductDisplayed(product1, priceOfProduct1),"Product [ "+product1+" ] is NOT displayed with price [ "+priceOfProduct1+" ], expected that it should be displayed");
        log("Validation passed successfully");

        log("Validate that product [ "+product2+" ] is displayed with price [ "+priceOfProduct2+" ]");
        Assert.assertTrue(yourCartPage.isProductDisplayed(product2, priceOfProduct2),"Product [ "+product2+" ] is NOT displayed with price [ "+priceOfProduct2+" ], expected that it should be displayed");
        log("Validation passed successfully");

        log("Click on 'Checkout' button");
        checkoutPage = yourCartPage.clickOnCheckoutButton();
        log("Clicked on 'Checkout' button");
    }

    @Test(priority = 4, description = "Enter Details on 'Checkout' page")
    public void Step4(){

        log("Enter First Name - [ "+fName+" ], Last Name [ "+lName+" ] and Postal Code - [ "+postalCode+" ]");
        checkoutPage.setFirstName(fName);
        checkoutPage.setLastName(lName);
        checkoutPage.setPostalCode(postalCode);
        log("All details are entered successfully");

        log("Click on 'Continue' button");
        checkoutPage.clickOnContinueButton();
        log("Clicked on 'Continue' button");
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowser(){

        teardown();
    }
}
