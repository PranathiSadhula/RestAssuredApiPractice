package week4.day2.newsletter;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetMyUserDetails extends NewsLetterBasics{
	
	@Test
	public void TC007_getMyUserDetails() {
		

		RestAssured.baseURI = prop.getProperty("base_uri");
				Response resp = RestAssured
						.given()
						.log()
						.all()
						.headers(accessHeader)
						.get("users/"+user_id);
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
			
	}

}
