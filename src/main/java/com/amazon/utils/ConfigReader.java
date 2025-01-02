package com.amazon.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private Properties prop;
	

	public ConfigReader() 
	{
		prop= new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("src/test/resources/configurations/config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getUrl()
	{
		return prop.getProperty("URL");
		
	}
	
	public String getBrowser()
	{
		return prop.getProperty("BROWSER");
		
	}
	public Long getGlobalTimeOut()
	{
		return Long.parseLong(prop.getProperty("GLOBALWAIT"));
				
		
	}
	
	public String getExcelFilePath()
	{
		return prop.getProperty("FIELDS_VERIFICATION_EXCEL");
				
		
	}
	
	public String getMax_Retry_Count()
	{
		return prop.getProperty("Max_Retries");
				
		
	}
}
