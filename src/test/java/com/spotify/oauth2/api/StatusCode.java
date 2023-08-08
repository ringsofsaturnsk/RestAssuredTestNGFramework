package com.spotify.oauth2.api;

public enum StatusCode {
	CODE_201(201,""),
	CODE_401(401,"Invalid access token");
	
	private final int code;
	private final String msg;
	
	StatusCode(int code, String msg){
		this.code=code;
		this.msg=msg;
	}
	
	public int getCode() {
		return code;
	}
	 public String getMsg() {
		 return msg;
	 }

}
