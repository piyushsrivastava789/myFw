 package Com.CRM.ObjectRepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
 
public class Organisations {

	@FindBy(xpath="//img[@alt='Create Organization...']")WebElement CreateOrganizationBtn;
	
	@FindBy(name="accountname")WebElement OrgNameEdt;
	
	@FindBy(name="industry")WebElement OrgtypeDropdown;
	
	@FindBy(xpath="//input[@class='crmbutton small save']") WebElement saveButton;
	
	@FindBy(xpath="//span[@class='dvHeaderText']") WebElement actMsgWE;
	
	
	public void navigateToCreateOrganizationpage() {
		CreateOrganizationBtn.click();
	}
	
	public void createOraganization(String orgName)
	{
		OrgNameEdt.sendKeys(orgName);
		
	}
	public void createOraganizationWithOrgtype(String orgName)
	{
		OrgNameEdt.sendKeys(orgName);
		Select sel = new Select(OrgtypeDropdown);
		 sel.selectByVisibleText("Energy");
		
	}
	public String varifyOrg() {
		 
		String actMsg= actMsgWE.getText(); 
		return actMsg;
	}
	public void saveRecord() {
		 saveButton.click();
	 }
}
