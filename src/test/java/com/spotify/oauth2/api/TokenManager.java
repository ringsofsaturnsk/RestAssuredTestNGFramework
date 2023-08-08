package com.spotify.oauth2.api;

import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.utils.ConfigLoader;

public class TokenManager {

	public static String renewToken() {

		HashMap<String, String> formparams = new HashMap<String, String>();
		formparams.put("client_id", ConfigLoader.getInstance().getClientId());
		formparams.put("client_secret", ConfigLoader.getInstance().getclient_secret());
		formparams.put("grant_type", ConfigLoader.getInstance().getgrant_type());
		formparams.put("refresh_token",
				ConfigLoader.getInstance().getrefresh_token());
		Response response = RestResource.postAccount(formparams);
		if (response.getStatusCode() != 200) {
			throw new RuntimeException("Token not generated");
		} else {
			return response.path("access_token");
		}
	}

}
