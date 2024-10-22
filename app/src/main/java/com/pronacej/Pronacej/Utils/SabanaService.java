package com.pronacej.Pronacej.Utils;

import com.pronacej.Pronacej.Model.Sabana;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SabanaService {

    @GET("/pronacej/v1/sabana/findAllSabana")
    Call<List<Sabana>> getSabana();

    @POST("/pronacej/v1/sabana/register")
    Call<Sabana> addSabana(@Body Sabana sabana);

    //@PUT("/pronacej/v1/sabana/edit/{id}")
    //Call<Sabana> putSabana();


}
