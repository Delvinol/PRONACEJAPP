package com.pronacej.Pronacej.Utils;

import com.pronacej.Pronacej.Model.AuthRequest;
import com.pronacej.Pronacej.Model.AuthResponse;
import com.pronacej.Pronacej.Model.RegisterRequest;
import com.pronacej.Pronacej.Model.RegisterResponse;
import com.pronacej.Pronacej.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/api/v1/auth/authenticate")
    Call<AuthResponse> authenticate(@Body AuthRequest authRequest);

    @POST("/api/v1/auth/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @GET("api/v1/auth/findAllUser")
    Call<List<User>> getAllUsers();
}
