package objectRepository;

import org.openqa.selenium.By;

public class Pg301_Qualifications 
{
	public static By Btn_WorkExperience_Add = By.xpath("//input[@id='addWorkExperience']");
	public static By Edt_WorkExperience_Company = By.xpath("//input[@id='experience_employer']");
	public static By Edt_WorkExperience_Jobtitle = By.xpath("//input[@id='experience_jobtitle']");
	public static By Edt_WorkExperience_Fromdate = By.xpath("//input[@id='experience_from_date']");
	public static By Edt_WorkExperience_Todate = By.xpath("//input[@id='experience_to_date']");
	public static By Edt_WorkExperience_Comments = By.id("experience_comments");
	public static By Btn_WorkExperience_Save = By.xpath("//input[@id='btnWorkExpSave']");
	
	public static By Chk_WorkExperience_DeleteAll = By.xpath("//input[@id='workCheckAll']");
	public static By Btn_WorkExperience_Delete = By.xpath("//input[@id='delWorkExperience']");
	
	public static By Msg_WorkExperience_Savedsuccessfully = By.xpath("//div[contains(text(),'Successfully Saved')]");
	public static By Msg_WorkExperience_Deletedsuccessfully = By.xpath("//div[contains(text(),'Successfully Deleted')]");
}
