package week4.day2.newsletter;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateMyUserDetails extends NewsLetterBasics{
	
	@Test
	public void TC008_uodateMyUserDetails() {
		

		RestAssured.baseURI = prop.getProperty("base_uri");
				Response resp = RestAssured
						.given()
						.log()
						.all()
						.body("{\r\n" + 
								"	\"language\": \"en_UK\"\r\n" + 
								"}")
						.headers(accessHeader)
						.patch("users/"+user_id);
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
			
	}

}
