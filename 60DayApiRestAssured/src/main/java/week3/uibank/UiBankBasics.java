package week3.day2.uibank;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeSuite;

import io.restassured.http.Header;
import io.restassured.http.Headers;

public class UiBankBasics {
	public String userId ;//= "5eef9c00e29f950044ba3088";
	public Headers hMap;
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	@BeforeSuite
	public void setUiBankBasics() {
		setUserId("5eef9c00e29f950044ba3088");
		List<Header> hList = new ArrayList<Header>();
		hList.add(new Header("Authorization","5C2q23WDAidmBPdQaTQwiJQ1HJZIWosYnfqkmMlIgKF8opmBCrlRKBPAEucQUU8d"));
		hMap = new Headers(hList);	
	}
	
	

}
