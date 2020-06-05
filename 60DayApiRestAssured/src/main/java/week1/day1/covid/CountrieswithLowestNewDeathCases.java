package week1.day1.covid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CountrieswithLowestNewDeathCases {

	@Test
	public void top5CountriesWithHighestNewCases() throws ParseException {
		// Step1 : Base url setup
		RestAssured.baseURI = "https://covid-19.dataflowkit.com/v1";

		// step 2 : get method to retrive data
		Response resp = RestAssured.given().get();

		// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		// jsonresp.prettyPrint();

		// step 4: get Lowest Death Cases
		List<String> countryList = jsonresp.getList("Country_text");
		List<String> newDeathCasesList = jsonresp.getList("'New Deaths_text'");
		System.out.println(newDeathCasesList.size());
		Map<Integer, String> countryMap = new TreeMap<Integer, String>();
		for (int i = 0; i < countryList.size(); i++) { // Creating date format

			if (newDeathCasesList.get(i) != null && newDeathCasesList.get(i) != "") {
				Integer newCasesNum = Integer.valueOf(newDeathCasesList.get(i).replace("+", "").replace(",", ""));
				countryMap.put(newCasesNum, countryList.get(i));

			}

		}

		// using for-each loop for iteration over Map.entrySet()
		int top5 = 0;
		for (Map.Entry<Integer, String> entry : countryMap.entrySet()) {
			if (entry.getValue() != "World" && top5 < 5) {
				System.out.println(entry.getValue() + " with lowest new Death cases :" + entry.getKey());
				top5++;
			}
		}

		// step 5 : verify status code : 200
		System.out.println("Expected status code : 200 and actual :" + resp.getStatusCode());

		// step 6 : verify response time < 600
		System.out.println("Expected response time < 600 and actual :" + resp.getTime());

		// step 7 : verify content type : JSON
		System.out.println("Expected content type < JSON and actual :" + resp.contentType());
	}

}
