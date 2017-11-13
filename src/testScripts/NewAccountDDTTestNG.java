package testScripts;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;

public class NewAccountDDTTestNG {
	WebDriver driver;
	String name, email, phone, password, gender, country;
	boolean weeklyEmail, monthlyEmail, occasionalEmail;
	WebElement nameElement, emailElement, phoneElement, passwordElement, countryElement,
		weeklyElement, monthlyElement, occasionalElement, verifyElement, maleElement, femaleElement, submitElement;
	boolean accountCreated = false;
	
	@Test(dataProvider = "getData")
	public void createAccount(String name, String email, String phone, String password, String gender, String country, 
			String weeklyEmail, String monthlyEmail, String occasionalEmail) {
		//define Variables
		defineVariables(name, email, phone, password, gender, country, weeklyEmail, monthlyEmail, occasionalEmail);
		
		//call method to define WebElements
		defineWebElements();
		
		//Input data
		inputData();
		
		//Submit data
		submitData();
		
		//Assert
		assertTest();
	}
	
	@DataProvider
	public String[][] getData() {
		final String filename = "C:\\Users\\shwar\\Documents\\Automation\\AccountInfo.xls";
		return utilities.ExcelReader.get(filename);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver = utilities.DriverFactory.open("chrome");
		final String webURL = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
		
		driver.get(webURL);
		
		WebElement createAccountElement = driver.findElement(By.cssSelector("a[class='btn btn-default']"));
		createAccountElement.click();
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
	
	public void defineWebElements() {
		nameElement = driver.findElement(By.id("MainContent_txtFirstName"));
		emailElement = driver.findElement(By.id("MainContent_txtEmail"));
		phoneElement = driver.findElement(By.id("MainContent_txtHomePhone"));
		passwordElement = driver.findElement(By.id("MainContent_txtPassword"));
		verifyElement = driver.findElement(By.id("MainContent_txtVerifyPassword"));
		maleElement = driver.findElement(By.id("MainContent_Male"));
		femaleElement = driver.findElement(By.id("MainContent_Female"));
		countryElement = driver.findElement(By.id("MainContent_menuCountry"));
		weeklyElement = driver.findElement(By.id("MainContent_checkWeeklyEmail"));
		monthlyElement = driver.findElement(By.id("MainContent_checkMonthlyEmail"));
		occasionalElement = driver.findElement(By.id("MainContent_checkUpdates"));
		submitElement = driver.findElement(By.name("ctl00$MainContent$btnSubmit"));
	}
	
	public void inputData() {
		nameElement.sendKeys(name);
		emailElement.sendKeys(email);
		phoneElement.sendKeys(phone);
		passwordElement.sendKeys(password);
		verifyElement.sendKeys(password);
		new Select(countryElement).selectByVisibleText(country);
		selectGender();
		selectCheckBoxes();
	}
	
	public void defineVariables(String name, String email, String phone, String password, String gender, String country, 
			String weeklyEmail, String monthlyEmail, String occasionalEmail) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.gender = gender;
		this.country = country;
		if(weeklyEmail.equalsIgnoreCase("True")) {this.weeklyEmail = true;}
		else {this.weeklyEmail = false;}
		if(monthlyEmail.equalsIgnoreCase("True")) {this.monthlyEmail = true;}
		else {this.monthlyEmail = false;}
		if(occasionalEmail.equalsIgnoreCase("True")){this.occasionalEmail = true;}
		else {this.occasionalEmail = false;}
		}
	
	public void selectGender() {
		if(gender.equalsIgnoreCase("Male")) {
			maleElement.click();
		}
		else {
			femaleElement.click();
		}
	}
	
	public void selectCheckBoxes() {
		if(weeklyEmail) {if(!weeklyElement.isSelected()) {
				weeklyElement.click();}
		}
		else {if(weeklyElement.isSelected()) {
				weeklyElement.click();}
		}
		if(monthlyEmail) {if(!monthlyElement.isSelected()) {
				monthlyElement.click();}
			}
		else {if(monthlyElement.isSelected()) {
				monthlyElement.click();}
		}
		if(occasionalEmail) {if(!occasionalElement.isSelected()) {
				occasionalElement.click();}
		}
		else {if(occasionalElement.isSelected()) {
				occasionalElement.click();}
		}
	}
	
	public void submitData() {
		submitElement.click();
	}
	
	public void assertTest() {
		final String expectedText = "Customer information added successfully";
		WebElement confirmationElement = driver.findElement(By.id("MainContent_lblTransactionResult"));
		String actualText = confirmationElement.getText();
		if(actualText.equals(expectedText)) {
			accountCreated = true;
		}
		Assert.assertEquals(true, accountCreated);
	}
}
