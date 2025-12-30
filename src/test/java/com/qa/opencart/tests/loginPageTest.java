package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.baseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

//Inheritance
public class loginPageTest extends baseTest{
	
	@Test
	@Severity(SeverityLevel.MINOR)
	public void loginPageTitleTest() {
		String actTitle =loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	@Severity(SeverityLevel.MINOR)
	public void loginPageURLTest() {
		String actUrl = loginPage.getCurrentPageUrl();
		Assert.assertEquals(actUrl.contains(AppConstants.LOGIN_PAGE_PARTIAL_URL), true);
	}
	
	@Test
	@Severity(SeverityLevel.CRITICAL)
	public void frwdPassLinkAvailable() {
		boolean isVisible =loginPage.isForgotPassVisible();
		Assert.assertTrue(isVisible);
	}
	
	@Test
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.MY_ACCOUNT_PAGE_TITLE);
		
	}

}
