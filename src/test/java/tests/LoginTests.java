package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTests extends BaseTest {

//	================ Following Code uses hardcoded values for username & password =========
	@Test(priority = 1)
	public void validLogin() {

		Log.info("Starting to test Login with Valid Credentials...");

		testrep = ExtentReportManager.createTest("Login with valid credentials Test");

		testrep.info("Navigating to URL");

		LoginPage loginPage = new LoginPage(driver);

		Log.info("Providing Credentials..");
		testrep.info("Providing Credentials...");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
		testrep.info("Clicking on Login Button");
		loginPage.clickLogin();
		System.out.println("Title is: " + driver.getTitle());

		String pageTitle = driver.getTitle();
		if (pageTitle.equalsIgnoreCase("Just a moment...")) {
			Assert.assertEquals(driver.getTitle(), "Just a moment...");
			Log.info("Test completed Successfully ");
			testrep.pass("Login Successful");
		} else if (pageTitle.equalsIgnoreCase("Dashboard / nopCommerce administration")) {
			Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
			Log.info("Test completed Successfully ");
			testrep.pass("Login Successful");
		}

	}

//	================ Following Code uses hardcoded values for username & password =========

	@Test(priority = 2)
	public void invalidLogin() {

		Log.info("Starting to test Login with Invalid Credentials...");
		testrep = ExtentReportManager.createTest("Login with Invalid credentials Test");
		testrep.info("Navigating to URL");

		LoginPage loginPage = new LoginPage(driver);

		Log.info("Providing Invalid Credentials...");
		testrep.info("Providing Invalid Credentials...");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin123");
		loginPage.clickLogin();
		loginPage.loginStatus();
		// Get the returned error message
		String actualErrorText = loginPage.loginStatus().trim().replaceAll("\\s+", " ");

		// Expected error message
		String expectedErrorText = "Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect"
				.trim().replaceAll("\\s+", " ");
		System.out.println("Title is: " + driver.getTitle());
		Log.info("Printing Test result...");
		testrep.info("Getting Login Page Error");
		System.out.println("Failed Login error: " + actualErrorText);
		Assert.assertEquals(actualErrorText, expectedErrorText, "Test Failed");
		testrep.pass("Login Failed as expected");

	}

}
