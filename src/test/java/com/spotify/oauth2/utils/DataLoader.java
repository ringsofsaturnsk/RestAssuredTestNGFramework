package com.spotify.oauth2.utils;

import java.util.Properties;


public class DataLoader {
	
	private final Properties properties; // the aim here to load the properties file only once
	private static DataLoader dataLoader;
	
	private DataLoader() {
		properties=PropertyUtils.propertyLoader("src/test/resources/data.properties");
	}
	
	public static DataLoader getInstance(){ //this method is to create instance of this class only once- aiming to load properties file only once
		if(dataLoader==null) {
			dataLoader = new DataLoader();
		}
		
			return dataLoader;

	}
	
	
	//Now creating loader methods to load the properties
	public String getgetPlaylistID() {
		String prop = properties.getProperty("getPlaylistID");
		if(prop!=null) {
			return prop;
		}
		else {
			throw new RuntimeException("getPlaylistID is not found in properties file");
		}
	}
	public String getupdatePlaylistID() {
		String prop = properties.getProperty("updatePlaylistID");
		if(prop!=null) {
			return prop;
		}
		else {
			throw new RuntimeException("updatePlaylistID is not found in properties file");
		}
	}
	
	
	
	

}
