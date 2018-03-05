package Com.CRM.GenericLib;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import Com.CRM.ObjectRepositoryLib.Login;

public class BaseClass extends Login {
	public static WebDriver driver;
	
	
	ExcelLib el=new ExcelLib();
		
	@BeforeClass
	public void launchBrowser() throws Throwable
	{
		while(true)
		{
			try {
			driver = new FirefoxDriver();
			break;
			}catch(Throwable e) {}
		}
		
		
		
	}
	@BeforeMethod
	public void login() throws Throwable
	{
		
		Properties p=el.getdataLogin();
		String userName=p.getProperty("userName");
		String password=p.getProperty("password");
		String URL=p.getProperty("url");
		while(true) {
			try {
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//Navigate to Application login page
		driver.get(URL);
		break;
			}catch(Throwable t) {}
		}
		Login log=PageFactory.initElements(driver,Login.class);
		log.login(userName,password);
		
		//login to Application...
				//driver.findElement(By.name("user_name")).sendKeys(userName);
				//driver.findElement(By.name("user_password")).sendKeys(password);
				//driver.findElement(By.id("submitButton")).click();
				//break;
			//}catch(Throwable e) {}
			
		//}
	}
	@AfterMethod
	public void logout() 
	{
		while(true) {
			try {
		
		WebElement w1=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(w1);
		act.perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		break;
		}catch(Throwable e)
			{
			}
			}
	}
	@AfterClass
	public void closeBrowser()
	{
		while(true)
		{
		try {
		driver.close();
		break;
		
		}catch(Throwable e) {}
	}
	}
}
