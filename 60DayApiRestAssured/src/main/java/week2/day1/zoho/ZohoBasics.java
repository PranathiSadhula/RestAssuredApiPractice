package week2.day1.zoho;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ZohoBasics {
public String zohoOauthToken;
private String customer_id = "";
public String getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(String customer_id) {
	this.customer_id = customer_id;
}
// access token implementation needs to be included here
	@BeforeSuite
	public void getAccessToken() {
		
		
		zohoOauthToken = "1000.7629147c36b5811ea8e9842081a995ff.6a511be8afd3700071387e2dba39397e";
	}

	
}
