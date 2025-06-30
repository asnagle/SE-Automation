package utils;

import java.time.Duration;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public class randomWait {
	public static void delayClick(WebDriver driver, WebElement locator) {
		Actions actions = new Actions(driver);
		actions.moveToElement(locator).pause(Duration.ofMillis(700)).click().perform();
		try {
			Thread.sleep(800 + new Random().nextInt(800));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
