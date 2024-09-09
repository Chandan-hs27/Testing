package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverUtil {
	
	static public  WebDriver getBrowserInstance(String browserName) {
		if(browserName.equals("chrome")) {
			 System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
			  return new ChromeDriver();}
		else if(browserName.equals("msedge")) {
			 System.setProperty("webdriver.msedge.driver", "src/test/resources/drivers/msedgedriver.exe");
			  return new EdgeDriver();}
		else if(browserName.equals("firefox")) {
			 System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
			  return new FirefoxDriver();}
		else {return null;}
		}
	}
