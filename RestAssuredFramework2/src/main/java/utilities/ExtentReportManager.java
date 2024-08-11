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
		//System.getProperty("user.dir") + "/src/test/resources/TestReport/extent.html
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setDocumentTitle("NatWest Test Report");
		reporter.config().setReportName("NatWest Automation Test Report");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Browser", "Chrome");
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

/*	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		
		if(extent == null) {
			
			ExtentSparkReporter spark=new ExtentSparkReporter("extent.html");
			extent=new ExtentReports();
			extent.attachReporter(spark);
			
			//create a new test
			spark.config().setDocumentTitle("API Test automation");
			spark.config().setReportName("Test Execution Report");
			spark.config().setTheme(Theme.DARK);
			
			extent.setSystemInfo("Automation Tester", "Sugirtha");
			extent.setSystemInfo("Organization", "NatWest");
			extent.setSystemInfo("BrowserName", "Chrome");			
			
		}
		
		return extent;
	}}*/
	


