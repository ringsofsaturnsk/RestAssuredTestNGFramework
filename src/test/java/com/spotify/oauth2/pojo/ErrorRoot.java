package com.spotify.oauth2.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ErrorRoot {

@JsonProperty("error")
private Errorr error;

@JsonProperty("error")
public Errorr getError() {
return error;
}

@JsonProperty("error")
public void setError(Errorr error) {
this.error = error;
}

}