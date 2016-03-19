package com.exceptionaire.denso.Utils;

public interface AppConstant {   
	
	/**
	 * Base url for calling webservices
	 */
	 static final String BASE_URL = "http://192.199.240.234/~densoamp/denso_api/api/";
	 
	 /**
	  * Login API Url
	  */
	 static final String LOGIN_API="login";
	 
	 static final String FORGET_PASSWORD="/forgotPassword/?email=";
	 
	 static final String GET_CATEGORY="listCategory";
	 static final String GET_SUBCATEGORY="getSectionByCatId/?v_cat_id=";
	 static final String GET_SECTION_BY_SUB_CAT_ID="getSectionBySubCatId/?v_sub_cat_id=";
	 
	 
	 
	 static final String TIME_OUT_EXCEPTION = "Connection Timeout";
}
