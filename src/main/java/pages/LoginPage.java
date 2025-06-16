package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Log;

public class LoginPage {

	private WebDriver driver;

//	======= Normal method of declaration of locators =====
	private By usernameTextBox = By.id("Email");
	private By passwordTextBox = By.id("Password");
	private By loginButton = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button");
	private By loginError = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[1]");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) {
		driver.findElement(usernameTextBox).clear();
		driver.findElement(usernameTextBox).sendKeys(username);
	}

	public void enterPassword(String password) {
		driver.findElement(passwordTextBox).clear();
		driver.findElement(passwordTextBox).sendKeys(password);
	}

	public void clickLogin() {
		Log.info("Clicking on Login Button...");
		driver.findElement(loginButton).click();
	}

	// Method to return login error message
	public String loginStatus() {
		WebElement errorMessage = driver.findElement(loginError);
		return errorMessage.getText(); // Returning the error message text
	}
}
