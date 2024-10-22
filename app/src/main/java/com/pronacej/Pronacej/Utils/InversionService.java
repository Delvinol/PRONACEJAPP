package com.pronacej.Pronacej.Utils;
import java.util.List;
import java.util.Map;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface InversionService {
    @GET("/api/v1/inversiones/filtrar")
    Call<List<Map<String, Object>>> filtrarInversiones(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechaFin
    );
}
