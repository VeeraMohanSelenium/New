package testScripts;

import org.openqa.selenium.By;
import frameworkLibrary.UtilityLibrary;
import objectRepository.Pg101_Login;
import objectRepository.Pg102_Home;
import objectRepository.Pg201_MyInfo;
import objectRepository.Pg301_Qualifications;

public class ApplicationLibrary extends UtilityLibrary
{
	public void Login()
	{
		 driver = launchBrowser();
		  
		  stepstatus = launchApplication("http://www.testingmasters.com/hrm/", driver);
		  logEvent(stepstatus, "Application is launched successfully", "Unable to launchapplication");
		  
		  stepstatus = setText(Pg101_Login.Edt_UserName, getdata("UserName"), driver);
		  logEvent(stepstatus, "Able to enter username", "Unable to enter username");
		  
		  stepstatus = setText(Pg101_Login.Edt_Password, getdata("Password"), driver);
		  logEvent(stepstatus, "Able to enter password", "Unable to enter password");
		  
		  stepstatus = clickElement(Pg101_Login.Btn_Login, driver);
		  logEvent(stepstatus, "Able to click on login", "Unable to click on login");
		  
		  stepstatus = exists(Pg102_Home.Lnk_MyInfo, driver);
		  logEvent(stepstatus, "Login is successful", "Login is not successful");
	}
	
	
	public void navigateToQualificationsPage()
	{
		stepstatus = clickElement(Pg102_Home.Lnk_MyInfo, driver);
		  logEvent(stepstatus, "Able to click on MyInfo", "Unable to click on MyInfo");
		  
		  stepstatus = JsclickElement(Pg201_MyInfo.Lnk_Qualifications, driver);
		  logEvent(stepstatus, "Able to click on Qualifications", "Unable to click on Qualifications");
		  
	}
	
	public void addWorkExperienceDetailsAndVerify()
	{
		stepstatus = clickElement(Pg301_Qualifications.Btn_WorkExperience_Add, driver);
		  logEvent(stepstatus, "Able to click on WorkExperience Add Button", "Unable to click on WorkExperience Add Button");
		  
		  
		  stepstatus = setText(Pg301_Qualifications.Edt_WorkExperience_Company, getdata("company"), driver);
		  logEvent(stepstatus, "Able to enter Company Name", "Unable to enter Company Name");
		  
		  
		  stepstatus = setText(Pg301_Qualifications.Edt_WorkExperience_Jobtitle, getdata("JobTitle"), driver);
		  logEvent(stepstatus, "Able to enter Jobtitle", "Unable to enter Jobtitle");
		 
		  stepstatus = setDateinCalender(Pg301_Qualifications.Edt_WorkExperience_Fromdate, getdata("FromDate"), driver);
		  logEvent(stepstatus, "Able to enter Fromdate", "Unable to enter Fromdate");
	
		  stepstatus = setDateinCalender(Pg301_Qualifications.Edt_WorkExperience_Todate, getdata("ToDate"), driver);
		  logEvent(stepstatus, "Able to enter Todate", "Unable to enter Todate");
		 
		  stepstatus = setText(Pg301_Qualifications.Edt_WorkExperience_Comments, getdata("Comments"), driver);
		  logEvent(stepstatus, "Able to enter Comments", "Unable to enter Comments");
		  
		  stepstatus = clickElement(Pg301_Qualifications.Btn_WorkExperience_Save, driver);
		  logEvent(stepstatus, "Able to click on WorkExperience Save Button", "Unable to click on WorkExperience Save Button");
		  
		  stepstatus = exists(Pg301_Qualifications.Msg_WorkExperience_Savedsuccessfully, driver);
		  logEvent(stepstatus, "Record Added Successfully Message is displayed", "Record Added Successfully Message is not displayed");
		  
	}
	
	public void DeleteAllWorkExperienceRecords()
	{
		 stepstatus = clickElement(Pg301_Qualifications.Chk_WorkExperience_DeleteAll, driver);
		  logEvent(stepstatus, "Able to click On checkbox", "Unable to click On checkbox");
		  
		  stepstatus = clickElement(Pg301_Qualifications.Btn_WorkExperience_Delete, driver);
		  logEvent(stepstatus, "Able to click On Delete Button", "Unable to click On Delete BUtton");
		  
		  stepstatus = exists(Pg301_Qualifications.Msg_WorkExperience_Deletedsuccessfully, driver);
		  logEvent(stepstatus, "Records Deleted Successfully Message is displayed", "Records Deleted Successfully Message is not displayed");
	}
	
	public void DeleteSingleWorkExperienceRecord(String RecordName)
	{
		String SingleRecordXPath = "//a[text()='" + RecordName +"']/../preceding-sibling::td/input[@type='checkbox']";
		
		stepstatus = clickElement(By.xpath(SingleRecordXPath), driver);
		logEvent(stepstatus, "Able to click On checkbox", "Unable to click On checkbox");
		
		stepstatus = clickElement(Pg301_Qualifications.Btn_WorkExperience_Delete, driver);
		  logEvent(stepstatus, "Able to click On Delete Button", "Unable to click On Delete BUtton");
		  
		  stepstatus = exists(Pg301_Qualifications.Msg_WorkExperience_Deletedsuccessfully, driver);
		  logEvent(stepstatus, "Records Deleted Successfully Message is displayed", "Records Deleted Successfully Message is not displayed");
	}
	
	public void DeleteAllMatchingWorkExperienceRecords(String RecordName)
	{
		String RecordXPath = "//a[text()='" + RecordName +"']/../preceding-sibling::td/input[@type='checkbox']";
		
		stepstatus = clickElements(By.xpath(RecordXPath), driver);
		logEvent(stepstatus, "Able to click On Delete Button", "Unable to click On Delete BUtton");
		
		stepstatus = clickElement(Pg301_Qualifications.Btn_WorkExperience_Delete, driver);
		logEvent(stepstatus, "Able to click On Delete Button", "Unable to click On Delete BUtton");
		  
		 stepstatus = exists(Pg301_Qualifications.Msg_WorkExperience_Deletedsuccessfully, driver);
		 logEvent(stepstatus, "Records Deleted Successfully Message is displayed", "Records Deleted Successfully Message is not displayed");
	}
	
	public void logOutAndQuitBrowser()
	{
		wait(2);
		  stepstatus = JsclickElement(Pg102_Home.Lnk_LogoutBar, driver);
		  wait(2);
		  stepstatus = JsclickElement(Pg102_Home.Lnk_Logout, driver);
		  logEvent(stepstatus, "Able to click on Logout", "Unable to click on Logout");
		  
		  quitbrowser(driver);
	}
}
