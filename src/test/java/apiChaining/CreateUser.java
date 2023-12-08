package apiChaining;

import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;

public class CreateUser {
	
	@Test
	public void createUser(ITestContext context) {
		
		Faker f=new Faker();//This will create random objects
		
		JSONObject jo=new JSONObject();
		jo.put("name",f.name().fullName());
		jo.put("gender", "male");
		jo.put("email", f.internet().emailAddress());
		jo.put("status", "Active");
		
		String token="ac38c931afadaec973f193541cf941b1e021a190515c9cf0c013c7ae08638872";
		
		int id=given().
			headers("Authorization","Bearer "+token).
			contentType(ContentType.JSON).
			body(jo.toString()).
		when().
			post("https://gorest.co.in/public/v2/users").
			jsonPath().getInt("id");
		
		//context.setAttribute("user_id", id);//Scope is Test level
		context.getSuite().setAttribute("user_id", id);//Scope is Suite level
	}

}
