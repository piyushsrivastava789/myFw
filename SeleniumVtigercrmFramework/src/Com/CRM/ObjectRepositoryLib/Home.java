package Com.CRM.ObjectRepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home {

	 @FindBy(xpath="//a[text()='Contacts']") WebElement contactLink;
	 
	 @FindBy(xpath="//a[text()='Organizations']") WebElement organizationLink;
	 
	 @FindBy(xpath="//a[text()='Opportunities']") WebElement opportunityLink;
	 
	 @FindBy(xpath="//a[text()='Leads']") WebElement leadLink;
	 
	 @FindBy(xpath="//a[text()='Campaigns']") WebElement campaignLink;
	 
	 @FindBy(xpath="//a[text()='Quotes']") WebElement quoteLink;
	 
	 @FindBy(xpath="//a[text()='Products']") WebElement productLink;
	 
	 public void navigateToContactPage() {
		 contactLink.click();
	 }
	 
	 public void navigateToOrganizationPage() {
		 organizationLink.click();
	 }
	 
	 public void navigateToOpportunityPage() {
		 opportunityLink.click();
	 }
	 
	 public void navigateToLeadPage() {
		 leadLink.click();
	 }
	 
	 public void navigateToCampaignPage() {
		 campaignLink.click();
	 }
	 
}
