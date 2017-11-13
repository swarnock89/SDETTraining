package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utilities.LoginToAccount;

public class CreateAccount {

	public static void main(String[] args) {
		//Set variables
		WebDriver driver;
		String browserType = "chrome";
		String webURL = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
		String name = "Shawn Warnock";
		String email = "shwarnock89@gmail.com";
		String phone = "9197856284";
		String password = "warnocks1";
		String gender = "Male";
		String country = "Croatia";
		boolean weeklyEmail = true;
		boolean monthlyEmail = false;
		boolean occasionalEmail = false;
		String actualText;
		String expectedText = "Customer information added successfully";
		
		//Initialize the driver
		driver = utilities.DriverFactory.open(browserType);
		
		//Navigate to webpage
		driver.get(webURL);
		
		//Navigate to create account page
		driver.findElement(By.xpath("//*[@id=\"ctl01\"]/div[3]/div[2]/div/div[2]/a")).click();
		
		//Set WebElements
		WebElement nameElement = driver.findElement(By.id("MainContent_txtFirstName"));
		WebElement emailElement = driver.findElement(By.id("MainContent_txtEmail"));
		WebElement phoneElement = driver.findElement(By.id("MainContent_txtHomePhone"));
		WebElement passwordElement = driver.findElement(By.id("MainContent_txtPassword"));
		WebElement verifyElement = driver.findElement(By.id("MainContent_txtVerifyPassword"));
		WebElement maleElement = driver.findElement(By.id("MainContent_Male"));
		WebElement femaleElement = driver.findElement(By.id("MainContent_Female"));
		WebElement countryElement = driver.findElement(By.id("MainContent_menuCountry"));
		WebElement weeklyElement = driver.findElement(By.id("MainContent_checkWeeklyEmail"));
		WebElement monthlyElement = driver.findElement(By.id("MainContent_checkMonthlyEmail"));
		WebElement occasionalElement = driver.findElement(By.id("MainContent_checkUpdates"));
		WebElement submitElement = driver.findElement(By.name("ctl00$MainContent$btnSubmit"));
		
		//Input data
		nameElement.sendKeys(name);
		emailElement.sendKeys(email);
		phoneElement.sendKeys(phone);
		passwordElement.sendKeys(password);
		verifyElement.sendKeys(password);
		
		//Select Gender
		if(gender.equalsIgnoreCase("Male")) {
			maleElement.click();
		}
		else {
			femaleElement.click();
		}
		
		//Select Country
		new Select(countryElement).selectByVisibleText(country);
		
		//Select Checkboxes
		if (weeklyEmail) {
			if(!weeklyElement.isSelected()) {
				weeklyElement.click();
			}
		}
		else {
			if(weeklyElement.isSelected()) {
				weeklyElement.click();
			}
		}
		
		if (monthlyEmail) {
			if(!monthlyElement.isSelected()) {
				monthlyElement.click();
			}
		}
		else {
			if(monthlyElement.isSelected()) {
				monthlyElement.click();
			}
		}
		
		if(occasionalEmail) {
			if(!occasionalElement.isSelected()) {
				occasionalElement.click();
			}
		}
		else {
			if(occasionalElement.isSelected()) {
				occasionalElement.click();
			}
		}
		
		//Submit Information
		submitElement.click();
		
		//Check for verification
		WebElement confirmationElement = driver.findElement(By.id("MainContent_lblTransactionResult"));
		actualText = confirmationElement.getText();
		if(actualText.equals(expectedText)) {
			System.out.println(actualText);
		}
		
		//Login to created account
		LoginToAccount account = new LoginToAccount(driver, email, password);
		System.out.println(account.AccountLogin());
		
		//Close Webdriver
		driver.close();
	}
}
