package frameworkLibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;

public class BaseClass 
{
	CommonVariables config = CommonVariables.getInstance();
	public boolean stepstatus;
	public WebDriver driver;
	 @BeforeSuite
	 public void BeforeSuite()
	 {
	    // get current folder path
		config.CurrentFolderPath = System.getProperty("user.dir") + "\\Results";

		// create folder with date and time stamp
		DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
		String DateTimeStamp = dateTimeInstance.format(Calendar.getInstance().getTime());
		DateTimeStamp = DateTimeStamp.replace(",", "");
		DateTimeStamp = DateTimeStamp.replace(" ", "_");
		DateTimeStamp = DateTimeStamp.replace(":", "-");
		config.CurrentFolderPath = config.CurrentFolderPath + "\\" + DateTimeStamp;
		File dir = new File(config.CurrentFolderPath);
		dir.mkdir();
		
		File dir1 = new File(config.CurrentFolderPath + "\\Screenshots");
		dir1.mkdir();
		
		
		PropertyConfigurator.configure("Config//Log4j.properties");
		
		config.report = new ExtentReports(config.CurrentFolderPath + "\\Summary.html", true);
	 
		config.logger=Logger.getLogger(":::::Start Execution::::");
		config.logger.info("");
		System.out.println(":::::::::::::::::Start Execution:::::::::::::::::");
	 }
	 
	 
	@BeforeMethod
	public void BeforeMethod(Method m1) 
	{
		config.logger=Logger.getLogger(":::::Start TestCase::::");
		config.logger.info("");
		
		
		if (config.CurrentTestData != null) config.CurrentTestData.clear();
		config.DataFilesPath = System.getProperty("user.dir") + "\\DataFiles";
		config.CurrentTestCasename = m1.getName();
		config.CurrentModuleName = m1.getDeclaringClass().getSimpleName();
		config.CurrentTestData = elib_loadData(config.CurrentTestCasename, config.CurrentModuleName);
		
		config.test = config.report.startTest(config.CurrentTestCasename);
		config.logger=Logger.getLogger(config.CurrentTestCasename);
		
		System.out.println(":::::::::::::::::Start TestCase----" + config.CurrentTestCasename + ":::::::::::::::::");
	}
	
	@AfterMethod
	public void AfterMethod()
	{
		config.logger=Logger.getLogger(":::::End TestCase::::");
		config.logger.info("");
		System.out.println(":::::::::::::::::End TestCase----" + config.CurrentTestCasename + ":::::::::::::::::");
		elib_UploadData(config.CurrentTestCasename, config.CurrentModuleName);
		config.report.endTest(config.test);
	}
	
	@AfterSuite
	public void AfterSuite() 
	{
		config.report.close();
		config.logger=Logger.getLogger(":::::End Execution::::");
		config.logger.info("");
		System.out.println(":::::::::::::::::End Execution:::::::::::::::::");
		
		WebDriver driver = new FirefoxDriver();
		driver.get(config.CurrentFolderPath + "\\Summary.html");
	}
	
	
	public HashMap<String,String> elib_loadData(String TestCaseName,String ModuleName)
	{
		HashMap<String,String> TestData = new HashMap<String,String>();
		try
		{		
			String FilePath = config.DataFilesPath + "\\" + ModuleName + ".xlsx";
			File f1 = new File(FilePath);
			FileInputStream fis = new FileInputStream(f1);
			XSSFWorkbook wb1 = new XSSFWorkbook(fis);
			
			XSSFSheet ws1 = wb1.getSheet("TestData");
			
			int rowcount = ws1.getLastRowNum();
			for(int r = 0;r<=rowcount;r++)
			{
				if(ws1.getRow(r).getCell(0)!=null)
				{
					String Excel_TestCaseName = ws1.getRow(r).getCell(0).getStringCellValue();
					if(Excel_TestCaseName.equalsIgnoreCase(TestCaseName))
					{
						int datarow = r + 1;
						int valuerow = r + 2;
						int cellcount = ws1.getRow(datarow).getLastCellNum();
						for(int c = 0;c<cellcount;c++)
						{
							String ExcelFieldName="";
							String ExcelFieldValue="";
							if(ws1.getRow(datarow).getCell(c)!=null)
							ExcelFieldName = ws1.getRow(datarow).getCell(c).getStringCellValue();
							if(ws1.getRow(valuerow).getCell(c)!=null)
							ExcelFieldValue = ws1.getRow(valuerow).getCell(c).getStringCellValue();
							if(ExcelFieldName.length()>1)
							TestData.put(ExcelFieldName, ExcelFieldValue);
						}
						break;
					}
				}
			}
			fis.close();
			wb1.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while reading data of '" + TestCaseName + "'  TestCase and '" + ModuleName + "' Module");
		}
		return TestData;
	}
	
	public void elib_UploadData(String TestCaseName,String ModuleName)
	{
		try
		{		
			String FilePath = config.DataFilesPath + "\\" + ModuleName + ".xlsx";
			File f1 = new File(FilePath);
			FileInputStream fis = new FileInputStream(f1);
			XSSFWorkbook wb1 = new XSSFWorkbook(fis);
			XSSFSheet ws1 = wb1.getSheet("TestData");
			int rowcount = ws1.getLastRowNum();
			for(int r = 0;r<rowcount;r++)
			{
				try
				{
					if(ws1.getRow(r).getCell(0)!=null)
					{
						String Excel_TestCaseName = ws1.getRow(r).getCell(0).getStringCellValue();
						if(Excel_TestCaseName.equalsIgnoreCase(TestCaseName))
						{
							int datarow = r + 1;
							int valuerow = r + 2;
							int cellcount = ws1.getRow(datarow).getLastCellNum();
							for(int c = 0;c<cellcount;c++)
							{
								String ExcelFieldName="";
								if(ws1.getRow(datarow).getCell(c)!=null)
								ExcelFieldName = ws1.getRow(datarow).getCell(c).getStringCellValue();
								if(ws1.getRow(valuerow).getCell(c)==null)
								{
									ws1.getRow(valuerow).createCell(c);
								}
								if(ExcelFieldName.length()>1 && config.CurrentTestData.containsKey(ExcelFieldName))
								{
									ws1.getRow(valuerow).getCell(c).setCellValue(config.CurrentTestData.get(ExcelFieldName));
								}
							}
						}
					}
				}
				catch(Exception e)
				{
					
				}
			}
			fis.close();
			
			FileOutputStream fos = new FileOutputStream(f1);
			wb1.write(fos);
			
			fos.close();
			wb1.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception occured while writing data of '" + TestCaseName + "'  TestCase and '" + ModuleName + "' Module");
		}
	}
	
}
