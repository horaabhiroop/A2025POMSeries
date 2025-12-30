package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.driverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.ForgetPasswordPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.pages.loginPage;
import com.qa.opencart.pages.productInfoPage;

public class baseTest {
	
	public WebDriver driver;
	public driverFactory df ;
	public Properties prop;
	
	public loginPage loginPage;
	public AccountPage accPage;
	public ForgetPasswordPage frgtPassPage;
	public SearchResultPage searchResPage;
	public productInfoPage productInfoPage;
	public SoftAssert softAssert;
	public RegisterPage registerPage;
	
	@BeforeTest
	public void setup() {
		df = new driverFactory();
		prop = df.initProp();
		
		driver = df.initDriver(prop);
		loginPage = new loginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void TearDown() {
		driver.quit();
	}

}
