package tests;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;
import utils.getSiteResponse;

public class LoginTests extends BaseTest {

//	Get Login credentials from Excel file
	@DataProvider(name = "ValidLoginData")
	public Object[][] getValidLoginCredentials() throws IOException {

		String srcFile = System.getProperty("user.dir") + "/TestData/testdata.xlsx";
		ExcelUtils.loadExcel(srcFile, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount - 1][2];

		for (int i = 1; i < rowCount; i++) {

			data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Gets Username
			data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Gets Password

		}
		ExcelUtils.closeExcel();
		return data;
	}

	@DataProvider(name = "InValidLoginData")
	public Object[][] getInValidLoginCredentials() throws IOException {

		String srcFile = System.getProperty("user.dir") + "/TestData/testdata.xlsx";
		ExcelUtils.loadExcel(srcFile, "Sheet2");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount - 1][2];

		for (int i = 1; i < rowCount; i++) {

			data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Gets Username
			data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Gets Password

		}
		ExcelUtils.closeExcel();
		return data;
	}
//	============= Example of how to get data from different Data Provider =================

	@DataProvider(name = "LoginData2")
	public Object[][] getData() {

		return new Object[][] { { "admin@yourstore.com", "admin" }, { "duser1@test.com", "admin" },
				{ "admin@yourstore.com", "dpass2" }, { "duser3@test.com", "dpass3" } };
	}

//	=============== Testing purpose to be deleted later ======
	@DataProvider(name = "LoginData3")
	public Object[][] getTestData() {

		return new Object[][] { { "admin@yourstore.com", "admin" } };
	}

