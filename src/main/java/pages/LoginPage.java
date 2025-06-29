package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.By; // To be uncommented if you don't want to use PageFactory
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Log;

public class LoginPage {

	private WebDriver driver;

//	======= Using PageFactory of Selenium ======
	@FindBy(id = "Email")
	WebElement usernameTextBox;

	@FindBy(id = "Password")
	WebElement passwordTextBox;

	@FindBy(xpath = "/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button")
	WebElement loginButton;

	@FindBy(xpath = "/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[1]")
	WebElement loginError;

//	
//	@FindBy(id = "xEqtR4")
//	WebElement HumanVerify;
//	======= Normal method of declaration of locators =====
//	private By usernameTextBox = By.id("Email");
//	private By passwordTextBox = By.id("Password");
//	private By loginButton = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button");
//	private By loginError = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[1]");
//	private By loginMessage = By.xpath("/html/head/title");

//	======= Expected Error Messages=======
	String invalidUser = "No customer account found".trim().replaceAll("\\s+", " ");
	String invalidPassword = "The credentials provided are incorrect".trim().replaceAll("\\s+", " ");
	String humanVerification = "Verify you are human by completing the action below.".trim().replaceAll("\\s+", " ");
	String waitTime = "Verifying you are human. This may take a few seconds.".trim().replaceAll("\\s+", " ");
	String SuccessLogin = "Dashboard / nopCommerce administration".trim().replaceAll("\\s+", " ");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterUsername(String username) {
//		driver.findElement(usernameTextBox).clear();	// To be uncommented if you don't want to use PageFactory
//		driver.findElement(usernameTextBox).sendKeys(username);	// To be uncommented if you don't want to use PageFactory
		usernameTextBox.clear();
		usernameTextBox.sendKeys(username);
	}

	public void enterPassword(String password) {
//		driver.findElement(passwordTextBox).clear();	//// To be uncommented if you don't want to use PageFactory
//		driver.findElement(passwordTextBox).sendKeys(password);	// To be uncommented if you don't want to use PageFactory
		passwordTextBox.clear();
		passwordTextBox.sendKeys(password);
	}

	public void clickLogin() {
		Log.info("Clicking on Login Button...");
//		driver.findElement(loginButton).click();	// To be uncommented if you don't want to use PageFactory
		loginButton.click();
	}

	// Method to return login error message
	public String loginStatus() {
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);

		if ("Dashboard / nopCommerce administration".equals(pageTitle)) {
			return pageTitle;
		} else if ("Just a moment...".equals(pageTitle) || "nopCommerce demo store. Login".equals(pageTitle)) {
			return loginExceptions();
		} else
			return pageTitle;

	}

	public String loginExceptions() {

		try {
			WebElement element = driver.findElement(By.id("xEqtR4"));
			element.getText();
			System.out.println("Human Verification Required... " + element.getText());
			String ErrorMessage = element.getText();
			return ErrorMessage;
		} catch (NoSuchElementException e) {
			System.out.println("Human Verification Element not found, skipping...");
			try {
				String fallbackMessage = loginError.getText();
				System.out.println("Fallback message: " + fallbackMessage);
				return fallbackMessage;
			} catch (NoSuchElementException ex2) {
				System.out.println("Element not found, skipping...");
				WebElement VerifyHuman = driver.findElement(By.id("xEqtR4"));
				String ErrorMessage = VerifyHuman.getText();
				return ErrorMessage;
			}

		}

	}
	
	
}
