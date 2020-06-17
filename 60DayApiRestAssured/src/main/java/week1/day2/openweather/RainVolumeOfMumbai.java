package week1.day2.openweather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RainVolumeOfMumbai{
	
	
		@Test()
		public void rainVolumeOfMumbai() throws ParseException {	
			
			float rain3daysVolume = 0;
		

			RestAssured.baseURI= "http://api.openweathermap.org/data/2.5/onecall";



			long today = new Date().getTime();
			
			long last3days = 24 *3 *60 * 60 * 1000;
			long timeOn3daysBck = today - last3days;
		
			Map<String,String> params = new HashMap<String, String>();
			params.put("lon","72.8777");
			params.put("lat","19.0760");
			
			params.put("start",String.valueOf(timeOn3daysBck));
			params.put("end",String.valueOf(today));
			params.put("appid", "63ba265769b16344f4f41db6bcd793f3");
			

			Response resp = RestAssured
					.given()
					.log().all() 
					.params(params)
					.accept(ContentType.JSON)
					.get();

			JsonPath jsonPath = resp.jsonPath();
			
			
		
			List<Map<Object, Map<Object, Object>>> hrlyRainReport = jsonPath.getList("hourly");
			
			for (Map<Object, Map<Object, Object>> hrlyRainVol : hrlyRainReport) {
				rain3daysVolume = rain3daysVolume+ (Float) hrlyRainVol.get("rain").get("1h");
			}
		
			
			System.out.println("rain volume in MUMBAI for 3days :" +rain3daysVolume);
		}	
		
	}