//=============== Following code reads Login Credentials from Excel files and provides as input value for the Username & Password Credentials

	@Test(priority = 1, dataProvider = "ValidLoginData") // Uncomment to use data from
	public void validLogin(String username, String password) {

		Log.info("Starting to test Login with Valid Credentials...");

		testrep = ExtentReportManager.createTest("Login with valid credentials Test");

		testrep.info("Navigating to URL");

		LoginPage loginPage = new LoginPage(driver);

		Log.info("Providing Credentials..");
		testrep.info("Providing Credentials...");
		try {
			loginPage.enterUsername(username);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginPage.enterPassword(password);
		testrep.info("Clicking on Login Button");
		loginPage.clickLogin();
		System.out.println("Title is: " + driver.getTitle());

//		======= Expected Error Messages=======
		String actualMsg = loginPage.loginStatus().trim().replaceAll("\\s+", " ");

		System.out.println(actualMsg);
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);

//		=================== Old Validation Code =====================
//		if ("Dashboard / nopCommerce administration".equals(pageTitle)) {
//			Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
//			Assert.assertEquals(actualMsg, "Dashboard / nopCommerce administration");
//			Log.info("Test completed Successfully ");
//			testrep.pass("Login Successful");
//		}
//		if ("Just a moment...".equals(pageTitle) && invalidUser.isEmpty()) {
//			Assert.assertEquals(driver.getTitle(), "Just a moment...");
//			Log.info(driver.getTitle());
//			Log.info("Test completed Successfully ");
//			testrep.pass("Login Successful");
//		} else if ("Just a moment...".equals(pageTitle) && actualMsg.equals(humanVerification)) {
//			Assert.assertEquals(driver.getTitle(), "Just a moment...");
//			Log.info("Need user intervention: " + humanVerification);
//			Log.info("Test completed Successfully ");
//			testrep.warning("Need User intervention: " + "Re-run the test manually.." + humanVerification);
//		} else if ("Just a moment...".equals(pageTitle) && actualMsg.equals(waitTime)) {
//			Assert.assertEquals(driver.getTitle(), "Just a moment...");
//			Log.info("Need user intervention: " + humanVerification);
//			Log.info("Test completed Successfully ");
//			testrep.warning("Need User intervention: " + "Re-run the test manually.." + humanVerification);
//		} else if ("Just a moment...".equals(pageTitle) && actualMsg.equals(invalidUser)) {
//			Assert.assertEquals(driver.getTitle(), "Just a moment...");
//			Log.info("User Account does not Exist...");
//			Log.info("Test completed Successfully ");
//			testrep.fail(invalidUser);
//			Assert.fail("Test failed due to Invalid Username/Account.");
//			throw new AssertionError("Marking test as failed due to Invalid Username/Account");
//		} else if ("Just a moment...".equals(pageTitle) && actualMsg.equals(invalidPassword)) {
//			Assert.assertEquals(driver.getTitle(), "Just a moment...");
//			Log.info("Incorrect Password Entered...");
//			Log.info("Test completed Successfully ");
//			testrep.fail(invalidPassword);
//			Assert.fail("Test failed due to Invalid Password.");
//			throw new AssertionError("Marking test as failed due to Invalid Password");
//		} else if ("Dashboard / nopCommerce administration".equals(pageTitle) && invalidUser.isEmpty()) {
//			Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
//			Log.info("Need user intervention: " + humanVerification);
//			Log.info("Test completed Successfully ");
//			testrep.warning("Need User intervention: " + "Re-run the test manually.." + humanVerification);
//		} else if ("Dashboard / nopCommerce administration".equals(pageTitle) && actualMsg.equals(invalidUser)) {
//			Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
//			Log.info(invalidUser);
//			Log.info("Test completed Successfully ");
//			testrep.fail(invalidUser);
//			Assert.fail("Test failed due to Invalid Username/Account.");
//			throw new AssertionError("Marking test as failed due to Invalid Username/Account");
//		} else if ("nopCommerce demo store. Login".equals(pageTitle) && actualMsg.equals(invalidUser)) {
//			Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
//			Log.info(invalidUser);
//			Log.info("Test completed Successfully ");
//			testrep.fail(invalidUser);
//			Assert.fail("Test failed due to Invalid Username/Account.");
//			throw new AssertionError("Marking test as failed due to Invalid Username/Account");
//		} else if ("Dashboard / nopCommerce administration".equals(pageTitle) && actualMsg.equals(invalidPassword)) {
//			Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
//			Log.info(invalidPassword);
//			Log.info("Test completed Successfully ");
//			testrep.fail(invalidPassword);
//			Assert.fail("Test failed due to Invalid Password.");
//			throw new AssertionError("Marking test as failed due to Invalid Password");
//		} else if ("nopCommerce demo store. Login".equals(pageTitle) && actualMsg.equals(invalidPassword)) {
//			Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
//			Log.info(invalidPassword);
//			Log.info("Test completed Successfully ");
//			testrep.fail(invalidPassword);
//			Assert.fail("Test failed due to Invalid Password.");
//			throw new AssertionError("Marking test as failed due to Invalid Password");
//		} else if ("nopCommerce demo store. Login".equals(pageTitle) && actualMsg.equals(invalidPassword)
//				&& actualMsg.equals(invalidUser)) {
//			Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
//			Log.info(invalidUser + invalidPassword);
//			Log.info("Test completed Successfully ");
//			testrep.fail("Invalid Credentials..." + invalidUser + invalidPassword);
//			Assert.fail("Test failed due to Invalid Password.");
//			throw new AssertionError("Marking test as failed due to Invalid Password");

//		=======================================================================
		getSiteResponse.validateLogin(driver, actualMsg, testrep);
	}
//	}

