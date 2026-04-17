package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {
    String repName;
    public ExtentReports extent;
    public ExtentSparkReporter sparkReporter;
    public ExtentTest test;

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        String reportDir = System.getProperty("user.dir") + File.separator + "reports";
        String reportPath = reportDir + File.separator + repName;
        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("RestAssured-Automation");
        sparkReporter.config().setReportName("Pet Store Users API");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Pet Store Users API");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        String os = testContext.getCurrentXmlTest().getParameter("OS");
        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Operating System", os);
        extent.setSystemInfo("Browser", browser);
        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Included Groups", includedGroups.toString());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result){
        test=extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.createNode(result.getName());
        test.log(Status.PASS, " Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        test=extent.createTest(result.getName());
        test.createNode(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL,result.getName()+" Failed");
        test.log(Status.FAIL,result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result){
        test=extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP,result.getName()+" Skipped");
        test.log(Status.SKIP,result.getThrowable().getMessage());
    }

    @Override
    public void onFinish(ITestContext testContext){
        if (extent != null) {
            extent.flush();
        }

    }
}
