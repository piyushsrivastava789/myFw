package Com.CMR.EndToEndTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Voda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.vodafone.in");
		driver.findElement(By.xpath("//a[@class='closeBtn mv_closeBtn']")).click();
		driver.findElement(By.xpath("//div[@class='selectWrap']/select[contains(@name,'ddlCircle')]")).click();
	}

}
