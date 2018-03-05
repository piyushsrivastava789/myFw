package Com.CRM.OrganizationTest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Com.CRM.GenericLib.BaseClass;
import Com.CRM.GenericLib.ExcelLib;
import Com.CRM.ObjectRepositoryLib.Home;
import Com.CRM.ObjectRepositoryLib.Organisations;

public class CreateOrganizationWithType extends BaseClass {

	@Test
	
	public void createOrganizationWithTypeTest() throws Throwable {
		 ExcelLib el=new ExcelLib();
		 String OrgName=el.getData("Sheet1", 5, 4);
		 
		 Home hm=PageFactory.initElements(driver,Home.class);
		 Organisations org=PageFactory.initElements(driver,Organisations.class);
	
		 String expMsg=OrgName+" - Organization Information";
				 
	//Navigate to organization list page 
		 hm.navigateToOrganizationPage();
	//click on create new organization "+" symbol, Enter Mandatory fields and create organization with organization Type			
		 org.createOraganizationWithOrgtype(OrgName);		 
	     
		 org.saveRecord(); 
	
		 //Verify the organization created successfully 			 
					
					String actMsg=org.varifyOrg();
					boolean msgVerify=actMsg.contains(expMsg);
					
					Assert.assertTrue(msgVerify);
					Reporter.log("create organization information page verified==pass",true);
					
		
	}

}
