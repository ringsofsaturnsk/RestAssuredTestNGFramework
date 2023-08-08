package com.spotify.oauth2.applicationApi;

import static io.restassured.RestAssured.given;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.Routes;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlist;

import io.restassured.response.Response;

public class PlaylistApi {
	static String playlistBasepath = "/v1";
	static String playlistPath = Routes.USERS+"/31as6vbffn66s54gznqe5vicq5oa"+Routes.PLAYLISTS;
	static String playlistAccessToken = "BQCt6hJjMhS71K_j1XCipUT4E-aAQmClaXMRWF0Nf1AHf9vqyY3KFrknmk3JQ07xRtidqSl67RpAP-6IfhuNT98Y-KfFh0goLhBvg-a1dTx9Zt09y1I5bGN5e6Ekj2uVbsiGqeTKuhM_ROxBVGRUQQcRqefArIaANbhn7iaJcNR-8-bMuEqbVfYk6VirHKqD7G9g9dE2EokyW6NhdVyv_HEEUUEJqiWThiq82oayKBLk_45iEy0RMktKiFHSnxzJkMdEW3JPOLiSILiy";

	// This is the final method
	public static Response postUsingRestResource(Playlist requestPlaylist) {
		return RestResource.post(Routes.BASE_PATH, TokenManager.renewToken(), playlistPath, requestPlaylist);

	}

	// This is the final method
	public static Response getUsingRestResource(String playlistID) {
		return RestResource.get(Routes.BASE_PATH, TokenManager.renewToken(), playlistID);

	}

	// This is the final method
	public static Response updateUsingRestResource(String playlistID, Playlist requestPlaylist) {
		return RestResource.update(Routes.BASE_PATH, TokenManager.renewToken(), playlistID, requestPlaylist);

	}

	public static Response post(Playlist requestPlaylist) {
		return given(SpecBuilder.getRequestSpec()).body(requestPlaylist).when()
				.post("/users/31as6vbffn66s54gznqe5vicq5oa/playlists").then().spec(SpecBuilder.getResponseSpec())
				.extract().response();

	}

	public static Response get(String playlistID) {
		return given(SpecBuilder.getRequestSpec()).when().get("/playlists/" + playlistID) // 7KrgeQjlyhhS2sElPLbD8W

				.then().spec(SpecBuilder.getResponseSpec()).extract().response();
	}

	public static Response update(String playlistID, Playlist requestPlaylist) {
		return given(SpecBuilder.getRequestSpec()).body(requestPlaylist).when().put("/playlists/" + playlistID).then()
				.spec(SpecBuilder.getResponseSpec()).extract().response();
	}

}
