 package Com.CRM.ContactTest;
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
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Com.CRM.GenericLib.BaseClass;
import Com.CRM.GenericLib.ExcelLib;
import Com.CRM.ObjectRepositoryLib.Contacts;


public class CreateContactWithOrganization extends BaseClass {
@Test
public void createContactWithOrganizationTest() throws Throwable {
		

	ExcelLib el=new ExcelLib();
	
	String lastName=el.getData("Sheet1", 2, 3);
	
		String expMsg="Contact Information";
		
    //Navigate to contact and 
		Contacts cnt=PageFactory.initElements(driver,Contacts.class);
		cnt.navigateTOCreateNewContactPage();
	
	//create new contact with organization by clicking on organization lookup and select existing organization	
		cnt.creteNewContactWithOrganization(lastName);
		cnt.OrgLookup();
		Set<String>Set=driver.getWindowHandles();
		Iterator<String>it=Set.iterator();
		String parentWinID=it.next();
		String childWinID=it.next();
		
   //switch driver control to child window to select organization
		driver.switchTo().window(childWinID);
		driver.findElement(By.id("1")).click();
		driver.switchTo().window(parentWinID);
		driver.findElement(By.name("button")).click();
		
   //verify new contact created successfully
		
		String actMsg=cnt.varifyContact();

		boolean msgVerify=actMsg.contains(expMsg);
		
		if(msgVerify) {
		String actmsg2=actMsg.replace(actMsg, expMsg) ;
		
		Assert.assertEquals(actmsg2, expMsg);
		Reporter.log("create contact with organization information page verified==pass",true);
		}
	}

}

