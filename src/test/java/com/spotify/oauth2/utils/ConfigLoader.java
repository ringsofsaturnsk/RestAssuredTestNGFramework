package com.spotify.oauth2.utils;

import java.util.Properties;


public class ConfigLoader {
	
	private final Properties properties; // the aim here to load the properties file only once
	private static ConfigLoader configLoader;
	
	private ConfigLoader() {
		properties=PropertyUtils.propertyLoader("src/test/resources/config.properties");
	}
	
	public static ConfigLoader getInstance(){ //this method is to create instance of this class only once- aiming to load properties file only once
		if(configLoader==null) {
			configLoader = new ConfigLoader();
		}
		
			return configLoader;

	}
	
	
	//Now creating loader methods to load the properties
	public String getClientId() {
		String prop = properties.getProperty("client_id");
		if(prop!=null) {
			return prop;
		}
		else {
			throw new RuntimeException("Client id is not found in properties file");
		}
	}
	public String getclient_secret() {
		String prop = properties.getProperty("client_secret");
		if(prop!=null) {
			return prop;
		}
		else {
			throw new RuntimeException("client_secret is not found in properties file");
		}
	}
	
	public String getgrant_type() {
		String prop = properties.getProperty("grant_type");
		if(prop!=null) {
			return prop;
		}
		else {
			throw new RuntimeException("grant_type is not found in properties file");
		}
	}
	
	public String getrefresh_token() {
		String prop = properties.getProperty("refresh_token");
		if(prop!=null) {
			return prop;
		}
		else {
			throw new RuntimeException("refresh_token is not found in properties file");
		}
	}
	
	
	

}
