package com.qa.opencart.pages;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ForgetPasswordPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. By locators
	
	
	//2. Constructor
	public ForgetPasswordPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. Method start
	
	public String forgetPageTitle() {
		return eleUtil.waitForTitleToBe(AppConstants.FORGOT_PAGE_TITLE, AppConstants.MEDIUM_TIME_OUT);
	}
	
	public String forgetPassURL() {
		String url = eleUtil.waitForUrl(AppConstants.MEDIUM_TIME_OUT, AppConstants.FORGET_PASS_PARTIAL_URL);
		System.out.println(url);
		return url;
	}
	

}