//	================ Following Code uses hardcoded values for username & password =========
//	@Test(priority = 1)
//	public void validLogin() {
//
//		Log.info("Starting to test Login with Valid Credentials...");
//
//		testrep = ExtentReportManager.createTest("Login with valid credentials Test");
//
//		testrep.info("Navigating to URL");
//
//		LoginPage loginPage = new LoginPage(driver);
//
//		Log.info("Providing Credentials..");
//		testrep.info("Providing Credentials...");
//		loginPage.enterUsername("admin@yourstore.com");
//		loginPage.enterPassword("admin");
//		testrep.info("Clicking on Login Button");
//		loginPage.clickLogin();
//		System.out.println("Title is: " + driver.getTitle());
//
//		String pageTitle = driver.getTitle();
//		if (pageTitle.equalsIgnoreCase("Just a moment...")) {
//			Assert.assertEquals(driver.getTitle(), "Just a moment...");
//			Log.info("Test completed Successfully ");
//			testrep.pass("Login Successful");
//		} else if (pageTitle.equalsIgnoreCase("Dashboard / nopCommerce administration")) {
//			Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
//			Log.info("Test completed Successfully ");
//			testrep.pass("Login Successful");
//		}
//
//	}

//	================ Following Code uses hardcoded values for username & password =========
//
//	@Test(priority = 2)
//	public void invalidLogin() {
//
//		Log.info("Starting to test Login with Invalid Credentials...");
//		testrep = ExtentReportManager.createTest("Login with Invalid credentials Test");
//		testrep.info("Navigating to URL");
//
//		LoginPage loginPage = new LoginPage(driver);
//
//		Log.info("Providing Invalid Credentials...");
//		testrep.info("Providing Invalid Credentials...");
//		loginPage.enterUsername("admin@yourstore.com");
//		loginPage.enterPassword("admin123");
//		loginPage.clickLogin();
//		loginPage.loginStatus();
//		// Get the returned error message
//		String actualMsgText = loginPage.loginStatus().trim().replaceAll("\\s+", " ");
//
//		// Expected error message
//		String expectedErrorText = "Login was unsuccessful. Please correct the errors and try again. The credentials provided are incorrect"
//				.trim().replaceAll("\\s+", " ");
//		System.out.println("Title is: " + driver.getTitle());
//		Log.info("Printing Test result...");
//		testrep.info("Getting Login Page Error");
//		System.out.println("Failed Login error: " + actualMsgText);
//		Assert.assertEquals(actualMsgText, expectedErrorText, "Test Failed");
//		testrep.pass("Login Failed as expected");
//
//	}

