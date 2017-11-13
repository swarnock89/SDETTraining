package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {

	WebDriver driver;
	public static WebDriver open(String browserType) {
		if(browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\shwar\\Documents\\Automation\\Software\\chromedriver.exe");
			return new ChromeDriver();
		}
		else if(browserType.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\shwar\\Documents\\Automation\\Software\\geckodriver.exe");
			return new FirefoxDriver();
		}
		else {
			System.setProperty("webdriver.ie.driver", "C:\\Users\\shwar\\Documents\\Automation\\Software\\IEDriverServer.exe");
			return new InternetExplorerDriver();
		}
	}
}
