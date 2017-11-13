package testScripts;

import org.testng.annotations.Test; 
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class AmazonPurchase {
	WebDriver driver;
	
	@Test
	public void purchaseAmazon() {
		//Select Department
		selectDepartment();
		
		//Select the Features
		selectFeatures();
		
		//Sort list
		sortResults();
		
		//Select vacuum
		selectVacuum();
		
		//Add vacuum to cart
		addToCart();
		
		//Proceed to Checkout
		navigateToCart();
		
		//Remove warranty
		removeWarranty();
		
		//Proceed to Checkout
		proceedToCheckout();
		
		//Account login
		signIn();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver = utilities.DriverFactory.open("chrome");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String webURL = "https://www.amazon.com";
		
		driver.get(webURL);
		
	}
	@AfterMethod
	public void afterMethod() {
	}
	
	public void selectDepartment() {
		WebElement departmentHoverElement = driver.findElement(By.cssSelector("span[class='nav-line-2']"));
		departmentHoverElement.click();
		
		WebElement appliancesElement = driver.findElement(By.xpath("//*[@id=\"a-page\"]/div[2]/div/div[3]/div[1]/div/a[5]"));
		appliancesElement.click();
		
		WebElement vacuumElement = driver.findElement(By.linkText("Vacuums & Floor Care"));
		vacuumElement.click();
		
		WebElement roboticVacuumElement = driver.findElement(By.linkText("Robotic Vacuums"));
		roboticVacuumElement.click();
	}
	
	public void selectFeatures() {
		WebElement featureElement = driver.findElement(By.name("s-ref-checkbox-2631204011"));
		featureElement.click();
		
		WebElement primeElement = driver.findElement(By.name("s-ref-checkbox-2470955011"));
		primeElement.click();
	}

	public void sortResults() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement sortElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sort")));
		new Select(sortElement).selectByValue("review-rank");
	}
	
	public void selectVacuum() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//*[@id=\"result_1\"]/div/div[3]/div/a/h2")));
		selectElement.click();
	}
	
	public void addToCart() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement warrantyElement = driver.findElement(By.id("mbb-offeringID-2"));
		WebElement cartElement = driver.findElement(By.id("add-to-cart-button"));
		
		warrantyElement.click();
		cartElement.click();
	}
	
	public void navigateToCart() {
		WebElement cartElement = driver.findElement(By.id("hlb-view-cart-announce"));
		cartElement.click();
	}
	
	public void removeWarranty() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement removeElement = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("span[class='a-size-small sc-action-delete']")));
		removeElement.click();
	}
	
	public void proceedToCheckout() {
		WebElement checkoutElement = driver.findElement(By.name("proceedToCheckout"));
		checkoutElement.click();
	}
	
	public void signIn() {
		String email = "shwarnock89@gmail.com";
		String password = "otis20";
		
		
		//if(!passwordElement.isDisplayed()) {
			WebElement emailElement = driver.findElement(By.id("ap_email"));
			WebElement continueElement = driver.findElement(By.id("continue"));

			emailElement.sendKeys(email);
			continueElement.click();
			
			WebElement passwordElement = driver.findElement(By.id("ap_password"));
			passwordElement.sendKeys(password);
			
			WebElement signInElement = driver.findElement(By.id("signInSubmit"));
			signInElement.click();
		//}
		/*else {		
		WebElement emailElement = driver.findElement(By.id("ap_email"));
		WebElement signInElement = driver.findElement(By.id("signInSubmit"));
		
		emailElement.sendKeys(email);
		passwordElement.sendKeys(password);
		signInElement.click();
		}*/
	}
}
