package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	
	public ExtentReports extent;//core object to manage the entire report
	public ExtentTest extenttest;//represent a single test and its logs
	public ExtentSparkReporter reporter;//configuring the html report
	
	public void onStart(ITestContext context) {
		reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/src/test/resources/TestReport/extent.html");
		
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setDocumentTitle("NatWest Test Report");
		reporter.config().setReportName("NatWest Automation Test Report");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("OS", "Ubuntu");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Project Name", "Dummy-API Test Automation");
		extent.setSystemInfo("Test Framework", "TestNG");
		extent.setSystemInfo("API Testing Tool", "RestAssured");
		extent.setSystemInfo("Programming Language", "Java");
	}
	public void onTestSuccess(ITestResult result) {
		extenttest = extent.createTest(result.getMethod().getMethodName());
		extenttest.createNode(result.getName());
		extenttest.log(Status.PASS, "Test passed");
	}
	public void onTestFailure(ITestResult result) {
		extenttest = extent.createTest(result.getMethod().getMethodName());
		extenttest.createNode(result.getName());
		extenttest.log(Status.FAIL, "Test failed");
	}
	public void onTestSkipped(ITestResult result) {
		extenttest = extent.createTest(result.getMethod().getMethodName());
		extenttest.createNode(result.getName());
		extenttest.log(Status.SKIP, "Test skipped");
	}
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}


	


