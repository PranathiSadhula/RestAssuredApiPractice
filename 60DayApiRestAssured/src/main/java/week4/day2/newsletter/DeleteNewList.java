package week4.day2.newsletter;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteNewList extends NewsLetterBasics{
	
	@Test
	public void TC005_DeleteNewList() {
		

		RestAssured.baseURI = prop.getProperty("base_uri");
				Response resp = RestAssured
						.given()
						.log()
						.all()
						.headers(accessHeader)
						//.contentType(ContentType.JSON)
					
						.delete("lists/"+"kk18wepf");
		
		assertTrue(resp.getStatusCode() == 204, "Expected status code : 204 and actual :" + resp.getStatusCode());

		
	}

}
