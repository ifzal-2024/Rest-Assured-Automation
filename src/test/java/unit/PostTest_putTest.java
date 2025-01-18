package unit;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostTest_putTest {
	String baseUrl = "https://reqres.in/";
	String basePath = "api/users"; // Base Path
	
	String body = "{\"first_name\":\"John\",\"job\":\"qa\"}";
	
	
	
	@Test
	@Disabled
	public void postTestOneGo() {
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
