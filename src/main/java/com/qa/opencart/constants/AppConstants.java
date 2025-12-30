package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	
	public static final String LOGIN_PAGE_PARTIAL_URL = "route=account/login";
	
	public static final String ACCOUNT_PAGE_PARTIAL_URL = "route=account/account";
	
	public static final String MY_ACCOUNT_PAGE_TITLE = "My Account";
	
	public static final int SMALL_TIME_OUT = 5;
	
	public static final int MEDIUM_TIME_OUT = 10;
	
	public static final int LARGE_TIME_OUT = 20;

	public static final String FORGOT_PAGE_TITLE = "Change Password";

	public static final String FORGET_PASS_PARTIAL_URL = "route=account/password";
	
	public static final List<String> EXPECTED_HEADER_LIST = Arrays.asList("My Account", "My Orders","My Affiliate Account", "Newsletter");

	public static final String REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";

	//public static final int TOTAL_NO_OF_PRODUCT_IMAGES = 4;
	

}
