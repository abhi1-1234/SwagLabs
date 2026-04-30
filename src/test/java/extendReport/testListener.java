package extendReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class testListener implements ITestListener {

    ExtentReports extent = extendManager.getInstance();

    // Class level
    private static Map<String, ExtentTest> classMap = new HashMap<>();

    // Browser level (per class)
    private static Map<String, Map<String, ExtentTest>> browserMap = new HashMap<>();

    @Override
    public void onTestStart(ITestResult result) {

        String className = result.getTestClass().getName();
        String methodName = result.getMethod().getMethodName();
        String browser = result.getTestContext().getCurrentXmlTest().getParameter("browser");

        // 🔹 1. Class node (only once)
        if (!classMap.containsKey(className)) {
            classMap.put(className, extent.createTest(className));
        }
        ExtentTest parent = classMap.get(className);

        // 🔹 2. Browser node (only once per class)
        browserMap.putIfAbsent(className, new HashMap<>());
        Map<String, ExtentTest> innerMap = browserMap.get(className);

        if (!innerMap.containsKey(browser)) {
            innerMap.put(browser, parent.createNode(browser.toUpperCase()));
        }

        ExtentTest browserNode = innerMap.get(browser);

        // 🔹 3. Method node (always new)
        ExtentTest testNode = browserNode.createNode(methodName);

        extendTestManager.setTestNode(testNode);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extendTestManager.getTest().pass("Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extendTestManager.getTest().fail(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extendTestManager.getTest().skip(result.getThrowable());
    }
}

