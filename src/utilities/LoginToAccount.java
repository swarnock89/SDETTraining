package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginToAccount {
	private WebDriver driver;
	private String email;
	private String password;

	public LoginToAccount(WebDriver driver, String email, String password) {
		this.driver = driver;
		this.email = email;
		this.password = password;
		
	}
	
	public String AccountLogin() {
		final String webURL = "http://sdettraining.com/trguitransactions/AccountManagement.aspx";
		
		//Navigate to login page
		driver.get(webURL);
		
		//Set WebElement"s
		WebElement emailElement = driver.findElement(By.name("ctl00$MainContent$txtUserName"));
		WebElement passwordElement = driver.findElement(By.name("ctl00$MainContent$txtPassword"));
		WebElement submitElement = driver.findElement(By.name("ctl00$MainContent$btnLogin"));
		
		//Login
		emailElement.sendKeys(email);
		passwordElement.sendKeys(password);
		submitElement.click();
		return confirmLogin();
	}
	
	private String confirmLogin() {
		WebElement confirmElement = driver.findElement(By.id("conf_message"));
		final String expectedMessage = "Logged in successfully";
		String actualMessage = confirmElement.getText();
		if(actualMessage.equals(expectedMessage)) {
			return actualMessage;
		}
		else {
			return "Test Failed";
		}
	}
}
