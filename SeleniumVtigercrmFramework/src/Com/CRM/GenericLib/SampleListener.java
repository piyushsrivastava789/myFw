package Com.CRM.GenericLib;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;




public class SampleListener implements ITestListener {
	
	//public WebDriver driver;
   
   
   public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
	   EventFiringWebDriver ef=new EventFiringWebDriver(BaseClass.driver);
		
		File sfile= ef.getScreenshotAs(OutputType.FILE);
		
		File dfile=new File("./ScreenShot/Contacttest3.png");
		
		try {
			FileUtils.copyFile(sfile, dfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
   
   
   
@Override
public void onFinish(ITestContext arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void onStart(ITestContext arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	// TODO Auto-generated method stub
	
}


@Override
public void onTestSkipped(ITestResult arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void onTestStart(ITestResult arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void onTestSuccess(ITestResult arg0) {
	// TODO Auto-generated method stub
	
}
	
}
