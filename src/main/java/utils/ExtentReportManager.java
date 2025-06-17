package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	
	private static ExtentReports extentrep;
	private static ExtentTest testrep;
	
	public static String repPath;
	
	public static ExtentReports getReportInstance() {
		
		if(extentrep == null) {
			
			String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
			repPath = "reports/ExtentReport_"+timestamp+".html";
			
			ExtentSparkReporter reporter = new ExtentSparkReporter(repPath);
			reporter.config().setDocumentTitle("Test Report of nopcommerce.com site");
			reporter.config().setReportName("Automated Test Execution Report");
			
			extentrep = new ExtentReports();
			extentrep.attachReporter(reporter);
			
		}
		return extentrep;
	}
	
	public static ExtentTest createTest(String TestName) {
		
		testrep = getReportInstance().createTest(TestName);
		return testrep;
		
	}
	
	public static String captureScreenShot(WebDriver driver, String screenshotName) {
		
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir")+"/screenshots/"+screenshotName+".png";
			System.out.println("Path for Screenshot is: "+path);
			FileUtils.copyFile(src, new File(path));
			return path;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
