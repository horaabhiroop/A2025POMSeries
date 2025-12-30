package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class Retry implements IRetryAnalyzer{
	
	private int retryCount =0;
	private int maxRetryCount = 3;

	@Override
	public boolean retry(ITestResult result) {
		if(!result.isSuccess())
			if(retryCount < maxRetryCount) {
				retryCount++;
				result.setStatus(ITestResult.FAILURE);
				return true;
			}
			else {
				result.setStatus(ITestResult.FAILURE);
			}
			else {
				
				result.setStatus(ITestResult.SUCCESS);
			}
			return false;
	}	
	}

	
	

