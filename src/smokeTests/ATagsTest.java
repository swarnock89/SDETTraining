package smokeTests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class ATagsTest {
	WebDriver driver;
	
	@Test
	public void createAccountIsPresent() {
		boolean createAccountPresent = false;
		List <WebElement> aElements = driver.findElements(By.tagName("a"));

		for (WebElement aElement : aElements) {
			if(aElement.getText().equals("CREATE ACCOUNT")) {
				createAccountPresent = true;
			}
		}
		Assert.assertTrue(createAccountPresent);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver = utilities.DriverFactory.open("chrome");
		final String webURL = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
		
		driver.get(webURL);
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

}
