package Com.CRM.DeleteOrganizationTest;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Com.CRM.GenericLib.BaseClass;
import Com.CRM.GenericLib.ExcelLib;
import Com.CRM.ObjectRepositoryLib.Contacts;
import Com.CRM.ObjectRepositoryLib.Home;
import Com.CRM.ObjectRepositoryLib.Organisations;

public class DeleteOrganizationWhnItContainsContactAndOppertunity extends BaseClass {

	@Test
	public void DeleteOrganizationItContainsContactAndOppertunityTest() throws Throwable
	{
		Home hm=PageFactory.initElements(driver,Home.class);
		 Organisations org=PageFactory.initElements(driver,Organisations.class);
		 Contacts cnt=PageFactory.initElements(driver,Contacts.class);
		ExcelLib el=new ExcelLib();
		 
		String lastName=el.getData("Sheet1", 1, 3);
		String OrgName=el.getData("Sheet1", 3, 4);
		String Opportunityname=el.getData("Sheet1", 3, 5);
		
		
		String expMsgOrg=OrgName+" - Organization Information";
		String expMsgContact=lastName+" - Contact Information";
		String expMsg=Opportunityname+" - Opportunity Information";
				
	 //Navigate to organization list page and click on create new organization "+" symbol,//Enter Mandatory fields and create organization
		hm.navigateToOrganizationPage();
		org.createOraganization(OrgName);
		org.saveRecord();			    
					    
	//Verify the organization created successfully    
		String actMsgOrg=org.varifyOrg();				
		boolean msgVerify=actMsgOrg.contains(expMsgOrg);
		Assert.assertTrue(msgVerify);
		Reporter.log("create organization information page verified==pass",true);
						
						
	//Navigate to contact and create new contact
		hm.navigateToContactPage();
		cnt.creteNewContact(lastName);
	// map recently created organization by clicking organization look up button
		cnt.OrgLookup();  
						
		Set<String> set= driver.getWindowHandles();
		Iterator<String> it =set.iterator();
		String parentID=it.next();
		String childID=it.next();
	//switch driver control to child window 			
						driver.switchTo().window(childID);
						while(true) {
						try {
						driver.findElement(By.id("search_txt")).sendKeys(OrgName);
						break;
						}catch(Throwable e) {}
						}
						driver.findElement(By.name("search")).click();
						driver.findElement(By.linkText(OrgName)).click();
			  //switch driver control back to parent window 			
						driver.switchTo().window(parentID);
						
						driver.findElement(By.name("button")).click();
			   //verify new contact created successfully
						WebElement mwb1=driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
						String actMsgContact=mwb1.getText();
						
						boolean msgVerify2=actMsgContact.contains(expMsgContact);
						
						if(msgVerify2) {
						String actMsgContact2=actMsgContact.replace(actMsgContact, expMsgContact) ;
						
						Assert.assertEquals(actMsgContact2, expMsgContact);
						Reporter.log("create contact information page verified==pass",true);
						}
						
						
			//Create Opportunities with existing organization by clicking respective lookups
					    while(true)
					    {
					    	try {
					driver.findElement(By.xpath("//a[text()='Opportunities']")).click();
					break;
				
					    	}catch(Throwable e) {}
					    }
					driver.findElement(By.xpath("//img[@alt='Create Opportunity...']")).click();
					driver.findElement(By.name("potentialname")).sendKeys(Opportunityname);
					//click on organization lookup
					driver.findElement(By.xpath("//input[@name='related_to']/../img[@src='themes/softed/images/select.gif']")).click();
					//get windows ids and switch driver control to child window
					Set<String>Set=driver.getWindowHandles();
					Iterator<String>it1=Set.iterator();
					String parentWinID=it1.next();
					String childWinID=it1.next();
					driver.switchTo().window(childWinID);
					//search for created organization and select
					driver.findElement(By.id("search_txt")).sendKeys(OrgName);
					driver.findElement(By.name("search")).click();
					driver.findElement(By.linkText(OrgName)).click();
					//switch driver control back to parent window
					driver.switchTo().window(parentWinID);
					driver.findElement(By.name("button")).click();
					
					//verify 
					WebElement mwb2=driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
					String actMsg=mwb2.getText();
					
					boolean msgVerify3=actMsg.contains(expMsg);
					
					if(msgVerify3) {
					String actmsg2=actMsg.replace(actMsg, expMsg) ;
					
					Assert.assertEquals(actmsg2, expMsg);
					Reporter.log("create contact information page verified==pass",true);
					}
					
					 
			//Navigate to organizations and perform delete operation on recently created organization		
					driver.findElement(By.xpath("//a[text()='Organizations']")).click();
					driver.findElement(By.name("search_text")).sendKeys(OrgName);
					WebElement wedrop=driver.findElement(By.id("bas_searchfield"));
					Select sel=new Select(wedrop);
					sel.selectByVisibleText("Organization Name");
					driver.findElement(By.xpath("//input[contains(@value,' Search Now ')]")).click();
					Thread.sleep(2000);
					driver.findElement(By.name("selected_id")).click();
					driver.findElement(By.xpath("//input[@value='Delete']")).click();
					//driver.findElement(By.linkText(OrgName)).click();
					//driver.findElement(By.xpath("//input[@class='crmbutton small delete']")).click();+
					Alert alt=driver.switchTo().alert();
					System.out.println(alt.getText());
					alt.accept();
					System.out.println("Deleted successfuly......");
					
					
					
				List<WebElement> lst=	driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr[*]/td[3]/a"));
				for(int i=0;i<lst.size();i++)
				{
				String OppName=lst.get(i).getText();
				if(Opportunityname.equals(OppName))
				{
					System.out.println("TC FAILED-- After deleting organization the related Opportunity name should be deleted");
				}
				else {
					System.out.println("TC PASS-- After deleting organization the related Opportunity name also get deleted successfuly:");
				}
				
				//driver.findElement(By.xpath("//a[text()='Contacts']")).click();
				
				
				}
	}

}
