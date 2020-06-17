package week1.day2.openweather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class MajorCitiesWeather {

	@Test
	public void majorCitiesWeather() {
		RestAssured.baseURI = "http://api.openweathermap.org/data/2.5/find";
		
		Map<String, String> pMap = new HashMap<String, String>();
		pMap.put("apiKey", "63ba265769b16344f4f41db6bcd793f3");
		pMap.put("lat", "21.7679");
		pMap.put("lon","78.8718");
		pMap.put("cnt","50");
		
		
		Response resp = RestAssured.given()
				.log().all() .params(pMap).get();

		JsonPath jsonresp = resp.jsonPath();
		List<Map<Object, Object>> list = jsonresp.getList("list");
	
		for (Map<Object, Object> map : list) {
			List<Map<Object, Object>> weathermap =  (List<Map<Object, Object>>) map.get("weather");
			
			if (weathermap.get(0).get("main").toString().equalsIgnoreCase("Rain") || weathermap.get(0).get("main").toString().equalsIgnoreCase("Haze")) {
				System.out.println(map.get("name")+" === "+weathermap.get(0).get("main"));
			}
			
		}
		//Map<Object, Object> respMap = jsonresp.getMap("");
		
		
		
	} 
	
	
}
	

