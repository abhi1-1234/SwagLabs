package extendReport;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.*;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentTestNGIReporterListener implements IReporter {

    private static final String OUTPUT_FOLDER = "test-output/ExtendReport/";
    private static final String FILE_NAME = "Extent.html";

    private ExtentReports extent;

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        init();

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> results = suite.getResults();

            for (ISuiteResult r : results.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getPassedTests(), Status.PASS);
                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
            }
        }

        extent.flush(); // 🔥 report generate karega
    }

    private void init() {

        // 👉 Ye line automatically folder create karegi
        ExtentSparkReporter spark = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);

        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Project", "Swag Labs");
        extent.setSystemInfo("Tester", "Abhishek Jagdale");
    }

    private void buildTestNodes(IResultMap tests, Status status) {

        if (tests.size() > 0) {

            for (ITestResult result : tests.getAllResults()) {

                ExtentTest test = extent.createTest(result.getMethod().getMethodName());

                // Groups = categories
                for (String group : result.getMethod().getGroups()) {
                    test.assignCategory(group);
                }

                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase());
                }

                test.getModel().setStartTime(new Date(result.getStartMillis()));
                test.getModel().setEndTime(new Date(result.getEndMillis()));
            }
        }
    }
}
