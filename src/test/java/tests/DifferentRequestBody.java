package tests;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DifferentRequestBody {
	
int id;

	//Post request body using HashMap
		@Test(enabled=true)
		public void hashMapBody() {
			baseURI="https://reqres.in";
			HashMap<String,String> map=new HashMap<>();
			map.put("name", "morpheus");
			map.put("job", "master");
			
			given().
			contentType("application/json").
			body(map).
			when().
			post("/api/users").
			then().
			body("job",equalTo("master"));
				
		}
	
	//Post request body using org.json
		@Test(enabled=true)
		public void orgJsonBody() {
			baseURI="https://reqres.in";
			JSONObject map=new JSONObject();
			map.put("name", "morpheus");
			map.put("job", "leader");

			given().
			contentType("application/json").
			body(map.toString()).
			when().
			post("/api/users").
			then().
			body("job",equalTo("leader"));
			
		}
		
		//Post request body using POJO class
		@Test(enabled=true)
		public void pojoClassBody() {
			baseURI="https://dummy.restapiexample.com";
			POJOBodyRequest data=new POJOBodyRequest();
				
			data.setAge(22);
			data.setName("Aaron");
			data.setSalary(20000);
			given().
			contentType("application/json").
			body(data).
			when().
			post("/api/v1/create").
			then().
			body("status",equalTo("success")).
			body("data.name",equalTo("Aaron")).
			body("data.age",equalTo(22)).
			body("data.salary",equalTo(20000));
			
		}
				
		//Post request body using External Json
		@Test(enabled=true)
		public void externalJsonBody() throws FileNotFoundException {
			baseURI="https://dummy.restapiexample.com";
			File f=new File(".\\JsonBodyreq.json");
			FileReader fr=new FileReader(f);
			JSONTokener jt=new JSONTokener(fr);
			JSONObject data=new JSONObject(jt);
			given().
			contentType("application/json").
			body(data.toString()).
			when().
			post("/api/v1/create").
			then().log().all().
			body("status",equalTo("success")).
			body("data.name",equalTo("Aaron")).
			body("data.age",equalTo("23")).
			body("data.salary",equalTo("20000"));
			
		}
}
