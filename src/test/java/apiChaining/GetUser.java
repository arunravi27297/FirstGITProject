package apiChaining;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class GetUser {
	
	@Test
	public void createUser(ITestContext context) {
		
				
		String token="ac38c931afadaec973f193541cf941b1e021a190515c9cf0c013c7ae08638872";
		
		//int id=(int) context.getAttribute("user_id");
		int id=(int) context.getSuite().getAttribute("user_id");
		given().
		headers("Authorization","Bearer "+token).
		pathParams("id",id).
		when().
		get("https://gorest.co.in/public/v2/users/{id}").
		then().
		statusCode(200).log().body();
		
	}

}
