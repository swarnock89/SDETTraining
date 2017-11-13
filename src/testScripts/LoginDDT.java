package testScripts;

import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDDT {
  
	WebDriver driver;
	WebElement emailElement, passwordElement, loginElement;
	String email, password;
	boolean loggedIn = false;
	
	
	@Test(dataProvider = "getData")
	public void loginTest(String name, String email, String phone, String password, String gender, String country, 
			String weeklyEmail, String monthlyEmail, String occasionalEmail) {
		
		//Set Class Variables
		setClassVariables(email, password);
		
		//Define the WebElements we will use
		defineWebElements();
		
		//Input data
		inputData();
		
		//Submit the Login
		submitLogin();
		
		//Test Assert
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
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.close();
	}
	
	public void defineWebElements() {
		emailElement = driver.findElement(By.name("ctl00$MainContent$txtUserName"));
		passwordElement = driver.findElement(By.name("ctl00$MainContent$txtPassword"));
		loginElement = driver.findElement(By.name("ctl00$MainContent$btnLogin"));
	}
	
	public void setClassVariables(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public void inputData() {
		emailElement.sendKeys(email);
		passwordElement.sendKeys(password);
	}
	
	public void submitLogin() {
		loginElement.click();
	}
	
	public void assertTest() {
		final String expectedText = "Logged in successfully";
		WebElement confirmElement = driver.findElement(By.id("conf_message"));
		String actualText = confirmElement.getText();
		if(actualText.equals(expectedText)) {
			loggedIn = true;
		}
		Assert.assertEquals(loggedIn, true);
	}
}
