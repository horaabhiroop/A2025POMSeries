package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.baseTest;
import com.qa.opencart.constants.AppConstants;

public class forgetPassPageTest extends baseTest{
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		frgtPassPage = accPage.clickOnForgotPassword();
	}
	
	@Test
	public void verifyFrgtPageTitle() {
		String actTitle =frgtPassPage.forgetPageTitle();
		Assert.assertEquals(actTitle, AppConstants.FORGOT_PAGE_TITLE);
	}
	@Test
	public void verifyFrgtPassURL() {
		String url =frgtPassPage.forgetPassURL();
		Assert.assertEquals(url, AppConstants.FORGET_PASS_PARTIAL_URL);
	}
	
}
