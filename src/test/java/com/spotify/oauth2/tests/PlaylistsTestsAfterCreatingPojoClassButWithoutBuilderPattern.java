package com.spotify.oauth2.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.spotify.oauth2.pojo.ErrorRoot;
import com.spotify.oauth2.pojo.Errorr;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.api.SpecBuilder;


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


public class PlaylistsTestsAfterCreatingPojoClassButWithoutBuilderPattern {
	
	
	
	
	@Test
	public void shouldBeAbleToCreatePlayList() {
		Playlist requestPlaylist = new Playlist().setName("Playlist By Spec Builder as separate and Pojo class usage Script")
		.setDescription("Pojo Code created first playlist").setPublic(false);
		
		Playlist responsePlaylist =given(SpecBuilder.getRequestSpec()).body(requestPlaylist).when()
		.post("/users/31as6vbffn66s54gznqe5vicq5oa/playlists")
		.then()
		.extract()
		.response().as(Playlist.class);
		assertThat(responsePlaylist.getName(),equalTo(requestPlaylist.getName()));
		assertThat(responsePlaylist.getDescription(),equalTo(requestPlaylist.getDescription()));
		
	}
	
	
	@Test
	public void shouldBeAbleToGetPlayList() {
		Playlist requestPlaylist = new Playlist();
		Playlist responsePlaylist =given(SpecBuilder.getRequestSpec())
		.when().get("/playlists/6QFLMKE2CToS7cislSz8Ns")
		.then().extract()
		.response().as(Playlist.class);
		assertThat(responsePlaylist.getName(),equalTo(requestPlaylist.getName()));
	}
	
	@Test
	public void shouldBeAbleToUpdateAPlayList() {
		Playlist requestPlaylist = new Playlist();
		requestPlaylist.setName("Playlist By Pojo class usage Script");
		requestPlaylist.setDescription("Pojo Code created first playlist is now Updated by Script");
		requestPlaylist.setPublic(false);
		
		Playlist responsePlaylist=given(SpecBuilder.getRequestSpec())
		.body(requestPlaylist)
		.when()
		.put("/playlists/6QFLMKE2CToS7cislSz8Ns")
		.then()
		.extract().response().as(Playlist.class);
		
	}
	
	@Test
	public void shouldNotBeAbleToCreatePlayListWithoutName() {
		Playlist requestPlaylist = new Playlist();
		requestPlaylist.setName("");
		requestPlaylist.setDescription("Pojo Code created first playlist with empty name");
		requestPlaylist.setPublic(false);
				
		Errorr error=given(SpecBuilder.getRequestSpec()).body(requestPlaylist).when()
		.post("/users/31as6vbffn66s54gznqe5vicq5oa/playlists")
		.then()
		.extract().response().as(Errorr.class);
		assertThat(error.getMessage(),equalTo("Missing required field: name"));
		assertThat(error.getStatus(),equalTo(400));
		
	}

	@Test
	public void shouldNotBeAbleToCreatePlayListWithInvalidToken() {
		String payload="{\r\n"
				+ "    \"name\": \"Playlist By Script\",\r\n"
				+ "    \"description\": \"Code created first playlist\",\r\n"
				+ "    \"public\": false\r\n"
				+ "}";
		Errorr error=given()
		.baseUri("https://api.spotify.com").basePath("/v1").contentType(ContentType.JSON)
		.header("Authorization", "Bearer 123inValidToken456boom").log().all()
		.body(payload).when()
		.post("/users/31as6vbffn66s54gznqe5vicq5oa/playlists")
		.then()
		.extract().response().as(Errorr.class);
		assertThat(error.getMessage(),equalTo("Invalid access token"));
		assertThat(error.getStatus(),equalTo(401));
		
	}
}
