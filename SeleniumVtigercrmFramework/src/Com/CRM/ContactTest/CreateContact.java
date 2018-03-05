package Com.CRM.ContactTest;


import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Com.CRM.GenericLib.BaseClass;
import Com.CRM.GenericLib.ExcelLib;

import Com.CRM.ObjectRepositoryLib.Contacts;
import Com.CRM.ObjectRepositoryLib.Home;
@Listeners(Com.CRM.GenericLib.SampleListener.class)
public class CreateContact extends BaseClass{

	@Test
	public void createContactTest() throws Throwable{
		ExcelLib el=new ExcelLib();
		
		 
		String lastName=el.getData("Sheet1", 1, 3);
		String expMsg=lastName+" - Contact Information";
		
		
		Home hm=PageFactory.initElements(driver,Home.class);
		hm.navigateToContactPage();
		
		Contacts cnt=PageFactory.initElements(driver,Contacts.class);
		//Navigate to contact and create new contact
		cnt.navigateTOCreateNewContactPage();
		cnt.creteNewContact(lastName);
		cnt.saveRecord();
	
		
		//verify new contact created successfully
		String actMsg=cnt.varifyContact();
		boolean msgVerify=actMsg.contains(expMsg);
		Assert.assertTrue(msgVerify);
		Reporter.log("create contact information page verified==pass",true);
		
	}

}
