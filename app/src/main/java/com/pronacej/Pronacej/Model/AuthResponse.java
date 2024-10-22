package com.pronacej.Pronacej.Model;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("id")
    Long id;
    @SerializedName("token")
    String token;
    @SerializedName("typeUserId")
    Integer typeUserId;
    @SerializedName("name")
    String name;
    @SerializedName("lastName")
    String lastName;

    public AuthResponse() {
    }

    public AuthResponse(Long id, String token, Integer typeUserId, String name, String lastName) {
        this.id = id;
        this.token = token;
        this.typeUserId = typeUserId;
        this.name = name;
        this.lastName = lastName;
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

    public Integer getTypeUserId() {
        return typeUserId;
    }

    public void setTypeUserId(Integer typeUserId) {
        this.typeUserId = typeUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
