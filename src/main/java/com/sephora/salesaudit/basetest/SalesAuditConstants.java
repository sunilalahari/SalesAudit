package com.sephora.salesaudit.basetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.jayway.jsonpath.DocumentContext;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.sephora.salesaudit.utils.ExcelUtility;
import com.sephora.salesaudit.utils.FileUtility;
import com.sephora.salesaudit.utils.XProperties;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
public class SalesAuditConstants  {
	//*************************************************************Static Variable******************************************************************************
	
	public static ExtentTest extentTest;
	public static SoftAssert s_Assert;
	public static String railIDValue="";
	public static XProperties prop = null;
	public static FileUtility util;
	public static ExtentReports extentReports;
	public  Logger logger = Logger.getLogger(this.getClass().getName());
	public static String user="";
	public static String host="";
	public static String pwd="";
	public static String POSFileName="";
	public static int port=0;
	public static Session aptosSession = null;
	public static Session session=null;
	public Properties config=null;
	public static boolean isCallingLogAppenderRepetitively = false;
	public static String dynamicDateTimeMyAppender = "";
	
	
	
	
	
}

