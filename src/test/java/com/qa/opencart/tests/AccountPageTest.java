package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.baseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends baseTest{
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void AccountPageTitleTest() {
		String actTitle = accPage.getAccountPageTitle();
		Assert.assertEquals(actTitle, AppConstants.MY_ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void AccountPageURLTest() {
		String actUrl = accPage.getAccountPageUrl();
		Assert.assertEquals(actUrl.contains(AppConstants.ACCOUNT_PAGE_PARTIAL_URL), true);
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		boolean actLink =accPage.isLogoutLinkVisible();
		Assert.assertTrue(actLink);
	}
	
	@Test
	public void accPageHeadersTest() {
		List<String> actHeader = accPage.getAccountSectionHeaderList();
		Assert.assertEquals(actHeader, AppConstants.EXPECTED_HEADER_LIST);
	}
	
}
