package unit;


import java.net.http.HttpResponse.BodyHandler;
import java.util.List;

import javax.xml.crypto.Data;

import org.apache.http.impl.client.AbstractAuthenticationHandler;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import groovy.util.logging.Log;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetTest_deleteTest {
	
	/*
	 * Lets take the url and break it downn
	 *  https://api.escuelajs.co/api/v1/products
	 *  * https - protocol
	 * api.escuelajs.co - host name / server name / domain
	 * api/v1/products - resource locator / end-point / path
	 * products - resource
	 * 
	 * rest-assured:
	 * - base URL = protocol + server name = https://api.escuelajs.co
	 * - base path = end-point = api/v1/products
	 */
	
	//String baseUrl = "https://api.escuelajs.co/";
	//String getAllProductsEndpoint =   "api/v1/products"; // Base Path
	
	//System.out.println(jPath.getString("page"));
	String baseUrl = "https://reqres.in/";
	String getAllProductsEndpoint = "api/users"; // Base Path
	
	@Test
	@Disabled
	public void getTestwithResponseObject() {
		
		Response response =	RestAssured.given().get("https://api.escuelajs.co/api/v1/products");
		response.prettyPrint();
		int statusCode= response.getStatusCode();
		System.out.println("Status code is: ["+ statusCode + "]");
		System.out.println(String.format("Status Code : [%s]", statusCode));
		//System.out.println(String.format("Status Code : [%s]" , response.getStatusCode()));
		System.out.println(String.format("Status Code : [%s] - %s" , response.getStatusCode(), "Success"));
	}
	
	@Test
	@Disabled
		public void path() {
		Response response = RestAssured.given().baseUri(baseUrl) 
				.basePath(getAllProductsEndpoint)
				.when()
				.log().all()	
				.get();
		//response.prettyPrint();
		System.out.println(response.asPrettyString());
		JsonPath jPath= JsonPath.from(response.asString());
	System.out.println(jPath.getInt("data.id[2]"));
	System.out.println(jPath.getInt("page"));
	System.out.println(jPath.getString("support"));
	}
	
	
	
	@Test
	@Disabled
	public void getTestinOneGo() {
		RestAssured.given().baseUri(baseUrl) // Pre-Condition
			.basePath(getAllProductsEndpoint)
			.when()
			.log().all()	//Request Log
			.get() // Action
			.then()
			.log().all()//Response log
			.statusCode(200) // Validation
		.body("data.id[2]",Matchers.equalTo(2)); // Validation
		
			}
	
	
	@Test
	@Disabled
	public void singleGetTest() {
		Header header =new Header("Content-Length", "82");
		String baseUrl = "https://api.escuelajs.co/";
		String endpoint = "api/v1/products/{id}";
		
		int productId= 8;
		RestAssured.given().baseUri(baseUrl)
		.basePath(endpoint)
		.pathParam("id", productId) // pathparam(StringParamName, objectValue)---from pop up 
		.queryParam("category", "Hoodie")
		.header(header)// heasder(Header header) from pop UP
		.auth().preemptive().basic("username", "password")
		.when()
		.log().all()
		.get()
		.then()
		.log().all()
		.statusCode(200);
		
			
	}
	
	
	@Test
		public void singleDeleteTest() 	{
		String baseUrlString = "https://api.escuelajs.co/";
		String endpoint = "api/v1/products/{id}";
		
		int productId = 8888;
		
		RestAssured.given().baseUri(baseUrlString)
			.basePath(endpoint)
			.pathParam("id", productId)
			.when()
			.log().all()
			.delete()
			.then()
			.log().all()
			.statusCode(200);
	}
	
	
	
	
	

}
