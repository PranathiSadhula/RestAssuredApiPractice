package week1.day1.covid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CountryStatsForIndia {

	@Test
	public void top5CountriesWithHighestNewCases() throws ParseException {
		// Step1 : Base url setup
		RestAssured.baseURI = "https://covid-19.dataflowkit.com/v1/india";

						
		// step 2 : get method to retrive data
				Response resp = RestAssured
						.given()
						.accept(ContentType.JSON)
						.get();

		// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
	
		// step 5 : verify status code : 200
		System.out.println("Expected status code : 200 and actual :" + resp.getStatusCode());

		// step 6 : verify response time < 600
		System.out.println("Expected response time < 600 and actual :" + resp.getTime());

		// step 7 : verify content type : JSON
		System.out.println("Expected content type = JSON and actual :" + resp.contentType());
	}

}
