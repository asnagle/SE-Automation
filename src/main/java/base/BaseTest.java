package base;

import java.io.File;
import java.util.Arrays;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
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

//			***** Uncomment to enable emailing report feature
			File fullPath = new File(ExtentReportManager.reportPath);
			String reportFolder = fullPath.getParent();
			EmailUtils.sendTestReport(reportFolder);
		} catch (Exception e) {
			System.err.println("Flush failed: " + e.getMessage());
		}

	}

//	@BeforeMethod
//	public void setUp() {
//
//		Log.info("Starting Web Browser...");
//		driver = new ChromeDriver();
//		driver = new FirefoxDriver();
//		driver.manage().window().maximize();

//	=============== Starting Browser in incognito mode and try to bypass Verification ===========
	@BeforeMethod
	public void setUp() {

		Log.info("Starting Web Browser...");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito"); // ← This is the key!
		options.addArguments("--start-maximized");
		options.addArguments("--disable-blink-features=AutomationControlled");
		options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
				+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");

		driver = new ChromeDriver(options);

		// 👻 JavaScript to hide 'webdriver' property
		((JavascriptExecutor) driver)
				.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

		Log.info("Navigating to URL...");
		driver.get("https://admin-demo.nopcommerce.com/admin");
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
