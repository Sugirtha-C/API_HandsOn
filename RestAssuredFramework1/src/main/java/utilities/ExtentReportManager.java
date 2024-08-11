package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	private static ExtentReports extent;
	
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
	}
	

}
