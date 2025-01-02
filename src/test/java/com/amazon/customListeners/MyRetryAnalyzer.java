package com.amazon.customListeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.amazon.utils.ConfigReader;

public class MyRetryAnalyzer implements IRetryAnalyzer {
    ConfigReader configReader = new ConfigReader();
	private int retryCount =0;
	private int max_retry_count=Integer.valueOf(configReader.getMax_Retry_Count());
	
	@Override
	public boolean retry(ITestResult result) {
		if(retryCount<max_retry_count && !result.isSuccess())
		{
			retryCount++;
			System.out.println("Retrying the Test==>"+result.getName()+"==> AttemptNo "+retryCount);
			return true;
		}
		return false;
	}

}
