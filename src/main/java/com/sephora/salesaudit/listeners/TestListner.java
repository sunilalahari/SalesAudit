package com.sephora.salesaudit.listeners;

import java.lang.reflect.Method;

import javax.net.ssl.SSLEngineResult.Status;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Font;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


import com.relevantcodes.extentreports.LogStatus;
import com.sephora.salesaudit.annotation.TestRailID;
import com.sephora.salesaudit.basetest.SalesAuditFileBase;
import com.sephora.salesaudit.utils.TestRail;


/**
 * 
 * TestListner is the listener class that listens to test
 * case execution and allows Engineer to complete post test operations.
 *
 */

public class TestListner extends TestListenerAdapter {

	/*private static final Logger logger = LogManager.getLogger(TestListner.class
			.getName());*/
	public Logger logger = Logger.getLogger(this.getClass().getName());
	String[] testIds;
	private TestRail testRail = new TestRail();

	private String[] qmetryID(ITestResult tr) {
		ITestNGMethod testNGMethod = tr.getMethod();
		Method method = testNGMethod.getConstructorOrMethod().getMethod();
		testNGMethod.getDescription();
		TestRailID testAnnotation = method
				.getAnnotation(TestRailID.class);
		if (testAnnotation != null)
			return testAnnotation.id();
		else
			return null;
	}
	

	@Override
	public void onTestStart(ITestResult tr) {
		testIds = qmetryID(tr);
		testIds = qmetryID(tr);
		
		for (String test : testIds){
			logger.info("[TC-" + test + " ------- Executing "
					+ tr.getMethod().getMethodName() + "]"
					+ "<br>DESCRIPTION :" + tr.getMethod().getDescription());
		//ESBBase.extentTest= ESBBase.extentReports.startTest(test);
		}
	}

	/*@Override
	public void onTestSuccess(ITestResult tr) {
		testIds = qmetryID(tr);
		testIds = qmetryID(tr);
		for (String test : testIds)
			logger.info("[TEST IS SUCCESSFUL -------- Test case " + test
					+ " passed]");
	}*/

	

	@Override
	public void onTestSuccess(ITestResult tr) {
		SalesAuditFileBase.extentTest=null;
		testIds = qmetryID(tr);
		testIds = qmetryID(tr);
		for (String test : testIds) {
			logger.info("[TEST IS SUCCESSFUL -------- Test case " + test + " passed]");
			SalesAuditFileBase.extentTest= SalesAuditFileBase.extentReports.startTest(test);
		     
			SalesAuditFileBase.extentTest.log(LogStatus.PASS,"<font color='green'>"+tr.getClass()+"------>"+tr.getName()+"[Test case " + test+ " Passed]</font>");
			try {
				testRail.testRail(1, test.replace("C", ""),tr.getTestContext());
			} catch (Throwable e) {
				e.printStackTrace();
			}
			 SalesAuditFileBase.extentReports.endTest(SalesAuditFileBase.extentTest);
			
		}
	}
	@Override
	public void onTestFailure(ITestResult tr) {
		testIds = qmetryID(tr);
		SalesAuditFileBase.extentTest=null;
		Throwable cause = tr.getThrowable();
		for (String test : testIds) {
			SalesAuditFileBase.extentTest= SalesAuditFileBase.extentReports.startTest(test);
			SalesAuditFileBase.extentTest.log(LogStatus.FAIL,"<font color='red'>"+tr.getName()+"[Test case " + test+ " Failed]</font>");
			SalesAuditFileBase.extentReports.endTest(SalesAuditFileBase.extentTest);
			if (null != cause) {
				logger.info("[TEST FAILED -------- Test case " + test
						+ " failed " +cause.getMessage()+"]");
				//ESBBase.extentTest= ESBBase.extentReports.startTest(test.replace("C", ""));
				
				try {
					testRail.testRail(5, test.replace("C", ""),tr.getTestContext());
				} catch (Throwable e) {
					e.printStackTrace();
				}
			} else
				logger.info("[TEST FAILED -------- Test case " + test
						+ " failed]");
		}

	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		SalesAuditFileBase.extentTest=null;
		testIds = qmetryID(tr);
		for (String test : testIds)
		{
			logger.info("[TEST IS SKIPPED -------- Test case " + test
					+ " skipped]");
			//ESBBase.extentTest= ESBBase.extentReports.startTest(test.replace("C", ""));
			SalesAuditFileBase.extentTest= SalesAuditFileBase.extentReports.startTest(test);
			SalesAuditFileBase.extentTest.log(LogStatus.SKIP,"<font color='blue'>"+tr.getName()+"[Test case " + test+ " Skip]</font>");
			SalesAuditFileBase.extentReports.endTest(SalesAuditFileBase.extentTest);
			
			try {
				testRail.testRail(6, test.replace("C", ""),tr.getTestContext());
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		
	}

}
