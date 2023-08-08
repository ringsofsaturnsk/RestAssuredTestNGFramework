package com.spotify.oauth2.utils;

import java.util.Properties;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PropertyUtils {

	public static Properties propertyLoader(String filePath) {
		Properties properties = new Properties();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to load the file:" + filePath);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Properties file not found at the path");
		}
		return properties;
	}

}
