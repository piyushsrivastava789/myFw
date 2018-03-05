package Com.CMR.EndToEndTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Com.CRM.GenericLib.BaseClass;
import Com.CRM.GenericLib.ExcelLib;

public class CreateOpertunityUsingOrg_Campaign extends BaseClass {
@Test
public void CreateOpertunityUsingOrgCampaignTest() throws Throwable
	{
		ExcelLib el=new ExcelLib();
		Properties p=el.getdataLogin();
		 
		String campaignName=el.getData("Sheet1", 3, 6);
		String OrgName=el.getData("Sheet1", 3, 4);
		String potentialname=el.getData("Sheet1", 3, 5);
		String expMsg="Opportunity Information";
		
		//Navigate to more Drop-down menu and click on campaign link
		driver.findElement(By.linkText("More")).click();
		driver.findElement(By.name("Campaigns")).click();
		
		//create new campaign..
		driver.findElement(By.xpath("//img[@alt='Create Campaign...']")).click();
		driver.findElement(By.name("campaignname")).sendKeys(campaignName);
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
		
		//Navigate to to organization and new Create organization..
		 driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		 driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	  
		//Enter Mandatory fields and create organization	   
		    driver.findElement(By.name("accountname")).sendKeys(OrgName);
		    driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		//Create Opportunities with existing campaign and organization by clicking respective lookups
		    while(true)
		    {
		    	try {
		driver.findElement(By.xpath("//a[text()='Opportunities']")).click();
		break;
		    	}catch(Throwable e) {}
		    }
		driver.findElement(By.xpath("//img[@alt='Create Opportunity...']")).click();
		driver.findElement(By.name("potentialname")).sendKeys(potentialname);
		
		//click on organization lookup
		driver.findElement(By.xpath("//input[@name='related_to']/../img[@src='themes/softed/images/select.gif']")).click();
		
		//get windows ids and switch driver control to child window
		Set<String>Set=driver.getWindowHandles();
		Iterator<String>it=Set.iterator();
		String parentWinID=it.next();
		String childWinID=it.next();
		driver.switchTo().window(childWinID);
		
		//search for created organization and select
		driver.findElement(By.id("search_txt")).sendKeys(OrgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.id("1")).click();
		
		//switch driver control back to parent window
		driver.switchTo().window(parentWinID);
		driver.findElement(By.name("button")).click();
		
		//verify 
		WebElement mwb=driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		String actMsg=mwb.getText();
		System.out.println(actMsg);

		if(actMsg.contains(expMsg))
		{
			System.out.println("create organization information page verified==pass");
		}
		else
		{
			System.out.println("create organization information page is not verified==fail");

		   }
		

	}

}
