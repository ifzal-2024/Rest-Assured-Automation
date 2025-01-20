package product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import base.RestStep;
import dto.Product;
import endpoint.IEndpoint;
import io.restassured.path.json.JsonPath;

public class GetProductsTest extends RestStep{ 
	
	
	
	@BeforeEach
	public void testDataSetup() 
	{
		init();
	}

	
	@Test
	public void getProducts_happyPath() throws Exception  
	{
		
		//Lets make apiGet Call and we know it will return reposnse
		apiGetStep(null, GET_PRODUCTS,null, 200, null); 
		// DTO - Data Table Object
		Product[] products = getResponse().as(Product[].class);
		System.out.println(products[0].getDescription());
		
		JsonPath jPath = JsonPath.from(getResponse().asString());
		System.out.println(jPath.getString("[0].description"));
		
	}
	
	 
}