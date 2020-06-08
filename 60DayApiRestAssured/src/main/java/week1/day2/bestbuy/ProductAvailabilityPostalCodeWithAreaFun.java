package week1.day2.bestbuy;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ProductAvailabilityPostalCodeWithAreaFun {
	
	@Test
	public void productAvailabilityPostalCodeWithAreaFun() {
		// Step1 : Base url setup
				RestAssured.baseURI = "https://api.bestbuy.com/v1/stores(area(02864,20))";

				// step2 : Authentication

				// step3 : build params
				Map<String, String> pMap = new HashMap<String, String>();
				pMap.put("apiKey", "qUh3qMK14GdwAs9bO59QRSCJ");
				pMap.put("format", "json");
				pMap.put("show", "storeId,name,address,distance");

				Response resp = RestAssured.given().params(pMap).get();

				JsonPath jsonresp = resp.jsonPath();
				jsonresp.prettyPrint();
				
				assertTrue(resp.getStatusCode() == 200, "Expected status code : 200 and actual :"+resp.getStatusCode());
	}

}