//	=============== Following code reads Login Credentials from Excel files and provides as input value for the Username & Password Credentials

	@Test(priority = 2, dataProvider = "InValidLoginData")
	public void invalidLogin(String username, String password) {

		Log.info("Starting to test Login with Invalid Credentials...");

		testrep = ExtentReportManager.createTest("Login with Invalid credentials Test");

		testrep.info("Navigating to URL");

		LoginPage loginPage = new LoginPage(driver);

		Log.info("Providing Credentials..");
		testrep.info("Providing Credentials...");
		try {
			loginPage.enterUsername(username);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginPage.enterPassword(password);
		testrep.info("Clicking on Login Button");
		loginPage.clickLogin();
		System.out.println("Title is: " + driver.getTitle());

//		======= Expected Titles & Error Messages=======
		String actualMsg = loginPage.loginStatus().trim().replaceAll("\\s+", " ");

		System.out.println(actualMsg);
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);

//		============================= Old Validation Code =============================
//		if ("Dashboard / nopCommerce administration".equals(pageTitle)) {
//		Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
//			Assert.assertEquals(actualMsg, "Dashboard / nopCommerce administration");
//			Log.info("Test completed Successfully ");
//			testrep.fail("Valid Credentials Entered..");
//			Assert.fail("Test failed due to Valid Credentials.");
//			throw new AssertionError("Marking test as failed due to Valid Credentials..");
//		}
//		if ("Just a moment...".equals(pageTitle) && invalidUser.isEmpty()) {
//			Assert.assertEquals(driver.getTitle(), "Just a moment...");
//			Log.info(driver.getTitle());
//			Log.info("Test completed Successfully ");
//			testrep.fail("Valid Credentials Entered..");
//			Assert.fail("Test failed due to Valid Credentials.");
//			throw new AssertionError("Marking test as failed due to Valid Credentials..");
//		} else if ("Just a moment...".equals(pageTitle) && actualMsg.equals(humanVerification)) {
//			Assert.assertEquals(driver.getTitle(), "Just a moment...");
//			Log.info("Need user intervention: " + humanVerification);
//			Log.info("Test completed Successfully ");
//			testrep.warning("Need User intervention: " + "Re-run the test manually.." + humanVerification);
//		} else if ("Just a moment...".equals(pageTitle) && actualMsg.equals(waitTime)) {
//			Assert.assertEquals(driver.getTitle(), "Just a moment...");
//			Log.info("Need user intervention: " + humanVerification);
//			Log.info("Test completed Successfully ");
//			testrep.warning("Need User intervention: " + "Re-run the test manually.." + humanVerification);
//		} else if ("Just a moment...".equals(pageTitle) && actualMsg.equals(invalidUser)) {
//			Assert.assertEquals(driver.getTitle(), "Just a moment...");
//			Log.info("User Account does not Exist...");
//			Log.info("Test completed Successfully ");
//			testrep.pass("Login failed as expected...");
//			Assert.assertEquals(actualMsg, invalidUser, "Test PASSED");
//		} else if ("Just a moment...".equals(pageTitle) && actualMsg.equals(invalidPassword)) {
//			Assert.assertEquals(driver.getTitle(), "Just a moment...");
//			Log.info("Incorrect Password Entered...");
//			Log.info("Test completed Successfully ");
//			testrep.pass(invalidPassword);
//			Assert.assertEquals(actualMsg, invalidPassword, "Test PASSED");
//		} else if ("Dashboard / nopCommerce administration".equals(pageTitle) && invalidUser.isEmpty()) {
//			Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
//			Log.info("Need user intervention: " + humanVerification);
//			Log.info("Test completed Successfully ");
//			testrep.warning("Need User intervention: " + "Re-run the test manually.." + humanVerification);
//		} else if ("Dashboard / nopCommerce administration".equals(pageTitle) && actualMsg.equals(invalidUser)) {
//			Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
//			Log.info("User Account does not Exist...");
//			Log.info("Test completed Successfully ");
//			testrep.pass(invalidUser);
//			Assert.assertEquals(actualMsg, invalidUser, "Test PASSED");
//		} else if ("nopCommerce demo store. Login".equals(pageTitle) && actualMsg.equals(invalidUser)) {
//			Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
//			Log.info("User Account does not Exist...");
//			Log.info("Test completed Successfully ");
//			testrep.pass(invalidUser);
//			Assert.assertEquals(actualMsg, invalidUser, "Test PASSED");
//		} else if ("Dashboard / nopCommerce administration".equals(pageTitle) && actualMsg.equals(invalidPassword)) {
//			Assert.assertEquals(driver.getTitle(), "Dashboard / nopCommerce administration");
//			Log.info("Incorrect Password Entered...");
//			Log.info("Test completed Successfully ");
//			testrep.pass(invalidPassword);
//			Assert.assertEquals(actualMsg, invalidPassword, "Test PASSED");
//		} else if ("nopCommerce demo store. Login".equals(pageTitle) && actualMsg.equals(invalidPassword)) {
//			Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
//			Log.info("Incorrect Password Entered...");
//			Log.info("Test completed Successfully ");
//			testrep.pass(invalidPassword);
//			Assert.assertEquals(actualMsg, invalidPassword, "Test PASSED");
//		} else if ("nopCommerce demo store. Login".equals(pageTitle) && actualMsg.equals(invalidPassword)
//				&& actualMsg.equals(invalidUser)) {
//			Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");
//			Log.info(invalidUser + invalidPassword);
//			Log.info("Test completed Successfully ");
//			testrep.pass("Invalid Credentials..." + invalidUser + invalidPassword);
//			Assert.assertEquals(actualMsg, invalidUser, "Test PASSED");
//		=========================================================================
		getSiteResponse.validateInvalidLogin(driver, actualMsg, testrep);

	}

}
