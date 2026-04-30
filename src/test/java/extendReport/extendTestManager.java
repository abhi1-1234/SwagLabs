package extendReport;

import com.aventstack.extentreports.ExtentTest;

public class extendTestManager {

    private static ThreadLocal<ExtentTest> parent = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> browserNode = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> testNode = new ThreadLocal<>();

    public static void setParent(ExtentTest test) {
        parent.set(test);
    }

    public static ExtentTest getParent() {
        return parent.get();
    }

    public static void setBrowserNode(ExtentTest test) {
        browserNode.set(test);
    }

    public static ExtentTest getBrowserNode() {
        return browserNode.get();
    }

    public static void setTestNode(ExtentTest test) {
        testNode.set(test);
    }

    public static ExtentTest getTest() {
        return testNode.get();
    }

}
