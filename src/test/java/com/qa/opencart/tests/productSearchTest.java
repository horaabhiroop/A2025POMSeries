package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.baseTest;

public class productSearchTest extends baseTest{
	
	@DataProvider
	public Object[][] getProductImageCount() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"Samsung", "Samsung SyncMaster 941BW", 1},
		};
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro"},
			{"MacBook", "MacBook Air"},
			{"Samsung", "Samsung SyncMaster 941BW"},
		};
	}
	
	@BeforeClass
	public void productPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@Test(dataProvider = "getProductData")
	public void productSearchTest2(String brand, String productName) {	
		searchResPage = accPage.doSearch(brand);
		productInfoPage = searchResPage.selectProduct(productName);
		String actProductHeaderName = productInfoPage.getProductHeaderValue();
		Assert.assertEquals(actProductHeaderName, productName);
	}
	
	@Test(dataProvider = "getProductImageCount")
	public void productImageCount(String brand, String productName, int ImgCount) {
		searchResPage = accPage.doSearch(brand);
		productInfoPage = searchResPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), ImgCount);	
	}
	
	@Test
	public void productDataTest() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfo = productInfoPage.getProductInfo();
		System.out.println(actProductInfo);
		
		softAssert.assertEquals(actProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfo.get("Availability"), "Out Of Stock");
//		softAssert.assertEquals(actProductInfo.get("Product Name "), "MacBook Pro");
		softAssert.assertEquals(actProductInfo.get("Product Price"), "$2,000.00");
		softAssert.assertEquals(actProductInfo.get("Product Ex Tax Price"), "Ex Tax: $2,000.00");
		System.out.println("----------");
		
		softAssert.assertAll();
		
	}

}
