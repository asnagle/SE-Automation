package base;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import utils.EmailUtils;
import utils.ExtentReportManager;
import utils.Log;

public class BaseTest {

	protected WebDriver driver;
	protected static ExtentReports extentRep;
	protected ExtentTest testrep;

	@BeforeSuite
	public static void setupReport() {

		extentRep = ExtentReportManager.getReportInstance();
	}

	@AfterSuite
	public static void teardownReport() {
		try {
			extentRep.flush();
			File fullPath = new File(ExtentReportManager.reportPath);
			String reportFolder = fullPath.getParent();
			EmailUtils.sendTestReport(reportFolder);
		} catch (Exception e) {
			System.err.println("Flush failed: " + e.getMessage());
		}

	}

	@BeforeMethod
	public void setUp() {

		Log.info("Starting Web Browser...");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Log.info("Navigating to URL...");
		driver.get("https://admin-demo.nopcommerce.com/login");

	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = ExtentReportManager.captureScreenShot(driver, "LoginFailure");
			testrep.fail("Test Failed... Check Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}

		if (driver != null) {
			Log.info("Closing the Browser...");
			driver.quit();
		}

	}

}
