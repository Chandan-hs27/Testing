package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import util.DriverUtil;
public class LaunchBrowserTest {
	WebDriver driver; 
	
  @Test
  public void TestDemoAppLaunch () throws InterruptedException {
	  driver.get("https://opensource-demo.orangehrmlive.com/");
	  Thread.sleep(2000);
	  driver.findElement(By.name("username")).sendKeys("Admin");
	  driver.findElement(By.name("password")).sendKeys("admin123");
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  System.out.println(driver.getCurrentUrl());
	  System.out.println(driver.getTitle());
	  Assert.assertEquals(driver.getTitle(), "OrangeHRM");
	  }
  @BeforeTest
  public void beforeTest() {
	  driver= DriverUtil.getBrowserInstance("chrome");
	  driver.manage().window().maximize();
  }}
 /*@AfterTest
  public void afterTest() {
	  driver.close();
  }}*/

