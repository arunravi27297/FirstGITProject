package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.apache.commons.logging.Log;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class JsonResponseValidation {

	@Test
	public void validationWithinThen() {
		given().
		queryParams("delay",2).
		when().
		get("https://reqres.in/api/users").
		then().log().all().statusCode(200).
		body("data[5].email",equalTo("tracey.ramos@reqres.in")).
		header("Server","cloudflare");
	}
	
	@Test
	public void validationUsingResponse() {
		Response res=given().
		queryParams("delay",2).
		when().
		get("https://reqres.in/api/users");
		String act=res.jsonPath().getString("data[5].email");
		assertEquals(act, "tracey.ramos@reqres.in");
		assertEquals(res.getStatusCode(), 200);
		assertEquals(res.getHeader("Connection"), "keep-alive");
	}
	
	@Test
	public void validationBodyArray() {
		
		
		Response res=given().
		contentType(ContentType.JSON).
		queryParams("delay",2).
		when().
		get("https://reqres.in/api/users");
		JSONObject jo=new JSONObject(res.asString());
		boolean flag=false;
		for(int i=0;i<jo.getJSONArray("data").length();i++) {
			String val=jo.getJSONArray("data").getJSONObject(i).getString("email");
			System.out.println(val);
			if(val.equals("eve.holt@reqres.in")) {
				flag=true;
				break;
			}
		}if(flag)
			System.out.println("Pass");
		else
			System.out.println("Fail");
	
	}
}


