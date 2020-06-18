package week2.day1.zoho;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateCustomer extends ZohoBasics  {
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
						.body("{\r\n" + 
								"    \"display_name\": \"updated customer using RA\",\r\n" + 
								"    \"first_name\": \"create customer\",\r\n" + 
								"    \"last_name\": \"via api\",\r\n" + 
								"    \"email\": \"pranathisadhula@gmail.com\",\r\n" + 
								"    \"shipping_address\": {\r\n" + 
								"        \"attention\": \"Benjamin George\",\r\n" + 
								"        \"street\": \"Harrington Bay Street\",\r\n" + 
								"        \"city\": \"Salt Lake City\",\r\n" + 
								"        \"state\": \"CA\",\r\n" + 
								"        \"zip\": 92612,\r\n" + 
								"        \"country\": \"U.S.A\",\r\n" + 
								"        \"fax\": 4527389\r\n" + 
								"    },\r\n" + 
								"   \"mobile\": \"9047769735\"\r\n" + 
								"}")
						
						.post("customers/"+getCustomer_id());

		// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
	
		// step 5 : verify status code : 201
				assertTrue(resp.getStatusCode() == 201, "Expected status code : 201 and actual :" + resp.getStatusCode());

			
	}


}
