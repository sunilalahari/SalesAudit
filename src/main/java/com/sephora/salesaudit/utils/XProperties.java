package com.sephora.salesaudit.utils;

import java.util.Properties;

public class XProperties extends Properties {

	public String getProperty(String key)
	{
		String result="";
		String tempValue="";
		String tempkey="";
		String mainvalue=super.getProperty(key);
		String finalValue="";
		if(mainvalue!=null)
		{
			if(mainvalue.contains("+"))
			{
				String keyValues[]=mainvalue.split(",");
				for(int k=0;k<keyValues.length;k++)
				{
					   result="";
					if(keyValues[k].contains("+"))
					{
					    String tempArray[]=keyValues[k].split("\\+");
						for (int i=0;i<tempArray.length;i++)
						{
							tempValue=tempArray[i];
							if(tempValue.startsWith("$"))
							{
								tempValue=tempValue.substring(tempValue.indexOf("$")+1);
								tempValue=super.getProperty(tempValue);
								result=result+tempValue;
							}
							else
							{
								result=result+tempValue;
							}
			
						}
					}
					else
					{
						result=keyValues[k];
					}
					finalValue=finalValue+result+",";
				}
				finalValue=finalValue.substring(0,finalValue.length()-1);
				
			}
			else
				finalValue=mainvalue;
		}
		else
		{
			finalValue=mainvalue;
		}

		return finalValue;
	}
}