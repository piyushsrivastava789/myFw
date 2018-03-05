package Com.CRM.ContactTest;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Com.CRM.GenericLib.BaseClass;
import Com.CRM.GenericLib.ExcelLib;
import Com.CRM.ObjectRepositoryLib.Contacts;
import Com.CRM.ObjectRepositoryLib.Home;

public class CreateContactWithImage extends BaseClass{

	@Test
	public void createContactWithImageTest()throws Throwable {
		// TODO Auto-generated method stub

		ExcelLib el=new ExcelLib();
		String imagePath=el.getData("Sheet1", 6, 7);
		String lastName=el.getData("Sheet1", 1, 3);
		
		//create a new contact with image
		Home hm=PageFactory.initElements(driver,Home.class);
		hm.navigateToContactPage();
		
		Contacts cnt=PageFactory.initElements(driver,Contacts.class);
		cnt.navigateTOCreateNewContactPage();  
		cnt.creteNewContactImageFile(lastName);
		//upload file/image
		StringSelection path=new StringSelection(imagePath);
		Toolkit tool=Toolkit.getDefaultToolkit();
		Clipboard mouse=tool.getSystemClipboard();
		mouse.setContents(path,null);
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		cnt.saveRecord();
		//verify image/file is displayed or not
		Boolean b=cnt.verifyImage();
		Assert.assertTrue(b);
		Reporter.log("Image is displayed==pass");

	}

		
	}


