package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.getSiteResponse;
import base.BaseTest;
import pages.LoginPage;
import pages.DashBoard;
import utils.ExtentReportManager;
import utils.Log;
import utils.ValidateProductSearch;
//import utils.getSiteResponse;

public class searchProduct extends BaseTest {

//	=============== Static Credential Provider ======
	@DataProvider(name = "Credentials")
	public Object[][] getTestData() {

		return new Object[][] { { "admin@yourstore.com", "admin" } };
	}
	@Parameters("baseUrl")
	@Test(priority = 1, dataProvider = "Credentials")
	public void accessDashboard(String username, String password) {
		Log.info("Starting to Test Search Product by Name...");
		testrep = ExtentReportManager.createTest("Search Product by Name");
		testrep.info("Navigating to URL");
		LoginPage loginPage = new LoginPage(driver);
		DashBoard dashboard = new DashBoard(driver);

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
		String pageTitle = driver.getTitle();
		String ADMIN_TITLE = "Dashboard / nopCommerce administration";
		String actualMsg = loginPage.loginStatus().trim().replaceAll("\\s+", " ");
//		String Products = dashboard.productList().trim().replaceAll("\\s+"," ");

		getSiteResponse.validateLogin(driver, actualMsg, testrep);
		System.out.println(actualMsg);
		if (ADMIN_TITLE.equals(pageTitle) && actualMsg.equals(ADMIN_TITLE)) {
			String product = "Nikon D5500 DSLR";
			dashboard.clickcatalog();
			dashboard.clickproducts();
			dashboard.searchproductByName(product);
			dashboard.searchButton();
//			dashboard.productList();
			ValidateProductSearch.productsFound(driver, actualMsg, testrep);
		} else {
			testrep.fail(
					"Login was either Unsuccessful / hit Human verification warning. Can't continue with the testing");
			getSiteResponse.throwFailure(actualMsg);
		}
	}
}
