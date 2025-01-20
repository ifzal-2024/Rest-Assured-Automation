package unit;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import util.EnvConfiguration;
import util.EnvKey;

public class MiscTest {
	
	char i;
	String aString;
	
	
	@Test
	@Disabled
	public void default_value_test() {
		System.out.println(i); // Primitive type 
		System.out.println(aString); // Object 
	}
	
	
	@Test

	public void env_configuration() 
	{
		EnvConfiguration envConfiguration = new EnvConfiguration();
		System.out.println(envConfiguration.getUrl());
	}
	
	
	@Test 
	@Disabled
	public void stringSplitTest() {
		String endpoint = "api/users/{id}/accounts/{accountId}";
		String[] arr = endpoint.split("/");
		
		List<String>params=new ArrayList<String>();
		
		for(String s : arr) {
			
			if(s.startsWith("{") &&  s.endsWith("}"))
			{
				params.add(s.replaceAll("[^A-Za-z0-9]", ""));
				System.out.println(s.replaceAll("[^A-Za-z0-9]", ""));
		}
			
		}
		
		}
	
	
	@Test
	@Disabled
	public void envKeyTest() {
		
		//EnvKey.BASE_URL.name();
		//System.out.println(EnvKey.BASE_URL.name());
		
EnvKey[] envKeys = EnvKey.values();
		
		for(EnvKey key : envKeys) 
		{
			//System.out.println(key.getKey());
			
		}
		System.out.println(EnvKey.BASE_URL.getKey());
		
	
	}
	
	
	
	
	
	
}
