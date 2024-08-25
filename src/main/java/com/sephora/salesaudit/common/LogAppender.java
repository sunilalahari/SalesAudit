

package com.sephora.salesaudit.common;
import org.apache.log4j.RollingFileAppender;

import com.sephora.salesaudit.basetest.SalesAuditFileBase;
import com.sephora.salesaudit.basetest.SalesAuditConstants;

public class LogAppender extends RollingFileAppender  {
	boolean repeatCalling=false;
	
	
	/***
	 * It will create the logs and append the logs which are created as per the current time stamp. 
	 */
    @Override
    public void  setFile(String fileName) {
    	String replaceTarget="%dateTime";
    	String createFolder="folder";
    	String dynamicName="";
    	if(SalesAuditConstants.isCallingLogAppenderRepetitively==false)
    	{
        if (fileName.indexOf(replaceTarget) >= 0) {
        	SalesAuditConstants.dynamicDateTimeMyAppender=CreateUniqueNameOnRunTime.createUniqueName();
        	dynamicName=SalesAuditConstants.dynamicDateTimeMyAppender;
        	fileName = fileName.replaceAll(replaceTarget, dynamicName);
        	String folderName=dynamicName.substring(0, dynamicName.lastIndexOf("_"));
        	fileName= fileName.replaceAll(createFolder, folderName);
        	SalesAuditConstants.isCallingLogAppenderRepetitively=true;
        }
        super.setFile(fileName);
    	}
    	else
    	{
    		dynamicName=SalesAuditConstants.dynamicDateTimeMyAppender;
        	fileName = fileName.replaceAll(replaceTarget, dynamicName);
        	String folderName=dynamicName.substring(0, dynamicName.lastIndexOf("_"));
        	fileName= fileName.replaceAll(createFolder, folderName);
    		super.setFile(fileName);
    	}
    }
}