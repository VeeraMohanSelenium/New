package frameworkLibrary;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

public class UtilityLibrary extends BaseClass
{
	public  boolean setText(By ele,String StrValue, WebDriver driver)
	{
		boolean stepstatus;
		try
		{
			driver.findElement(ele).sendKeys(StrValue);
			stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	
	public  boolean doubleClick(By ele,String StrValue, WebDriver driver)
	{
		boolean stepstatus;
		try
		{
			
			Actions a1 = new Actions(driver);
			a1.doubleClick(driver.findElement(ele));
			a1.build().perform();
			
			stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	public  boolean clickElement(By ele,WebDriver driver)
	{
		boolean stepstatus;
		try
		{
			driver.findElement(ele).click();
			stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	
	public  boolean rightClick(By ele,WebDriver driver)
	{
		boolean stepstatus;
		try
		{
			Actions a1 = new Actions(driver);
			a1.contextClick(driver.findElement(ele));
			a1.build().perform();
			stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	public  boolean clickElements(By ele,WebDriver driver)
	{
		boolean stepstatus;
		try
		{
			List<WebElement> allitems = driver.findElements(ele);
			for(WebElement element:allitems)
			{
				element.click();
			}
			stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	

	public  boolean setDateinCalender(By ele, String StrDate,WebDriver driver)
	{
		boolean stepstatus;
		try
		{
			driver.findElement(ele).click();
			driver.findElement(ele).sendKeys(StrDate);
			driver.findElement(ele).sendKeys(Keys.ESCAPE);
			Thread.sleep(1000);
			stepstatus=true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	public  boolean setOptionInSelect(By eleSelect, String eleOption,WebDriver driver)
	{
		boolean stepstatus = false;
		try
		{
			Select s1 = new Select(driver.findElement(eleSelect));
			s1.selectByVisibleText(eleOption);
			stepstatus= true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	public  void logEvent(boolean stepstatus,String PassMessage,String FailMessage)
	{
		if(stepstatus==true)
		{
			System.out.println("	<<PASS>> " + PassMessage);
			String scn =  config.test.addScreenCapture(getscreenshot());
			config.test.log(LogStatus.PASS, PassMessage + scn);
			config.logger.info("<<PASS>> " + PassMessage);
		}
		else
		{
			System.out.println("	<<FAIL>> " + FailMessage);
			config.test.log(LogStatus.FAIL, FailMessage);
			config.logger.info("<<FAIL>> " + FailMessage);
		}
		Assert.assertEquals(stepstatus, true);
	}
	
	public  boolean exists(By ele, WebDriver driver)
	{
		boolean stepstatus;
		try
		{
			driver.findElement(ele);
			stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	
	
	public  boolean quitbrowser(WebDriver driver)
	{
		boolean stepstatus;
		try
		{
			driver.quit();
			stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	
	public  WebDriver launchBrowser()
	{
		WebDriver Tempdriver = null;
		String BrowserName=getdata("Browser");
		switch(BrowserName.toLowerCase())
		{
			case "firefox":
			{
				try
				{
					Tempdriver = new FirefoxDriver();
				}
				catch(Exception e)
				{
					System.setProperty("webdriver.gecko.driver", "BrowserServers\\geckodriver.exe");
					Tempdriver = new FirefoxDriver();
				}
				break;
			}
			case "ie":
			case "internetexplorer":
			{
				System.setProperty("webdriver.ie.driver", "BrowserServers\\IEDriverServer.exe");
				Tempdriver = new InternetExplorerDriver();
				break;
			}
			case "chrome":
			{
				System.setProperty("webdriver.chrome.driver","BrowserServers\\chromedriver.exe");
				Tempdriver = new ChromeDriver();
				break;
			}
			default:
			{
				System.out.println("please Select the correct browser");
			}
		}
		return Tempdriver;
	}
	
	public  boolean launchApplication(String URL,WebDriver driver)
	{
		boolean stepstatus;
		try
		{
			driver.get(URL);
			stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	public  boolean JsclickElement(By ele,WebDriver driver)
	{
		boolean stepstatus;
		try
		{
			WebElement element = driver.findElement(ele);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", element);
			stepstatus = true;
		}
		catch(Exception e)
		{
			stepstatus = false;
		}
		return stepstatus;
	}
	public  String getDateAndTimeStamp()
	{
		DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
		String DateTimeStamp = dateTimeInstance.format(Calendar.getInstance().getTime());
		DateTimeStamp = DateTimeStamp.replace(",", "");
		DateTimeStamp = DateTimeStamp.replace(" ", "_");
		DateTimeStamp = DateTimeStamp.replace(":", "-");
		return DateTimeStamp;
	}
	
	public  boolean wait(int timeInSec)
	{
		boolean stepStatus;
		try
		{
			Thread.sleep(timeInSec*1000);
			stepStatus = true;
		}
		catch(Exception e)
		{
			stepStatus = false;
		}		
		return stepStatus;
	}
	
	public String getdata(String FieldName)
	{
		if(config.CurrentTestData.containsKey(FieldName))
		{
			return config.CurrentTestData.get(FieldName);
		}
		else
		{
			System.out.println("Data Not Found For '" + FieldName + "' Field");
			return null;
		}
	}
	
	public void putdata(String FieldName,String FieldValue)
	{
		if(config.CurrentTestData.containsKey(FieldName))
		{
			config.CurrentTestData.put(FieldName, FieldValue);
		}
		else
		{
			System.out.println("'" + FieldName + "' Field Name Not Found");
		}
	}
	
	public String getscreenshot()
	{
		String Dest = config.CurrentFolderPath + "\\Screenshots\\";
		DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
		String DateTimeStamp = dateTimeInstance.format(Calendar.getInstance().getTime());
		DateTimeStamp = DateTimeStamp.replace(",", "");
		DateTimeStamp = DateTimeStamp.replace(" ", "_");
		DateTimeStamp = DateTimeStamp.replace(":", "-");
		
		Dest = Dest + config.CurrentTestCasename + DateTimeStamp +".png";
		
		TakesScreenshot ts =(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destinationfile = new File(Dest);
		
		try
		{
			FileUtils.copyFile(source, destinationfile);
		}
		catch(Exception e)
		{
			System.out.println("<<Exception>>  Unable to take screenshot");
		}
		
		return Dest;
		 
	}
}
