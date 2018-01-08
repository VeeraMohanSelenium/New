package objectRepository;

import org.openqa.selenium.By;

/**
 * This is the login page of HRM application
 * @author Ankit
 *
 */
public class Pg101_Login 
{
	public static By Edt_UserName = By.id("txtUsername");
	public static By Edt_Password = By.xpath("//input[@id='txtPassword']");
	public static By Btn_Login = By.xpath("//input[@id='btnLogin']");
}
