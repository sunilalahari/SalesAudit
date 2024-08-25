package com.sephora.salesaudit.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertyReader
{
	/***
	 * It initialize the property file.
	 * @param moduleName
	 * @return
	 * @throws ConfigurationException 
	 */
	public static XProperties getPropeties(String location)
	{
		//Properties prop = new Properties();
		XProperties prop = new XProperties();
		InputStream input = null;
		try
		{
			if(!(System.getProperty("host")==null)){
				PropertiesConfiguration config = new PropertiesConfiguration("./src/test/resources/config/"+location);
				config.setProperty("hostName", System.getProperty("host"));
				config.setProperty("portNo", System.getProperty("port"));
				config.setProperty("portNoSFTP", "5160");
				config.setProperty("userLogin", "Administrator");
				config.setProperty("passLogin", "manage");
				config.setProperty("userService", "Administrator");
				config.setProperty("passService", "manage");
				config.save();
			}
			input = new FileInputStream("./src/test/resources/config/"+location);
			prop.load(input);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			
			prop=null;
		} catch (ConfigurationException e) {
			e.printStackTrace();
			prop=null;
		}
		return prop;

	}

}