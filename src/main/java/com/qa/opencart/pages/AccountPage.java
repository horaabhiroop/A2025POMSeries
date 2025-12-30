package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1. By locators
	private By logoutBtn = By.linkText("Logout");
	private By SearchField = By.xpath("//input[@name='search']");
	private By accPageHeaders = By.xpath("//div[@id='content']/h2");
	private By forgotPassLink = By.linkText("Password");
	private By searchButton = By.xpath("//div[@id='search']//button");
	
	
	//2. Constructor
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. Page Action
	

	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.MY_ACCOUNT_PAGE_TITLE, AppConstants.MEDIUM_TIME_OUT);
		System.out.println("Page title is : " + title);
		return title;
	}
	
	public String getAccountPageUrl() {
		String url = eleUtil.waitForUrl(AppConstants.SMALL_TIME_OUT, AppConstants.ACCOUNT_PAGE_PARTIAL_URL);
		System.out.println("Url of the current Page is : " + url);
		return url;
	}
	
	public boolean isLogoutLinkVisible() {
		return eleUtil.waitForElementPresence(logoutBtn, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public boolean isSearchFieldVisible() {
		return eleUtil.waitForElementPresence(SearchField, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
	}
	
	public List<String> getAccountSectionHeaderList() {
		return eleUtil.getAllElementsTextList(accPageHeaders, AppConstants.MEDIUM_TIME_OUT);
	}
	
	public ForgetPasswordPage clickOnForgotPassword() {
		 eleUtil.doClickWithWait(forgotPassLink, AppConstants.MEDIUM_TIME_OUT);
		 return new ForgetPasswordPage(driver);
	}
	
	public SearchResultPage doSearch(String ProductName) {
		eleUtil.doSendKeysWithWait(SearchField, AppConstants.MEDIUM_TIME_OUT, ProductName);
		eleUtil.doClickWithWait(searchButton, AppConstants.MEDIUM_TIME_OUT);
		return new SearchResultPage(driver);
	}
	
	
	

}
