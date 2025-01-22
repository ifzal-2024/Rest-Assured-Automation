package product;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import base.ApiGetStep;

import dto.Product;
import endpoint.IEndpoint;
import io.restassured.path.json.JsonPath;

public class GetProductsTest { 
	
	/*
	 * Ques - How you parse response from an API request?
	 * Ans - We have created DTO (Data Table Object) / POJO (Plain Old Java Object)
	 * from swagger (APIM) schema. then we do object mapping from response
	 * 
	 */
	
	


	
	@Test
	public void getProducts_happyPath() throws Exception  
	{
		ApiGetStep step=new ApiGetStep();
		
	
		//Lets make apiGet Call and we know it will return reposnse
		step.get(null, IEndpoint.GET_PRODUCTS,null, 200, null); 
		
		
		// DTO - Data Table Object
		Product[] products = step.getResponse().as(Product[].class);
		System.out.println(products[0].getDescription());
		
		//Array To List
		List<Product>listOfProducts = Arrays.asList(products);
		System.out.println(listOfProducts.get(0).getDescription());
		
		//JsonPath
		JsonPath jPath = JsonPath.from(step.getResponse().asString());
		System.out.println(jPath.getString("[0].description")); 
		
	}
	
	 
}