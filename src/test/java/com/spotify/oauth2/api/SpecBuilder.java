package com.spotify.oauth2.api;

import java.util.HashMap;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	static String access_token = "BQC8pxQLblf-OVJ6DPfL7Z2Fjp1DOWN-dXXrrCpO1c0mmSyNU0_74hWz_PfexF4Z5deVuNlZ4iVp0duKdFlmyj5DyzyeKGyFK_iLNf7BOF3i_mo3tuIU43SB8ZlGg_ythQWvfbafRqqksCb9TA2ZxG5SisMdxRp49ePV2Tdh4lcTniofMdnLYJqeZna0AjebaMOWJDRqEe8npnl5f_JHcmOf3F1e9M7jl7FMT1i3IiD2IyJZxDHFBmzGatcQvUX0ebcuWJWQ7rUbumCa";

	public static RequestSpecification getRequestSpec() {
		return new RequestSpecBuilder().setBaseUri("https://api.spotify.com").setBasePath("/v1")
				.setContentType(ContentType.JSON).addFilter(new AllureRestAssured())
				.addHeader("Authorization", "Bearer " + access_token)
				.log(LogDetail.ALL).build();

	}
	public static RequestSpecification getRequestSpecWithoutTokenAndPath() {
		return new RequestSpecBuilder().setBaseUri("https://api.spotify.com")
				.setContentType(ContentType.JSON)
				.log(LogDetail.ALL).build();

	}
	
	public static RequestSpecification getRequestSpecForPostAccount(HashMap<String,String>formparams) {
		return new RequestSpecBuilder().setBaseUri("https://accounts.spotify.com")
				.addFormParams(formparams)
				.setContentType(ContentType.URLENC)
				.log(LogDetail.ALL).build();

	}

	public static ResponseSpecification getResponseSpec() {
		return new ResponseSpecBuilder().expectContentType(ContentType.JSON).log(LogDetail.ALL).build();
	}

}
