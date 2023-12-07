package tests;

import static io.restassured.RestAssured.given;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;


public class SchemaValidation {

	@Test
	public void jsonSchemaValidation() {
		given().
		queryParams("delay",2).
		when().
		get("https://reqres.in/api/users").
		then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema.json"));
	}
	
	@Test
	public void xmlSchemaValidation() {
		given().
		
		when().
		get("http://restapi.adequateshop.com/api/Traveler?page=1").
		then().
		assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchema.xsd"));
	}
	
}


