package testPackage;

import Utils.Utility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import dataFile.test1Data;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CheckoutPage;
import pages.YourCartPage;
import pages.homePage;
import pages.loginPage.*;
import java.awt.*;
import java.io.IOException;

import static Utils.Utility.log;
import static pages.loginPage.*;

/**
 * Created by Ajagdale on 26-04-2026
 *
 */

public class test1 implements test1Data {

    private homePage home;
    private YourCartPage yourCartPage;
    private CheckoutPage checkoutPage;
    static ExtentSparkReporter reporter;

    @Parameters("browser")
    @BeforeTest
    public void launchBrowser(@Optional("Chrome") String browser) throws AWTException {

        reporter = new ExtentSparkReporter("test-output/ExtendReport/Extent.html");
        ExtentReports extend = new ExtentReports();
        extend.attachReporter(reporter);

        if (browser.equalsIgnoreCase(chrome)){
            log("Log into Swag Lags with Username [ "+userName+" ] and Password [ "+password+" ]");
            home = loginIntoSwagLabs(chrome, userName, password);
            log("User logged in successfully");
        }
        if ((browser.equalsIgnoreCase(firefox))){
            log("Log into Swag Lags with Username [ "+userName+" ] and Password [ "+password+" ]");
            home = loginIntoSwagLabs(firefox, userName, password);
            log("User logged in successfully");
        }
        if ((browser.equalsIgnoreCase(msEdge))){
            log("Log into Swag Lags with Username [ "+userName+" ] and Password [ "+password+" ]");
            home = loginIntoSwagLabs(msEdge, userName, password);
            log("User logged in successfully");
        }
    }

    @Test(priority = 1, description = "Step1")
    public void Step1(){

        log("Select Product [ "+product1+" ] with price [ "+priceOfProduct1+" ] and click on 'Add to cart' button");
        home.selectProduct(product1,priceOfProduct1);
        log("Product [ "+product1+" ] is selected successfully and clicked on 'Add to cart' button");

        log("Select Product [ "+product2+" ] with price [ "+priceOfProduct2+" ] and click on 'Add to cart' button");
        home.selectProduct(product2,priceOfProduct2);
        log("Product [ "+product2+" ] is selected successfully and clicked on 'Add to cart' button");
    }

    @Test(priority = 2, description = "Step2")
    public void Step2(){

        log("Validate that Cart badge count is displayed as [ "+count2+" ]");
        String count = home.getBadgeCount();
        Assert.assertEquals(count,count2,"Badge count of Cart icon is NOT displayed as [ "+count2+" ], expected that it should be displayed");
        log("Validation passed successfully");

        log("Click on 'Cart' icon");
        yourCartPage = home.clickOnCartIcon();
        log("Clicked on 'Cart' icon");
    }

    @Test(priority = 3, description = "Step3")
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

    @Test(priority = 4, description = "Step4")
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

    @AfterMethod
    public void TakeScreenshot_Of_Failed_Test_Methods(ITestResult result) throws IOException {
        if(ITestResult.FAILURE == result.getStatus())
        {
            Utility.TakeScreenShotOf("test1",result.getMethod().getDescription());
        }

    }

    @AfterTest(alwaysRun = true)
    public void closeBrowser(){

        teardown();
        System.gc();
    }
}
