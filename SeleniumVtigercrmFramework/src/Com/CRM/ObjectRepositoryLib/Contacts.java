package Com.CRM.ObjectRepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Contacts {

 
 @FindBy(xpath="//img[@alt='Create Contact...']") WebElement createNewContact;
 
 @FindBy(name="lastname") WebElement lastNameEdt;
 
 @FindBy(name="button") WebElement saveButton;
 
 @FindBy(xpath="//span[@class='dvHeaderText']") WebElement actMsgWE;
 
 @FindBy(name="imagename")WebElement imageFileAttatch;
 
 @FindBy(xpath="//img[@title='Contact Image']") WebElement imageVerify;
 
 @FindBy(xpath="//input[@name='account_id']/following-sibling::img[@title='Select']")
 WebElement orgLookup;
 
 public void navigateTOCreateNewContactPage() {
	 createNewContact.click();
 }
 public void creteNewContact(String lastname) {
	 lastNameEdt.sendKeys(lastname);
	 
 }
 public void creteNewContactImageFile(String lastname ) {
	 lastNameEdt.sendKeys(lastname);
	 imageFileAttatch.click();
	 
 }
 public void creteNewContactWithOrganization(String lastname) {
	 lastNameEdt.sendKeys(lastname);
	 
	 
 }
 public void OrgLookup() {
	 orgLookup.click();
 }
 public void saveRecord() {
	 saveButton.click();
 }
 public String varifyContact() {
	 
	String actMsg= actMsgWE.getText(); 
	return actMsg;
}
 public boolean verifyImage() {
	boolean bt= imageVerify.isDisplayed();
	return bt;
	 
 }
}
