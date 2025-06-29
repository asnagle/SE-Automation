package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class ValidateProductSearch {
	public static void productsFound(WebDriver driver, String actualMsg, ExtentTest testrep) {
		
		String searchedProd = "Nikon D5500 DSLR";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement products = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.cssSelector("#products-grid > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(3)")));
		String Product = products
				.getCssValue("#products-grid > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(3)");
		if(searchedProd.equals(Product)) {
			testrep.pass("Product Found: " + Product);
            Log.info("Test completed Successfully ");
		}
	}
	
}
