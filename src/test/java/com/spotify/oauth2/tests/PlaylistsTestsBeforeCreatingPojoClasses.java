package com.spotify.oauth2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.tools.SimpleJavaFileObject;

import org.hamcrest.core.IsEqual;
import org.hamcrest.text.IsEqualCompressingWhiteSpace;
import org.hamcrest.text.MatchesPattern.*;

import org.hamcrest.*;
import org.testng.annotations.BeforeClass;


import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.MatcherAssert.assertThat;


public class PlaylistsTestsBeforeCreatingPojoClasses {
	
	RequestSpecification requestSpecification;
	ResponseSpecification responseSpecfication;
	String access_token="BQBSV2K1QOMnYBueFxIxwdb8G-dC1EkGhA1JEMytbBqvefsnjnXO6VQbspgESnpg4bw2POeKpjpM5VYE8EGwW6uN8jAU9Wvbl61kXNaefuT5o5MJndm7ciqyT0ywTQudn5Ly2TzO39yRfqQp3sxOwjyYZ8eKcctxqpCZpMU_NxgKZQOkViTv0MsXJ1pypNI0oe3MEK7HCvydanAj3pbtnTFlTSsLZM6IFlj7q3vKyD_oihO-flBrnpVM0J2kwBJw_32iZd0AkariItpy";
	
	@BeforeClass
	public void beforeClass(){
		RequestSpecBuilder reqspec= new RequestSpecBuilder();
		reqspec.setBaseUri("https://api.spotify.com").setBasePath("/v1").setContentType(ContentType.JSON)
		.addHeader("Authorization", "Bearer "+access_token).log(LogDetail.ALL);
		
		requestSpecification= reqspec.build();
		
		ResponseSpecBuilder resspec= new ResponseSpecBuilder();
		resspec.expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		responseSpecfication= resspec.build();
		
	}
	
	@Test
	public void shouldBeAbleToCreatePlayList() {
		String payload="{\r\n"
				+ "    \"name\": \"Playlist By Script\",\r\n"
				+ "    \"description\": \"Code created first playlist\",\r\n"
				+ "    \"public\": false\r\n"
				+ "}";
		given(requestSpecification).body(payload).when()
		.post("/users/31as6vbffn66s54gznqe5vicq5oa/playlists")
		.then()
		.spec(responseSpecfication)
		.assertThat().statusCode(201)
		.body("name", equalTo("Playlist By Script"),"description",equalTo("Code created first playlist"));
	}
	
	
	@Test
	public void shouldBeAbleToGetPlayList() {
		given(requestSpecification)
		.when().get("/playlists/6QFLMKE2CToS7cislSz8Ns")
		.then().spec(responseSpecfication).assertThat().statusCode(200)
		.body("name",equalTo("Playlist By Script"));
	}
	
	@Test
	public void shouldBeAbleToUpdateAPlayList() {
		String payload="{\r\n"
				+ "    \"name\": \"Playlist By Script\",\r\n"
				+ "    \"description\": \"Code created first playlist is now Updated by Script\",\r\n"
				+ "    \"public\": false\r\n"
				+ "}";
		given(requestSpecification)
		.body(payload)
		.when()
		.put("/playlists/6QFLMKE2CToS7cislSz8Ns")
		.then()
		.spec(responseSpecfication)
		.assertThat().statusCode(200)
		.body("description", equalTo("Code created first playlist is now Updated by Script"));
	}
	
	@Test
	public void shouldNotBeAbleToCreatePlayListWithoutName() {
		String payload="{\r\n"
				+ "    \"name\": \"\",\r\n"
				+ "    \"description\": \"Code created first playlist\",\r\n"
				+ "    \"public\": false\r\n"
				+ "}";
		given(requestSpecification).body(payload).when()
		.post("/users/31as6vbffn66s54gznqe5vicq5oa/playlists")
		.then()
		.spec(responseSpecfication)
		.assertThat()
		.body("error.status", equalTo(400),"error.message",equalTo("Missing required field: name"));
	}

	@Test
	public void shouldNotBeAbleToCreatePlayListWithInvalidToken() {
		String payload="{\r\n"
				+ "    \"name\": \"Playlist By Script\",\r\n"
				+ "    \"description\": \"Code created first playlist\",\r\n"
				+ "    \"public\": false\r\n"
				+ "}";
		given()
		.baseUri("https://api.spotify.com").basePath("/v1").contentType(ContentType.JSON)
		.header("Authorization", "Bearer 123inValidToken456boom").log().all()
		.body(payload).when()
		.post("/users/31as6vbffn66s54gznqe5vicq5oa/playlists")
		.then()
		.spec(responseSpecfication)
		.assertThat()
		.body("error.status", equalTo(401),"error.message",equalTo("Invalid access token"));
	}
}
