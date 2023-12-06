package tests;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.HashMap;

public class FirstTest {
	
	int id;

@Test(enabled=true)
	public void postCallSample1() {
		baseURI="https://reqres.in";
		HashMap<String,String> map=new HashMap<>();
		map.put("name", "morpheus");
		map.put("job", "leader");
		
		id= given().
		contentType("application/json").
		body(map).
		when().
		post("/api/users").jsonPath().getInt("id");
		
		//then().
		//statusCode(201).log().all();
	}
	
	@Test(priority=2,dependsOnMethods= {"postCallSample1"})
	public void putMethod1() {
		//baseURI="https://reqres.in";
		HashMap<String,String> map=new HashMap<>();
		map.put("name", "morpheus");
		map.put("job", "Manager");
		
		given().
		contentType("application/JSON").
		body(map).
		when().
		put("https://reqres.in/api/users/"+id).
		then().
		statusCode(200);
	}
	
	@Test(enabled=true,dependsOnMethods= {"putMethod1"})
	public void getMethod1() {
		baseURI="https://reqres.in";
		when().
		get("/api/users/"+2).
		then().
		statusCode(200).body("data.id", equalTo(2));
	}
	
	@Test(enabled=true,dependsOnMethods= {"putMethod1"})
	public void deleteMethod1() {
		baseURI="https://reqres.in";
		when().
		delete("/api/users/"+id).
		then().
		statusCode(204);
	}
}
