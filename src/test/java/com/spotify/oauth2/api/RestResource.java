package com.spotify.oauth2.api;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import com.spotify.oauth2.pojo.Playlist;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestResource {
	
	public static Response post(String basepath,String access_token,String path,Object requestPlaylist) {
		return given(SpecBuilder.getRequestSpecWithoutTokenAndPath()).body(requestPlaylist)
				.basePath(basepath)
				.auth().oauth2(access_token)				
				.when()
				.post(path) //"/users/31as6vbffn66s54gznqe5vicq5oa/playlists"
				.then().spec(SpecBuilder.getResponseSpec())
				.extract()
				.response();
		
	}
	
	public static Response postAccount(HashMap<String, String> formparams) {
		return given(SpecBuilder.getRequestSpecForPostAccount(formparams)).when().post(Routes.API+Routes.TOKEN).then()
				.spec(SpecBuilder.getResponseSpec()).extract().response();
	}

	public static Response get(String basepath,String access_token,String playlistID) {
		return given(SpecBuilder.getRequestSpecWithoutTokenAndPath())
				.basePath(basepath)
				.header("Authorization", "Bearer " + access_token) // we can remove this line and we can give .auth().oauth2(access_token) 
				.when().get(Routes.PLAYLISTS+"/"+playlistID) //7KrgeQjlyhhS2sElPLbD8W
				
				.then().spec(SpecBuilder.getResponseSpec()).extract()
				.response();
	}
	
	public static Response update(String basepath,String access_token,String playlistID, Object requestPlaylist) {
		return given(SpecBuilder.getRequestSpecWithoutTokenAndPath())
		.body(requestPlaylist)
		.basePath(basepath)
		.header("Authorization", "Bearer " + access_token) // we can remove this line and we can give .auth().oauth2(access_token) 
		.when()
		.put(Routes.PLAYLISTS+"/"+playlistID)
		.then().spec(SpecBuilder.getResponseSpec())
		.extract().response();
	}

}
