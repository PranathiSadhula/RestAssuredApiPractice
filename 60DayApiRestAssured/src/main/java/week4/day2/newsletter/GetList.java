package week4.day2.newsletter;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetList extends NewsLetterBasics{
	
	@Test
	public void TC004_getList() {
		

		RestAssured.baseURI = prop.getProperty("base_uri");
				Response resp = RestAssured
						.given()
						.log()
						.all()
						.headers(accessHeader)
						.get("lists");
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
		System.out.println(jsonresp.get("value.id"));
		
		
	}

}
