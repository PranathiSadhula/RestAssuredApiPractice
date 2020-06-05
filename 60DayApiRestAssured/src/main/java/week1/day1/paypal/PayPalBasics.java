package week1.day1.paypal;

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

public class PayPalBasics {
public String paypalBearerAuth;
public ArrayList<String> productIds = new ArrayList<String>();
	@BeforeSuite
	public void getAccessToken() {
		// Step1 : Base url setup
		RestAssured.baseURI = "https://api.sandbox.paypal.com/v1/oauth2/token";

		//Basic Auth
		RestAssured.authentication = RestAssured
				.preemptive()
				.basic("AUa2yDVVQPjjxNuDSvhyDo-yv3u_N4SL5ihYromz-47P8ypTKzKlomOo-hdfT3lsKm51ODKaUc3Ko2Ve", "EMVEhNXzg2SP2xKcs1ZRJ0HWk6XvtxkYfOs4g_V0Kb6U-vajiA6uRnsiB7uotfNehKVAfkbdNNW-zFsp");
		// build paramsMap
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("grant_type", "client_credentials");
		
		// step 2 : get method to retrive data
		Response resp = RestAssured.given().params(paramsMap).accept(ContentType.JSON).post();

		// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		
		paypalBearerAuth = jsonresp.get("access_token");
	}

	
	
	@DataProvider(name="createpaypalapp")
	public String[] getDataToCreatePayPalApp() {
		 File projectWorkSpace = new File("./");
		    File[] jsonFiles = projectWorkSpace.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".json");
				}
			});
		   System.out.println(jsonFiles.length);
		String[] data = new String[jsonFiles.length];
		for(int i = 0; i < jsonFiles.length; i++) {
			data[i] = jsonFiles[i].getAbsolutePath();
		}
		
		return data;
		
	}
}
