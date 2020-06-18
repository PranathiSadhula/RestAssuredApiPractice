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

public class CreateCustomer extends ZohoBasics  {
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
								"    \"display_name\": \"rest api 60 day practice via RA-2\",\r\n" + 
								"    \"first_name\": \"create customer\",\r\n" + 
								"    \"last_name\": \"via api\",\r\n" + 
								"    \"email\": \"pranathisadhula@gmail.com\"\r\n" + 
								"   \r\n" + 
								"}")
						
						.post("customers");

		// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
		setCustomer_id(jsonresp.get("customer.customer_id").toString());
	
		// step 5 : verify status code : 201
				assertTrue(resp.getStatusCode() == 201, "Expected status code : 201 and actual :" + resp.getStatusCode());

			
	}


}
