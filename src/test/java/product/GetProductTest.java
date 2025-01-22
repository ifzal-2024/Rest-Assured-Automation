package product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import base.ApiGetStep;

import dto.Product;
import endpoint.IEndpoint;


public class GetProductTest 
{
	
	/*
	 * 1. Happy Path / Positive Scenario -  Priority 1 : 200
	 * 2. Negative - Entity not available : 400 / 404 not found
	 * 3. Negative - param missing
	 * 4. param null
	 * 5. param empty
	 * 6. no auth
	 * 7. missing auth
	 */
	
	/*
	 * Test Data - DONE
	 * Request build - DONE
	 * response parse / mapping
	 * assertion
	 */
	
	static int productId;
	
	@BeforeAll
	public static void testSetUp() throws Exception 
	{
		/*
		 * GetProducts API request
		 * response mapping > list / array of product
		 * list of ids
		 * randomize the ids
		 * random id = productId
		 */
		
		
	ApiGetStep getProductsStep= new ApiGetStep(); 
		
	//GetProducts API Request	
	getProductsStep.get(null, IEndpoint.GET_PRODUCTS, null, 200, null); 
		
	//Object mapping
	Product[] products =	getProductsStep.getResponse().as(Product[].class); 
	
		
	List<Integer>ids=new ArrayList<Integer>();
		
		
	for (Product product : products) 
	{
		ids.add(product.getId());
	}
		
		//Randomize
	Collections.shuffle(ids);
	
	//Assign 
	productId=ids.get(0);
		
	}
	
	
	
	
	
	@Test
	public void getProductHappyPath() throws Exception 
	{
		ApiGetStep getProductStep = new ApiGetStep();
		getProductStep.get(null, IEndpoint.GET_PRODUCT, new Object[] {productId}, 200, null);
	}
	
	

}
