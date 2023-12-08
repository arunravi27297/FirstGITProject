package tests;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class AuthenticationMethods {
@Test
	public void basicAuthMethod() {
		given().
		auth().basic("postman","password").
		when().get("https://postman-echo.com/basic-auth").
		then().
		statusCode(200).body("authenticated",equalTo(true));
	}

@Test
public void digestAuthMethod() {
	given().
	auth().digest("postman","password").
	when().get("https://postman-echo.com/basic-auth").
	then().
	statusCode(200).body("authenticated",equalTo(true));
}

@Test
public void preemptiveAuthMethod() {
	given().
	auth().preemptive().basic("postman","password").
	when().get("https://postman-echo.com/basic-auth").
	then().
	statusCode(200).body("authenticated",equalTo(true));
}

@Test
public void bearerTokenMethod() {
	
	//Generate token from https://gorest.co.in/ and use it to access their apis
	String token="ac38c931afadaec973f193541cf941b1e021a190515c9cf0c013c7ae08638872";
	given().
	headers("Authorization","Bearer "+token).
	when().get("https://gorest.co.in/public/v2/posts").
	then().
	statusCode(200).log().body();
}

@Test
public void oAuth1Method() {
	
	//This one not working needed working dummy api
	given().
	auth().oauth("consumerkey","consumerSecrat","accessToken","tokenSecrat").//These details provided by developers
	when().get("URL").
	then().
	statusCode(200);
}
@Test
public void oAuth2Method() {
	
	//This one not working needed working dummy api
	given().
	auth().oauth2("Token").
	when().get("URL").
	then().
	statusCode(200);
}
@Test
public void apiKeyMethod() {
	
	//This one not working needed working dummy api
	given().
	queryParams("APIKey","keyValue").//This is based on api key, 1 - Api name, 2 - Api value
	when().get("URL").
	then().
	statusCode(200);
}
}
