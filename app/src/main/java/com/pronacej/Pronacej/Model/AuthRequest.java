package com.pronacej.Pronacej.Model;

import com.google.gson.annotations.SerializedName;

public class AuthRequest {

    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;

    public AuthRequest() {
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
