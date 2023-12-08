package apiChaining;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class UpdateUser {
	
	@Test
	public void createUser(ITestContext context) {
		
		Faker f=new Faker();//This will create random objects
		
		JSONObject jo=new JSONObject();
		jo.put("name",f.name().fullName());
		jo.put("gender", "female");
		jo.put("email", f.internet().emailAddress());
		jo.put("status", "inactive");
		
		String token="ac38c931afadaec973f193541cf941b1e021a190515c9cf0c013c7ae08638872";
		
		//int id=(int) context.getAttribute("user_id");
		int id=(int) context.getSuite().getAttribute("user_id");
		given().
			headers("Authorization","Bearer "+token).
			contentType(ContentType.JSON).
			pathParams("id",id).
			body(jo.toString()).
		when().
			put("https://gorest.co.in/public/v2/users/{id}").
		then().
			statusCode(200).log().body().
			body("status",equalTo("inactive"));
		
	}

}
