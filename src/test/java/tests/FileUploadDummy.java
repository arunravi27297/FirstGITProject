package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.io.File;
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

public class FileUploadDummy {

	@Test
	public void singleFileUpload() {
		//This to dummy code to execute file upload
		File f=new File("C://path");
		given().
		multiPart("file",f).
		contentType("multiPart/form-data").
		when().
		get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
	}
	
	@Test
	public void multipleFileUpload() {
		//This to dummy code to execute file upload
		File f1=new File("C://path");
		File f2=new File("C://path");
		given().
		multiPart("files",f1).
		multiPart("files",f2).
		contentType("multiPart/form-data").
		when().
		get("http://restapi.adequateshop.com/api/Traveler?page=1");
		
	}
	
}


