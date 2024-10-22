package com.pronacej.Pronacej.Utils;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecursoService {

    @GET("/pronacej/v1/recursos/getRegimes")
    Call<List<Map<String, Object>>> getRegimes();

    @GET("/pronacej/v1/recursos/getTrabajadores")
    Call<List<Map<String, Object>>> getTrabajadores();
}
