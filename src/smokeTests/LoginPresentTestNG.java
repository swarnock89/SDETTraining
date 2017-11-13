package smokeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPresentTestNG {
	WebDriver driver;
	
	@Test
	public void loginElementsPresentTest() {
		boolean LoginEmailBox = driver.findElement(By.name("ctl00$MainContent$txtUserName")).isDisplayed();
		boolean passwordBox = driver.findElement(By.name("ctl00$MainContent$txtPassword")).isDisplayed();
		
		Assert.assertTrue(LoginEmailBox, "Email text box is present");
		Assert.assertTrue(passwordBox, "Password text box is present");
	}
	
	@BeforeMethod
	public void SetUp() {
		System.out.println("Starting test");
		
		final String webURL = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
		driver = utilities.DriverFactory.open("chrome");

		driver.get(webURL);
	}
	
	@AfterMethod
	public void TearDown() {
		System.out.print("Closing test");
		driver.close();
	}
}
