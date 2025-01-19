package unit;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MiscTest {
	
	@Test 
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
	
	
	
	
	
	
	
	
	
}
