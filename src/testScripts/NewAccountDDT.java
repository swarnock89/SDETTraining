package testScripts;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(value = Parameterized.class)
public class NewAccountDDT {
	//Set variables
	WebDriver driver;
	String name, email, phone, password, country, gender, actualText;
	boolean weeklyEmail, monthlyEmail, occasionalEmail; 
	boolean successfullyCreatedAccount = false;
	String expectedText = "Customer information added successfully";
	WebElement nameElement, emailElement, phoneElement, passwordElement, verifyElement, maleElement, femaleElement,
		countryElement, weeklyElement, monthlyElement, occasionalElement, submitElement;
	
	@Test
	public void newAccountTest() {
		//Define WebElements
		defineWebElements();
		
		//Input data
		inputData();
		
		//Submit Information
		submitElement.click();
		
		//Assert
		assertCheck();
	}

	@Parameters
	public static List<String[]> getData(){
		final String filename = "C:\\Users\\shwar\\Documents\\Automation\\AccountInfo.csv";
		return utilities.CSVReader.get(filename);
	}
	
	@Before
	public void beforeMethod() {
		driver = utilities.DriverFactory.open("chrome");
		final String webURL = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
		
		driver.get(webURL);
		
		WebElement createAccountElement = driver.findElement(By.xpath("//*[@id=\"ctl01\"]/div[3]/div[2]/div/div[2]/a"));
		createAccountElement.click();
	}
	
	@After
	public void afterMethod() {
		driver.close();
	}
	
	//Constructor
	public NewAccountDDT(String name, String email, String phone, String password, String gender, 
			String country, String weeklyEmail, String monthlyEmail, String occasionalEmail) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.gender = gender;
		this.country = country;
		if(weeklyEmail.equalsIgnoreCase("true")) {this.weeklyEmail = true;}
		else {this.weeklyEmail = false;}
		if(monthlyEmail.equalsIgnoreCase("True")) {this.monthlyEmail = true;}
		else {this.monthlyEmail = false;}
		if(occasionalEmail.equalsIgnoreCase("True")) {this.occasionalEmail = true;}
		else {this.occasionalEmail = false;}
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
		
		//Select Checkboxes
		checkBoxSelector();
		
		//Select Gender
		if(gender.equalsIgnoreCase("Male")) {maleElement.click();}
		else {femaleElement.click();}
		
		//Select Country
		new Select(countryElement).selectByVisibleText(country);
		
	}
	
	private void checkBoxSelector() {
		if (weeklyEmail) {if(!weeklyElement.isSelected()) {
			weeklyElement.click();}
		}
		else {if(weeklyElement.isSelected()) {
			weeklyElement.click();}
		}
		
		if (monthlyEmail) {if(!monthlyElement.isSelected()) {
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
	
	public void assertCheck() {
		//Check for verification
		WebElement confirmationElement = driver.findElement(By.id("MainContent_lblTransactionResult"));
		actualText = confirmationElement.getText();
		if(actualText.equals(expectedText)) {
			successfullyCreatedAccount = true;
		}
		Assert.assertEquals(true, successfullyCreatedAccount);
	}
}