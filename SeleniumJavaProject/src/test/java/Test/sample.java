package Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import util.DriverUtil;
import org.testng.annotations.DataProvider;

	public class sample {
	  WebDriver driver;
	  @Test(dataProvider = "Demo")
	  public void m1(String a, String b) {
			System.out.println(a+"   "+b);
		driver= DriverUtil.getBrowserInstance("chrome");
		driver.get("https://lkmdemoaut.accenture.com/TestMeApp/login.htm");}
       
	  
        @DataProvider
	  public Object[][] Demo() {
		  Object[][] data= {
			 { "user01", "pass123" },{ "user02", "pass345" },{ "user03", "pass567" }};
	      return data;
	    }}
