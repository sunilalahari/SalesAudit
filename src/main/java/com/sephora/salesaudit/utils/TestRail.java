package com.sephora.salesaudit.utils;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;

import com.sephora.salesaudit.basetest.SalesAuditConstants;
public class TestRail {
	APIClient client = new APIClient("https://sephoraus.testrail.net/");
	public void testRail(int Status, String testCaseID,ITestContext context) throws MalformedURLException, IOException, APIException {
		//String railID=context.getCurrentXmlTest().getParameter("testRailRunID");
		Map<String,Object> data = new HashMap<String, Object>();
		String railID="";
		
		if(SalesAuditConstants.railIDValue!=null)
			railID=SalesAuditConstants.railIDValue;
		else
			railID=context.getCurrentXmlTest().getParameter("testRailRunID");
        System.out.println("Value of TestRail ID ---------------->"+railID);
		client.setUser("vijay.saini@sephora.com");
		client.setPassword("Sephora2017");
		data.put("status_id", new Integer(Status));
		data.put("comment", "Automation Script Execution");
		client.sendPost("add_result_for_case/" + railID + "/"+ testCaseID, data);

	}
}
