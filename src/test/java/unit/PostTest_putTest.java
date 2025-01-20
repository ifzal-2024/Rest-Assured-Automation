package unit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import dto.User;
import groovy.json.JsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostTest_putTest {
	String baseUrl = "https://reqres.in/";
	String basePath = "api/users"; // Base Path
	
	String body = "{\"first_name\":\"John\",\"job\":\"qa\"}";
	
	Map<String, Object> userMap = new HashMap<String, Object>();
	
	
	@Test
	//@Disabled
	public void postTestOneGo_pojo_object_dto() 
	{
		// Test Data creation/preparation
		User user =new User();
		user.setFirstName("Fatema"); 
		user.setJob("housewife");
		
		//Request building
			Response response =	RestAssured.given() 
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(user)
			.contentType(ContentType.JSON) 
			.when()
			.log().all()
			//.post(baseUrl + basePath)
			.post();
			response.then().log().all();			
			Assertions.assertEquals(201, response.getStatusCode());
			
			//Object Mapping to our POJO Class
			User actuaUser = response.as(User.class);
			
			//Assertion
			Assertions.assertEquals(user.getFirstName(), actuaUser.getFirstName(),"Name did not match");
			
			Assertions.assertEquals(user.getJob(), actuaUser.getJob());
			
	}
	
	
	
	@Test
	@Disabled
	public void postTestOneGo_string_body() {
		RestAssured.given()
		.baseUri(baseUrl)
		.basePath(basePath)
		.body(body) // .body(StringBody) from PopUP --- String type
		.contentType(ContentType.JSON)// contentTyoe(content Type Conent Type) from pop up-- (ContentTYpe-Enum)--JSON  
		.when()
		.log().all()
		//.post(baseUrl + basePath) 
		.post()
			.then()
			.log().all()
		.statusCode(201)
		.body("first_name", Matchers.equalTo("Joh")); 
			}
	
	@Test
	@Disabled
	public void postTestOneGo_map_body() 
	{
		userMap.put("first_name", "Jean");
		userMap.put("job", "lead");
		
		RestAssured.given()
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(userMap)
			.contentType(ContentType.JSON)
			.when()
			.log().all()
			//.post(baseUrl + basePath)
			.post()
			.then()
			.log().all()
			.statusCode(201)
			.body("first_name", Matchers.equalTo("Jean"))
		  .body("job",Matchers.equalTo("lead"));
		
	}
	
	
	
	
	@Test
	@Disabled
	public void postTestOneGo_json_body() 
	{
		File file = new File("src/test/resources/user.json");
			RestAssured.given()
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(file)
			.contentType(ContentType.JSON)
			.when()
			.log().all()
			//.post(baseUrl + basePath)
			.post()
			.then()
			.log().all()
			.statusCode(201)
			.body("first_name", Matchers.equalTo("frost"));
	}
	
	
	private static String jsonBuilder() {
		JSONObject json = new JSONObject();
		json.put("first_name", "eva");
		json.put("job", "supervisor");
		
		return json.toString();	} 
	
	@Test
	@Disabled
	public void postTestOneGo_json_builder() 
	{
		
			RestAssured.given()
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(jsonBuilder())
			.contentType(ContentType.JSON)
			.when()
			.log().all()
			//.post(baseUrl + basePath)
			.post()
			.then()
			.log().all()
			.statusCode(201)
			.body("first_name", Matchers.equalTo("eva"));
	}
	
	
	
	
	@Test
	@Disabled
		public void putTestOneGo() 
	{
		//String basePath = "api/users/{id}/accounts/{accountId}"; // in Industry They also add Multiple pathparam, id accounts together.
		
		String basePath = "api/users/{id}";
		
		RestAssured.given()
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(body)
			.pathParam("id", 300)
			.contentType(ContentType.JSON)
			.when()
			.log().all()
			.put()
			.then()
			.log().all()
			.statusCode(200)
			.body("first_name", Matchers.equalTo("John"));
	}
	
	
	


	@Test
	@Disabled
	public void patchTest() {
		String basePath = "api/users/{id}";
		String body = "{\"job\":\"qa\"}";
		
		RestAssured.given()
			.baseUri(baseUrl)
			.basePath(basePath)
			.body(body)
			.pathParam("id", 300)
			.contentType(ContentType.JSON)
			.when()
			.log().all()
			.patch()
			.then()
			.log().all()
			.statusCode(200)
			.body("job", Matchers.equalTo("qa"));
	}
	

	
	
	//day01
	

}
