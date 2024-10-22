package com.pronacej.Pronacej.Utils;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SeguridadService {

    @GET("/pronacej/v1/seguridad/getReyertas")
    Call<List<Map<String, Object>>> getReyertas();

    @GET("/pronacej/v1/seguridad/getTraslados")
    Call<List<Map<String, Object>>> getTraslados();

    @GET("/pronacej/v1/seguridad/getOperativos")
    Call<List<Map<String, Object>>> getOperativos();

    @GET("/pronacej/v1/seguridad/getMegaOperativos")
    Call<List<Map<String, Object>>> getMegaOperativos();

    @GET("/pronacej/v1/seguridad/getFugas")
    Call<List<Map<String, Object>>> getFugas();
}
