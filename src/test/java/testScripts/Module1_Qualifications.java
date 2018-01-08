package testScripts;

import org.testng.annotations.Test;

public class Module1_Qualifications extends ApplicationLibrary
{
  @Test
  public void TC101_AddQualificationDetails_WorkExperience() 
  {
	  Login();
	  
	  navigateToQualificationsPage();
	  
	  addWorkExperienceDetailsAndVerify();
	  
	  logOutAndQuitBrowser();
  }
  
  @Test
  public void TC102_DeleteQualificationDetails_WorkExperience()
  {
	  Login();
	  
	  navigateToQualificationsPage();

	  DeleteSingleWorkExperienceRecord("CTS");
	  
	  
//	  DeleteAllWorkExperienceRecords();
//	  

//	  
	  logOutAndQuitBrowser();
  }
  
  @Test
  public void TC103_AddQualificationDetails_Education()
  {
	  Login();
	  navigateToQualificationsPage();
	  
	  
	  
	  
	
  }
  
  
  
  
}
