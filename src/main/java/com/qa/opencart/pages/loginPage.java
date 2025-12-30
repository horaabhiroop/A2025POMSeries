package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class loginPage {
	
	//limited scope
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//By locators
	
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By frwtPass = By.xpath("//input[@id='input-password']/following-sibling::a");
	private By registerLink = By.linkText("Register");
	
	//Page constructor - to initialize the instance variable.
	public loginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//Page Actions + Methods	
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleToBe(AppConstants.MY_ACCOUNT_PAGE_TITLE, AppConstants.MEDIUM_TIME_OUT);
		System.out.println("Page title is : " + title);
		return title;
	}
	
	public String getCurrentPageUrl() {
		String url = eleUtil.waitForUrl(AppConstants.SMALL_TIME_OUT, AppConstants.LOGIN_PAGE_PARTIAL_URL);
		System.out.println("Url of the current Page is : " + url);
		return url;
	}
	
	public boolean isForgotPassVisible() {
		return eleUtil.waitForElementPresence(frwtPass, AppConstants.SMALL_TIME_OUT).isDisplayed();
	}
	
	public AccountPage doLogin(String username, String pwd) {
		
		eleUtil.doSendKeys(emailId, username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		//return eleUtil.waitForTitleToBe(AppConstants.MY_ACCOUNT_PAGE_TITLE, AppConstants.MEDIUM_TIME_OUT);
		return new AccountPage(driver);
	}
	
	public SearchResultPage performSearch(String productName) {
		AccountPage accPage = new AccountPage(driver);
		return accPage.doSearch(productName);
	}
	
	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
