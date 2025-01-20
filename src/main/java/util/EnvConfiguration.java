package util;

import java.util.Properties;

import java.io.IOException;
import java.io.InputStream;

public class EnvConfiguration {
	
	private final static String FILE_NAME = "env.properties";
	
	private String url;
	private String dbUrl;
	private String dbUser;
	private String dbPass;
	
	
	public EnvConfiguration() 
	{
		loadProperties();
	}
		
	private void loadProperties() 
	{
		/*
		 * Properties object
		 * Initializing by getClass().getClassLoader().getResourceAsStream("env/configuration.properties")
		 * The above can read either file or folder from .properties
		 *  retrieve key EnvKey.keyname.getKey() 
		 * Initialize the variables (url,dburl etc etc)
		 */
		
		Properties properties = new Properties();
		
		InputStream iStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		
		try {
			properties.load(iStream);
			
			url=properties.getProperty(EnvKey.BASE_URL.getKey());
			dbUrl=properties.getProperty(EnvKey.DB_URL.getKey());
			dbUser = properties.getProperty(EnvKey.DB_USER.getKey());
			dbPass = properties.getProperty(EnvKey.DB_PASS.getKey());
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public String getUrl() {
		return url;
	}
	

	
	public String getDbUrl() {
		return dbUrl;
	}

	
	public String getDbUser() {
		return dbUser;
	}
	
	
	public String getDbPass() {
		return dbPass;
	}
	

	

}
