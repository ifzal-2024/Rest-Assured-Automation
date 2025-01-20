package base;

import java.util.ArrayList;
import java.util.List;

import endpoint.IEndpoint;
import groovyjarjarpicocli.CommandLine.IFactory;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import util.EnvConfiguration;

public abstract class RestStep implements IEndpoint
{
	
	RequestSpecification request;
	Response response;
	
	EnvConfiguration envConfiguration = new EnvConfiguration();

	public void init() {
		request = RestAssured.given(); 
		//request=RestAssured.reset();
		setBaseUrl();
		
	}
	
	/**
	 * Get Response object {@link #response}
	 * @return
	 */
	public Response getResponse() 
	{
		return response;
	}
			
	
	public void setBaseUrl() {
		request.baseUri(envConfiguration.getUrl());
	}
	
	public void setHeaders(Headers headers)
	{
	if (headers !=null)
	{
		
		request.headers(headers);
	}
	}
	
	public void setHeaders(Header... headerArr ) {
		
		for (Header header: headerArr) // We use For loop --> Inside Header Array (headerArr )... add all the header one by one Header: header
		{
			setHeader(header);// We call setHeader (Header header) method from below 
			// request will add header one by one by iterating. 
		}
	}
	
	public void setHeader(Header header) {
		
		request.header(header);
	}
	
	public void setEndpoint(String endpoint) 
	{
		request.basePath(endpoint); // Add Endpoint in the basPath
	}

	public void setParams(String endpoint, Object[] params) throws Exception {
		if (params != null) {
			// api/users/{id}/accounts/{accountId}
			// .pathParam("id", 300)
			// .pathParam("accountId", "38829020")

			List<String> paramNames = new ArrayList<String>();

			String[] arr = endpoint.split("/");

			// (1)FindOut the expected params from the end-point
			for (String s : arr) {
				if (s.startsWith("{") && s.endsWith("}")) {
					paramNames.add(s.replaceAll("[^A-Za-z0-9]", s));

				}
				// (3)validate if expected params to equal to actual param
				if (params.length != paramNames.size()) {
					throw new Exception("Given params are not mtching with endpint expected params");
				}
				int index = 0;
				// (2)Assign params
				for (Object param : params) // Object param from params of object
				{
					request.pathParam(paramNames.get(index), param);
					index++;
				}
			}
		}
	}
	
	
	
	
	
	
	//in Get call we need header,endpoint,param, statusCode, StatusMessage
	// If you need more things you can add 
	public Response apiGetStep(Headers headers, String endpoint, Object[] params, 
			int stausCode, String statusMessage) throws Exception  { 
				/*
		 * Headers - DONE
		 * End-point - DONE
		 * Params - DONE
		 * statusCode - DONE
		 * statusMessage - 
		 */
		
		setHeaders(headers);
		setEndpoint(endpoint);
		
		
		setParams(endpoint, params);
	
		response = request.log().all().get();
		response.then().log().all();
		
		validateStatusCode(stausCode);
		 validateStatusLine(statusMessage);
		
		return response;
		
		
		
		}
	
	
	public Response apiPostStep(Headers headers, String endpoint, Object body, Object[] params, 
			int stausCode, String statusMessage) throws Exception 
	{	
		setHeaders(headers);
		setEndpoint(endpoint);
		setParams(endpoint, params);
		
		response = request.log().all().get();
		response.then().log().all();
		
		validateStatusCode(stausCode);
		validateStatusLine(statusMessage);
		
		return response;
	}
	
	
	
	/*
	 * validate Status Code
	 * @param expectedSTatusCOde
	 */
	
	public void validateStatusCode(int expectedStatusCode) 
	{
		response.then().statusCode(expectedStatusCode);
		
	}
	
	/**
	 * Validate Status Line
	 * @param expectedLine String
	 */
	public void validateStatusLine(String expectedLine) 
	{
		if(expectedLine !=null)
		{

		response.then().statusLine(expectedLine);
	
	}
	
	}

}
