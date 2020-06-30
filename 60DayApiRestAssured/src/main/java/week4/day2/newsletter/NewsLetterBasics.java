package week4.day2.newsletter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class NewsLetterBasics {
	protected String list_id;
	protected String segment_id;
	protected String user_id;
	protected static String access_token;
	protected static String refresh_token;
	protected static Properties prop;
	protected static Headers accessHeader;

	@BeforeSuite
	public void generateAccessToken() throws FileNotFoundException, IOException {
		prop = new Properties();
		prop.load(new FileInputStream("./src/main/resources/newsletter_config.properties"));
		List<Header> hList = new ArrayList<Header>();
		hList.add(new Header("Authorization", "Basic " + prop.getProperty("base64_authkey")));
		Headers hMap = new Headers(hList);

		RestAssured.baseURI = prop.getProperty("base_uri");
		// step 2 : get method to retrive data
		Response resp = RestAssured
						.given()
						.log()
						.all()
						.headers(hMap)
						.contentType(ContentType.JSON)
						.body("{\r\n" + "  \"username\": \"pranathisadhula@gmail.com\"" + " , \"password\": \"Pranathi75#\""
								+ " , \"grant_type\": \"https://nl2go.com/jwt\"" + "\r\n" + "}")
		
						.post("oauth/v2/token");

// step 3 convert response into json response
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
		this.access_token = jsonresp.get("access_token");
		this.refresh_token = jsonresp.get("refresh_token");
		
		List<Header> accessList = new ArrayList<Header>();
		accessList.add(new Header("Authorization", "Bearer " + this.access_token));
		accessList.add(new Header("Content-Type", "application/json"));
		this.accessHeader = new Headers(accessList);

		
	}
}