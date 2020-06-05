package week1.day1.paypal;

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

public class CreateProductwithbody extends PayPalBasics {
	@Test
	public void createProduct() {
			// Step1 : Base url setup
		RestAssured.baseURI = "https://api.sandbox.paypal.com/v1/catalogs/products";
		
		List<Header> hList = new ArrayList<Header>();
		hList.add(new Header("Authorization","Bearer "+paypalBearerAuth));
		
		Headers hMap = new Headers(hList);					
		// step 2 : get method to retrive data
				Response resp = RestAssured
						.given()
						.headers(hMap)
						.contentType(ContentType.JSON)
						.body("{\r\n" + 
								"  \"name\": \"Pranathi 60day API Service -RA within body \",\r\n" + 
								"  \"description\": \"60Day API service provides practice problems 2 per week for 60days -RA\",\r\n" + 
								"  \"type\": \"SERVICE\",\r\n" + 
								"  \"category\": \"SOFTWARE\",\r\n" + 
								"  \"image_url\": \"https://example.com/streaming.jpg\",\r\n" + 
								"  \"home_url\": \"https://example.com/home\"\r\n" + 
								"}")
						
						.post();

		// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
	
		// step 5 : verify status code : 201
				assertTrue(resp.getStatusCode() == 201, "Expected status code : 201 and actual :" + resp.getStatusCode());

				// step 6 : verify response time < 600
				assertTrue(resp.getTime()<  600,"Expected response time < 600 and actual :" + resp.getTime());

				// step 7 : verify content type : JSON
				assertTrue(resp.contentType().toLowerCase().contains("json"),"Expected content type = JSON and actual :" + resp.contentType());

	}


}
