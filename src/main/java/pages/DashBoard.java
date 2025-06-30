package pages;

//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;

//import java.util.List;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Log;
import utils.randomWait;

public class DashBoard {

	private WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//	======= Using PageFactory of Selenium ======
	@FindBy(xpath = "//nav/ul/li[2]/a")
	WebElement catalog;

	@FindBy(xpath = "///li[2]/ul/li/a/p")
	WebElement products;

	@FindBy(id = "SearchProductName")
	WebElement SearchProductName;

	@FindBy(id = "search-products")
	WebElement searchProductsButton;

	@FindBy(id = "products-grid")
	WebElement productList;

	public DashBoard(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public WebElement waitforPage(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated((locator)));
		
		System.out.println(element);
		return element;
	}

	public void clickcatalog() {
		Log.info("Clicking on Catalog tab in left navigation pane...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.nav-item.has-treeview > a.nav-link")));
		element.click();
	}

	public void clickproducts() {

		Log.info("Clicking on Products tab in left navigation pane...");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement productsSubmenu = wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("li.nav-item > a.nav-link > p")));
		randomWait.delayClick(driver, productsSubmenu);
	}

	public void searchproductByName(String productName) {
		WebElement productEl = waitforPage(By.id("SearchProductName"));
		System.out.println(productEl);
		Log.info("Clicking on Search Products By Name...");
		SearchProductName.click();
		SearchProductName.sendKeys(productName);
	}

	public void searchButton() {

		Log.info("Clicking on Search Button...");
		searchProductsButton.click();
		
	}
	
//	public String productList() {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		WebElement products = wait.until(ExpectedConditions.presenceOfElementLocated(
//				By.cssSelector("#products-grid > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(3)")));
//		String Product = products.getCssValue("#products-grid > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(3)");
//		System.out.println(Product);
		
		

////	=================== Under development ================
//	public void exportproductList() {
////		WebElement product = driver.findElement(By.partialLinkText("Nikon"));
////		String productName = product.getText();
////		System.out.println(productName);
//////		if(product )
////		return products;
//		List<WebElement> rows = driver.findElements(By.cssSelector("table tr"));
//
//		try (BufferedWriter writer = new BufferedWriter(new FileWriter("products.txt"))) {
//		    for (WebElement row : rows) {
//		        List<WebElement> cells = row.findElements(By.tagName("td"));
//		        if (cells.size() < 8) continue;
//
//		        String productId = cells.get(0).findElement(By.cssSelector("input[type='checkbox']")).getAttribute("value");
//		        String imgSrc = cells.get(1).findElement(By.tagName("img")).getAttribute("src");
//		        String productName = cells.get(2).getText();
//		        String sku = cells.get(3).getText();
//		        String price = cells.get(4).getText();
//		        String quantity = cells.get(5).getText();
//		        String editLink = cells.get(7).findElement(By.tagName("a")).getAttribute("href");
//
//		        String line = String.format("ID: %s | Name: %s | SKU: %s | Price: %s | Qty: %s | Image: %s | Edit: %s",
//		                                    productId, productName, sku, price, quantity, imgSrc, editLink);
//
//		        writer.write(line);
//		        writer.newLine(); // Adds a line break
//		    }
//		    System.out.println("✅ Data written to products.txt successfully.");
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}


	
}
