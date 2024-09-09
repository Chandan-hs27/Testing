package Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import util.DriverUtil;

public class DesignExtentReport {
	WebDriver driver;
	ExtentSparkReporter sparkReporter;
	ExtentReports reports;
	ExtentTest testLog;
	
	  @BeforeTest
	  public void beforeTest() {
		  driver = DriverUtil.getBrowserInstance("chrome");
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-ms");
		  String filepath = System.getProperty("user.dir")+"/extent-reports/"+sdf.format(new Date())+".html";
		  sparkReporter = new ExtentSparkReporter(filepath);
		  
		  reports = new ExtentReports();
		  reports.attachReporter(sparkReporter);
		  
		  sparkReporter.config().setDocumentTitle("Report on Testing");
		  //sparkReporter.config().setTheme(Theme.DARK);	  
	  }
  @Test(priority=1)
  public void Home() {
	  testLog=reports.createTest("Home page");
	  driver.get("https://www.mercurytravels.co.in/");
	  driver.manage().window().maximize();
	  System.out.println(driver.getTitle());
	  //Assert.assertEquals(driver.getTitle(),"mercury");
	  Assert.assertTrue(driver.getTitle().contains("Mercury Travels"));
	  }
  @Test(priority=2)
  public void DropDown() throws InterruptedException {
	  testLog=reports.createTest("Dropdown Test");
	  WebElement dDown = driver.findElement(By.name("theme"));
	  Select holiday = new Select(dDown);
	  Thread.sleep(1000);
	  //holiday.selectByVisibleText("Family");
	 // holiday.selectByValue("13");
	  holiday.selectByIndex(1);
	  WebElement dDown1 = driver.findElement(By.name("nights"));
	  Select stay = new Select(dDown1);
	  stay.selectByIndex(1);
	  JavascriptExecutor js=(JavascriptExecutor) driver;
	  js.executeScript("document.getElementById('dphh1').value='02/10/2024'");
	  
	  }
  
  @Test(priority=3)
  public void Destination() throws InterruptedException {
	  testLog=reports.createTest("Text box");
	  Thread.sleep(1000);
	  driver.findElement(By.id("holiday_category_id")).sendKeys("goa");
      
  }
  
  @Test(priority=4)
  public void failMethod() {
	  testLog=reports.createTest("failed method");
	  Assert.assertTrue(driver.getTitle().contains("z"));
	  }    
  
  @AfterMethod(enabled=false)
  public void afterMethod(ITestResult result) {
	if(result.getStatus()==ITestResult.SUCCESS)
	{
		testLog.log(Status.PASS,"This is in"+ result.getMethod().getMethodName());
	}
	else if(result.getStatus()==ITestResult.FAILURE)
	{
		testLog.log(Status.FAIL,"This is in"+ result.getMethod().getMethodName());
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destFile = System.getProperty("user.dir")+"/extent-reports/captures/"
		+result.getMethod().getMethodName()+".png";
		try {
			FileUtils.copyFile(srcFile,new File(destFile));
			testLog.addScreenCaptureFromPath(destFile);
			testLog.log(Status.FAIL, MarkupHelper.createLabel("This is a failed method", ExtentColor.RED));
			testLog.log(Status.FAIL, result.getThrowable().getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} 

  @AfterTest
  public void afterTest() {
	 // driver.close();
	  reports.flush();
  }

}
