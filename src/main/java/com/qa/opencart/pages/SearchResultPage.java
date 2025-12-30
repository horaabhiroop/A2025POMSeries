package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	
	//1. By locators
	By ProductCount = By.xpath("//div[@class='product-thumb']");
	
	//2. Constructor
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	//3. Methods
	public int getSearchProductCount() {
		return eleutil.waitForElementsToBeVisible(ProductCount, AppConstants.MEDIUM_TIME_OUT).size();
	}
	
	public productInfoPage selectProduct(String productName) {
		By product = By.linkText(productName);
		eleutil.doClick(product);
		return new productInfoPage(driver);
	}

}
