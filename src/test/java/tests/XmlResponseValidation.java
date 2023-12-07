package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.commons.logging.Log;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class XmlResponseValidation {

	@Test
	public void validationWithinThen() {
		given().
		
		when().
		get("http://restapi.adequateshop.com/api/Traveler?page=1").
		then().log().all().
		statusCode(200).
		body("TravelerinformationResponse.page",equalTo("1")).
		body("TravelerinformationResponse.travelers.Travelerinformation[2].name",equalTo("vano")).
		header("Server","Microsoft-IIS/10.0");
	}
	
	@Test
	public void validationUsingResponse() {
		Response res=given().
				
				when().
				get("http://restapi.adequateshop.com/api/Traveler?page=1");
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(res.xmlPath().getInt("TravelerinformationResponse.travelers.Travelerinformation[6].id"), 11139);
		String val=res.xmlPath().getString("TravelerinformationResponse.page");
		System.out.println(val);
	}
	
	@Test
	public void validationBodyArray() {
		Response res=given().
		when().
		get("http://restapi.adequateshop.com/api/Traveler?page=1");
		XmlPath xp=new XmlPath(res.asString());
		List<String> travellers=xp.getList("TravelerinformationResponse.travelers.Travelerinformation");
		Assert.assertEquals(travellers.size(), 10);
		
		List<String> travellersName=xp.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
		boolean flag=false;
		for(String travellerName:travellersName) {
			if(travellerName.equals("Developer123")) {
				flag=true;
				break;
			}
		}
		Assert.assertTrue(flag);
	}
}


