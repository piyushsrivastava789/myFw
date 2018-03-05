package Com.CRM.GenericLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLib {
	public String getData(String sheetName,int rowNum,int col) throws Throwable
	{
		
	
	//step 1: Get the location of the >xlsx file
	FileInputStream path = new FileInputStream("C:\\Users\\User\\eclipse-workspace\\SeleniumVtigercrmFramework\\TestData\\TestCaseData.xlsx");
	
	//step 2: Open workbook in read mode
	Workbook wb = WorkbookFactory.create(path);
	
	//step 3 get control of the sheet 1
	Sheet sh = wb.getSheet(sheetName);
	
	//step 4: get the control of 1st row
	Row row=sh.getRow(rowNum);
	
	//step 5: Read the Data from cell
	String data=row.getCell(col).getStringCellValue();
	
	return data;
	}
	public Properties getdataLogin() throws Throwable
	{
		FileInputStream path = new FileInputStream("./TestData/Commandata.properties");
		
		
		Properties pro= new Properties();
		pro.load(path);
		return pro;
		
	}

}
