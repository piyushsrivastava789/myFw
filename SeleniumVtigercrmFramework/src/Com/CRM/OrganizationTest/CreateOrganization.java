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
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Com.CRM.GenericLib.BaseClass;
import Com.CRM.GenericLib.ExcelLib;
import Com.CRM.ObjectRepositoryLib.Home;
import Com.CRM.ObjectRepositoryLib.Organisations;

public class CreateOrganization extends BaseClass {
@Test
	public void createOrganizationTest()  throws Throwable {
	Home hm=PageFactory.initElements(driver,Home.class);
	 Organisations org=PageFactory.initElements(driver,Organisations.class);
		ExcelLib el=new ExcelLib();
		String OrgName=el.getData("Sheet1", 4, 4);
	    String expMsg=OrgName+" - Organization Information";
		
    //Navigate to organization list page and click on create new organization "+" symbol , Enter Mandatory fields and create organization
		   hm.navigateToOrganizationPage();
		   org.createOraganization(OrgName);
		   org.saveRecord();
	//Verify the organization created successfully    
		    String actMsg=org.varifyOrg();
		    boolean msgVerify=actMsg.contains(expMsg);
			
			Assert.assertTrue(msgVerify);
			Reporter.log("create organization information page verified==pass",true);
			}
			
	}


