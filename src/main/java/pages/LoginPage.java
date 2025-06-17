package pages;

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
//	======= Normal method of declaration of locators =====
//	private By usernameTextBox = By.id("Email");
//	private By passwordTextBox = By.id("Password");
//	private By loginButton = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[3]/button");
//	private By loginError = By.xpath("/html/body/div[6]/div/div/div/div/div[2]/div[1]/div/form/div[1]");

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
//		WebElement errorMessage = driver.findElement(loginError);	// To be uncommented if you don't want to use PageFactory
//		return errorMessage.getText(); // Returning the error message text // To be uncommented if you don't want to use PageFactory
		return loginError.getText();
	}
}
