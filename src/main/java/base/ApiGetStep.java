package base;

import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ApiGetStep extends RestStep {
	
	
	public ApiGetStep() 
	{
		init();
		 
	}
	
	
	
	/**
	 * Build a GET API Request
	 * @param headers {@link Headers}
	 * @param endpoint API End-point
	 * @param params Path Params
	 * @param stausCode expected status code
	 * @param statusMessage expected status message
	 * @return {@link #response}
	 * @throws Exception if path params do not have same size at end-point
	 */
	//in Get call we need header,endpoint,param, statusCode, StatusMessage
	// If you need more things you can add 
	public Response get(Headers headers, String endpoint, Object[] params, 
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
	

}
