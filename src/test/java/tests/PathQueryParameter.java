package tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class PathQueryParameter {
	@Test
public void getCall1() {
	given().
	pathParams("path1","api").
	pathParams("path2","users").
	queryParams("delay",2).
	when().
	get("https://reqres.in/{path1}/{path2}").
	then().
	statusCode(200).
	body("data[0].id",equalTo(1)).log().all().header("Content-Type", "application/json; charset=utf-8");
}
	@Test
	public void validateHeader() {
		Response res=given().
		pathParams("path1","api").
		pathParams("path2","users").
		queryParams("delay",2).
		when().
		get("https://reqres.in/{path1}/{path2}");
		System.out.println("Content Type header value is "+res.getHeader("Content-Type"));
		Headers heads=res.getHeaders();
		for(Header head:heads) {
			System.out.println(head.getName()+" : "+head.getValue());
		}
	}
	
}
