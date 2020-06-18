package week2.day1.zoho;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetCustomer extends ZohoBasics  {
	@Test
	public void createProduct() {
			// Step1 : Base url setup
		RestAssured.baseURI = "https://subscriptions.zoho.com/api/v1/";
		
		List<Header> hList = new ArrayList<Header>();
		hList.add(new Header("Authorization","Bearer "+zohoOauthToken));
		hList.add(new Header("X-com-zoho-subscriptions-organizationid","717079474"));
		
		Headers hMap = new Headers(hList);					
		// step 2 : get method to retrive data
				Response resp = RestAssured
						.given()
						.headers(hMap)
						.contentType(ContentType.JSON)
						
						.get("customers");

		// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		List<Map<Object, Object>> custidlist = jsonresp.getList("customers");
    	for (Map<Object, Object> map : custidlist) {
			if (map.containsValue(getCustomer_id())) {
				System.out.println(getCustomer_id()+" is listed in getcustomers response "+ map.containsValue(getCustomer_id()) );
			}
		}
	
		// step 5 : verify status code : 201
				assertTrue(resp.getStatusCode() == 200, "Expected status code : 200 and actual :" + resp.getStatusCode());

			
	}


}
