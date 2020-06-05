package week1.day1.paypal;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ListCreatedProducts extends PayPalBasics {

	@Test
	public void listCreatedProducts() {
		// Step1 : Base url setup
		RestAssured.baseURI = "https://api.sandbox.paypal.com/v1/catalogs/products";

		List<Header> hList = new ArrayList<Header>();
		hList.add(new Header("Authorization", "Bearer " + paypalBearerAuth));

		Headers hMap = new Headers(hList);
		// step 2 : get method to retrive data
		Response resp = RestAssured.given().headers(hMap).params("page_size", "100").contentType(ContentType.JSON)
				.get();

		// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		// jsonresp.prettyPrint();
		// storeProductIDs[PROD-0LX03787TK620161S, PROD-620732897L126100B]
		List<String> prodIDList = jsonresp.getList("products.id");
		if (!productIds.isEmpty()) {
			for (int index = 0; index < productIds.size(); index++) {

				assertTrue(prodIDList.contains(productIds.get(index)), "Product is not listed");

			}
		}
		// step 5 : verify status code : 201
				assertTrue(resp.getStatusCode() == 200, "Expected status code : 201 and actual :" + resp.getStatusCode());

				// step 6 : verify response time < 600
				assertTrue(resp.getTime() <  600,"Expected response time < 600 and actual :" + resp.getTime());

				// step 7 : verify content type : JSON
				assertTrue(resp.contentType().toLowerCase().contains("json"),"Expected content type = JSON and actual :" + resp.contentType());
	}

}
