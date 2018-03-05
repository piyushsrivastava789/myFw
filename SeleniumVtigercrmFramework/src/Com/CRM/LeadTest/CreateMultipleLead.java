package Com.CRM.LeadTest;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import Com.CRM.GenericLib.ExcelLib;

public class CreateMultipleLead {
@Test

	public void createMultipleLeadTest()throws Throwable {
	int rowNum=7; int col1=2; int col2=3;
		ExcelLib el=new ExcelLib();
		 Properties p=el.getdataLogin();
		 String userName=p.getProperty("userName");
			String password=p.getProperty("password");
			String URL=p.getProperty("url");
			
			
			//Launch Browser..	
			WebDriver driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		//Navigate to application login page
			driver.get(URL);
			
			//login to application
			driver.findElement(By.name("user_name")).sendKeys(userName);
			driver.findElement(By.name("user_password")).sendKeys(password);
			driver.findElement(By.id("submitButton")).click();
			for(int i=1;i<=6;i++)
			{
			String lastName=el.getData("Sheet1", rowNum, col2);
			String ComponyName=el.getData("Sheet1", rowNum, col1);
			
			driver.findElement(By.xpath("//a[text()='Leads']")).click();
			driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
			driver.findElement(By.name("lastname")).sendKeys(lastName);
			driver.findElement(By.name("company")).sendKeys(ComponyName);
			driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
			driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
			rowNum++;
			}
			driver.close();

	}

}
