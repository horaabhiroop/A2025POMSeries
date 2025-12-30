package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class productInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	
	//1. By locator
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.xpath("//a[@class='thumbnail']/img");
	private By productHeaderDetails = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceDetails = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> productMap;
	
	
	//2. Constructor
	public productInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	//3. Methods
	 
	public String getProductHeaderValue() {
		
		String productHeaderValue = eleutil.doElementGetText(productHeader);
		System.out.println(productHeaderValue);
		return productHeaderValue;
		
	}
	
	public int getProductImagesCount() {
		return eleutil.waitForElementsToBeVisible(productImages, AppConstants.MEDIUM_TIME_OUT).size();
	}
	
	public Map<String, String> getProductInfo() {
		
		productMap = new HashMap<String, String>();
		
		//1. Add product Name in Map
		productMap.put("Product Name : ", getProductHeaderValue());
		
		//Get the meta details of the product
		getProductMetaData();
		
		//Get the price details of the product
		getProductPriceData();
		
		
		return productMap;
		
	}
	
	private void getProductMetaData() {
        List<WebElement> metaData = eleutil.getElements(productHeaderDetails);
		
		//Product Details data
		for(WebElement a : metaData) {
			String text =a.getText();
			String[] details = text.split(":");
			this.productMap.put(details[0].trim(), details[1].trim());
		}
	}
		
		private void getProductPriceData() {
			List<WebElement> priceData = eleutil.getElements(productPriceDetails);
			
			String ProductPrice = priceData.get(0).getText();
			String ProductExTaxPrice = priceData.get(1).getText().trim();
			
			this.productMap.put("Product Price", ProductPrice);
			this.productMap.put("Product Ex Tax Price", ProductExTaxPrice);
		}
	

}
