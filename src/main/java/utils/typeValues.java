package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class typeValues {
	public static void enterChars(WebDriver driver, WebElement locator, String textToType) throws InterruptedException {
		// Mimic typing character by character
		String inputText = textToType;
		for (char ch : inputText.toCharArray()) {
			String outPutText = Character.toString(ch);
			locator.sendKeys(outPutText);
			Thread.sleep(100); // sleep 100ms between keystrokes
		}
	}

}
