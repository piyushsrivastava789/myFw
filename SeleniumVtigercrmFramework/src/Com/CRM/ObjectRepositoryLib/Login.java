package Com.CRM.ObjectRepositoryLib;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {
 
	@FindBy(name="user_name") WebElement userNameEdt;
	
	@FindBy(name="user_password") WebElement passwordEdt;
	
	@FindBy(id="submitButton") WebElement loginButton;
	
	public void login(String userName, String password )
	{
		userNameEdt.sendKeys(userName);
		passwordEdt.sendKeys(password);
		loginButton.click();
	}
	
}
