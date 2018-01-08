package frameworkLibrary;

import java.util.HashMap;
import org.apache.log4j.Logger;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class CommonVariables 
{
	private static CommonVariables single_instance = null;
	public HashMap<String,String> CurrentTestData;
	public String CurrentTestCasename;
	public String CurrentModuleName;
	public String DataFilesPath;
	public String CurrentFolderPath;
	public ExtentReports report;
	public ExtentTest test;
	public Logger logger; 
    public static CommonVariables getInstance()
    {
        if (single_instance == null) single_instance = new CommonVariables();
        return single_instance;
    }
}
