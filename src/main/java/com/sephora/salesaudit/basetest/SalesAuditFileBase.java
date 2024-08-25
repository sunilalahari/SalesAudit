
package com.sephora.salesaudit.basetest;


import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import com.google.common.base.Function;
import com.jcraft.jsch.Session;
import com.relevantcodes.extentreports.ExtentReports;
import com.sephora.salesaudit.utils.FileUtility;
import com.sephora.salesaudit.utils.PropertyReader;


@Listeners({ com.sephora.salesaudit.listeners.TestListner.class })
public class SalesAuditFileBase extends SalesAuditConstants{
	int count=1;
	@Parameters({"configFile","documentTitle","reportName"})
	@BeforeSuite(alwaysRun = true)
	public void initSuite(String config,String documentTitle, String reportName  ) {
		printLog("configFile----------->"+config);
		railIDValue=System.getProperty("testRailID");
		s_Assert=new SoftAssert();
		prop=PropertyReader.getPropeties(config);
		util= new FileUtility();
		DOMConfigurator.configure("./src/test/resources/log4j.xml");
		extentReports   = new ExtentReports(prop.getProperty("REPORT_PATH")+"/"+reportName,true);
		extentReports.addSystemInfo("Environment","QA-Sanity");

}

	@AfterSuite
	public void tearDownExtendReport()
	{
		extentReports.flush();
		extentReports.close();
	}
	
	public void printLog(String logText)
	{
		Reporter.log(logText);
		logger.info(logText);
	}
	public void printLogError(String logText,Exception e)
	{
		Reporter.log(logText+"--->"+e.toString());
		logger.error(logText+"--->"+e.toString());
	}

	public void terminateMCSSession()
	{
		//util.terminateSFTPSession(mcsServerSession);
	}

	@BeforeMethod(alwaysRun=true)
	public void initTestConfigurations(){

		s_Assert=new SoftAssert();
	}
	@AfterMethod
	public void sessionDestructor()
	{
		s_Assert=null;
		System.gc();
	}









	/*
	 * This method is used for connecting to APTOS server
	 */
	public boolean isAPTOSConnected()
	{
		host=prop.getProperty("APTOS_SERVER_HOST");
		port=Integer.parseInt(prop.getProperty("APTOS_SERVER_PORT"));
		user=prop.getProperty("APTOS_SERVER_USER");
		pwd=prop.getProperty("APTOS_PASSWORD");
		try
		{
			aptosSession=util.getVPNSession(host, user, pwd, port);
			if(aptosSession!=null)
			{
				try
				{
					aptosSession.connect();
					printLog("<-------------------------------------Connection to the aptosSession Server succeeded---------------------------->");
					return true;
				}
				catch(Exception e)
				{
					printLog("<-------------------------------------Connection to the aptosSession Server Fail-------------------------------->");
					printLogError("Issue in Connection to aptosSession", e);
					return false;
				}
			}
			else
			{
				printLog("<----------------------------------------Issue in Creating the session Object for aptosSession-------------------------------->");
				return false;
			}
		}
		catch(Exception e)
		{
			printLogError("Issue in Creating the session Object",e);
			return false;
		}

	}
	
}
