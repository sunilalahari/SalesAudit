package com.sephora.salesaudit.utils;

import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.sephora.salesaudit.basetest.SalesAuditFileBase;

public class FileUtility extends SalesAuditFileBase {
	
	/*
	 * This method is used to get the session object 
	 */
	
	public Session getVPNSession(String host, String user, int port, String privateKeyFileName,
			String privateKeyFilePath) 
	{
		JSch jsch = null;
		String privateKeyFile = "";
		try {
			printLog("<-------------------Creating the Session Object for host: " + host
					+ "---------------------------->");
			privateKeyFile = privateKeyFilePath + privateKeyFileName;
			jsch = new JSch();
			jsch.addIdentity(privateKeyFile);
			session = jsch.getSession(user, host, port);
			config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(
					"PreferredAuthentications", 
					"publickey,keyboard-interactive,password");
			session.setConfig(config);
			session.setConfig(
					"PreferredAuthentications", 
					"publickey,keyboard-interactive,password");
		} catch (Exception e) {
			session = null;
			printLogError("Exception in creating the  Session Object---->", e);
		}
		return session;

	
}
	
	public Session getVPNSession(String host, String user, String pwd, int port) {
		Properties config = null;
		JSch jsch = null;
		try {
			printLog("<-------------------Creating the Session Object for host: " + host+"--------------------------->");
			jsch = new JSch();
			session = jsch.getSession(user, host, port);
			session.setPassword(pwd);
			config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(
					"PreferredAuthentications", 
					"publickey,keyboard-interactive,password");
			session.setConfig(config);
			session.setConfig(
					"PreferredAuthentications", 
					"publickey,keyboard-interactive,password");
		} catch (Exception e) {
			session = null;
			e.printStackTrace();
		}
		return session;
	}

}
