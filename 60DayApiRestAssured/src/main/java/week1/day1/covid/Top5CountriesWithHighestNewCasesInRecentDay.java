package week1.day1.covid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Top5CountriesWithHighestNewCasesInRecentDay {
	
	@Test
	public void top5CountriesWithHighestNewCases() throws ParseException {
		//Step1 : Base url setup
				RestAssured.baseURI = "https://covid-19.dataflowkit.com/v1";
				
		//step 2 : get method to retrive data  
				Response resp = RestAssured
						.given()
						.get();

		//step 3 convert response into json response
				JsonPath jsonresp = resp.jsonPath();
		//step 4: get New cases updated in Recent day
				long currTime = new Date().getTime();
				// console.log("currTime :"+currTime);
				long last24thHr = 24 * 60 * 60 * 1000;
				// console.log("last24thHr :"+last24thHr)
				long expectedLastUpdated = currTime - last24thHr;
					
				List<String> countryList = jsonresp.getList("Country_text");
				List<String> lastUpdatedList = jsonresp.getList("'Last Update'");
				List<String> newCasesList = jsonresp.getList("'New Cases_text'");
				Map<Integer, String> countryMap = new TreeMap<Integer, String>(Collections.reverseOrder());
				for ( int i = 0; i < countryList.size(); i++) { // Creating date format
					
					String myDate = lastUpdatedList.get(i);//.replace("-", "").replace(" ", "").replace(":", "");
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHHmm");
					if(myDate != null && myDate != "") {
						Date date = sdf.parse(myDate);
						long udatedtimeinMS = date.getTime();
						if (udatedtimeinMS > expectedLastUpdated && newCasesList.get(i) != ""  && newCasesList.get(i) != null) {
							Integer newCasesNum =Integer.valueOf(newCasesList.get(i).replace("+", "").replace(",", ""));
								countryMap.put(newCasesNum, countryList.get(i));
							
						}
					}
				}
				 // using for-each loop for iteration over Map.entrySet() 
				int top5 = 0;
		        for (Map.Entry<Integer,String> entry : countryMap.entrySet()) {  
		            if(entry.getValue() != "World" && top5 < 7) {
		            	System.out.println(entry.getValue()+" with new cases :"+entry.getKey());
		            	top5++;
		            } 
				}
		     
				
				//step 5 : verify status code : 200
				System.out.println("Expected status code : 200 and actual :"+resp.getStatusCode());
				
				//step 6 : verify response time < 600
				System.out.println("Expected response time < 600 and actual :"+resp.getTime());
				
				//step 7 : verify content type : JSON
				System.out.println("Expected content type < JSON and actual :"+resp.contentType());
	}

}
