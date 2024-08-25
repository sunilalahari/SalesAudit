package com.sephora.salesaudit.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import org.testng.util.RetryAnalyzerCount;


import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.pcbsys.foundation.utils.propertiesLoader;
import com.sephora.salesaudit.basetest.SalesAuditConstants;
import com.sephora.salesaudit.utils.TestReRun;

public class AnnotationTransformer implements IAnnotationTransformer {
	
	String type;
    static int counter=0;
    String testCaseList="";
	String flowType=null;
	String testCaseType=null;
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(TestReRun.class);
		/*if(!testCaseType.equals("ALL"))
		{
			if(counter>0)
			{
				if(flowType!=null && testCaseType!=null)
				{
				annotation.setEnabled(isMethodExecute(testCaseList, testMethod.getName()));
				}
			}
			else
			{
				flowType=System.getProperty("module");	
				testCaseType=System.getProperty("priorty");
				if(flowType!=null && testCaseType!=null)
				{
					testCaseList=getTestCaseName(flowType,testCaseType);
					annotation.setEnabled(isMethodExecute(testCaseList, testMethod.getName()));
					
				}
			}
		}
		counter++;*/
		

		/*if(ESBConstants.testCaseType.equalsIgnoreCase("Critical"));
		{
			type="testCri";
		}
		if(testMethod.get)
		{
			annotation.ge
		}*/
		
		
	}
	/*
	 * This method is used fir getting name of test cases needs to be Execute
	 */
	public String getTestCaseName(String sheetName,String type) 
	{
		   Fillo  fillo = new Fillo();
           Connection con=null;
           String result="";;
		try {
			con = fillo.getConnection("./src/test/resources/mapping/TestNGMapping/TestNGMapping.xlsx");
			String query = "Select * from "+ sheetName+" where PRIORTY ='"+type+"' ";
            Recordset recordSet = con.executeQuery(query);
            while (recordSet.next()) {
            	result=result+recordSet.getField("TEST_CASE_NAME");
            	
            }
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
               
            
	}
	/*
	 * This method is used for checking which testMethod will execute
	 */
	public boolean isMethodExecute(String testCaseList,String methodName)
	{
		if(testCaseList.contains(methodName))
		{
	      return true;
		}
		else
		{
		  return false;
		}
	}
}
