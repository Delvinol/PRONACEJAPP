package com.pronacej.Pronacej.Model;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("id")
    Long id;
    @SerializedName("token")
    String token;

    public RegisterResponse() {
    }

    public RegisterResponse(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
