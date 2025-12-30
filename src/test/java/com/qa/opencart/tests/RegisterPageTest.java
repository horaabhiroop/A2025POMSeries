package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.baseTest;

public class RegisterPageTest extends baseTest{
	
	@BeforeClass
	public void registerSetup() {
		registerPage = loginPage.goToRegisterPage();
	}
	
	public String randomEmail() {
		Random random = new Random();
		String email = "automation"+ random.nextInt(1000) +"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] registerUser() {
		return new Object[][] {
			{"riya", "abc", randomEmail(), "9293923", "riya@231", "yes"},
			{"riya", "abc", randomEmail(), "9293923", "riya@231", "yes"}
		};
	}
	
//	@Test(dataProvider = "getRegExcelData")
//	public void userRegTest(String firstName, String lastName, String phone, String password, String subscribe) {
//
//		boolean successFlag = registerPage.userRegistration(firstName, lastName, randomEmail(), phone, password, subscribe);
//		registerPage.goToRegisterPage();
//		Assert.assertEquals(successFlag, true);
//
//	}
	
	@Test(dataProvider = "registerUser")
	public void userRegPage(String name, String lastName, String email, String num, String pass, String sub) {
		boolean status = registerPage.userRegistration(name, lastName, email, num, pass, sub);
		registerPage.doLogoutRegister();
		Assert.assertTrue(status);
	}
	

}
