package com.pronacej.Pronacej.Utils;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CjdrService {

    @GET("/pronacej/v1/cj/showTD")
    Call<List<Map<String, Object>>> obtenerTD(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin,
            @Query("incluirEstadoIng") Boolean incluirEstadoIng
    );

    @GET("/pronacej/v1/cj/showIE")
    Call<List<Map<String, Object>>> obtenerIE(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin,
            @Query("incluirEstadoIng") Boolean incluirEstadoIng
    );

    @GET("/pronacej/v1/cj/showIL")
    Call<List<Map<String, Object>>> obtenerIL(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin,
            @Query("incluirEstadoIng") Boolean incluirEstadoIng
    );

    @GET("/pronacej/v1/cj/showIC")
    Call<List<Map<String, Object>>> obtenerIC(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin,
            @Query("incluirEstadoIng") Boolean incluirEstadoIng
    );

    @GET("/pronacej/v1/cj/showPopulation")
    Call<List<Map<String,Object>>> obtenerePopulation(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin,
            @Query("incluirEstadoIng") Boolean incluirEstadoIng
    );

    // Reportes Diario CJDR
    @GET("pronacej/v1/dailyCjdr/showReportCjdr")
    Call<List<Map<String, Object>>> obtenerReporteDiarioCjdr(
            @Query("fecha_seleccionada")String fecha_seleccionada);

    // Reporte de Edad Simple CJDR
    @GET("/pronacej/v1/cj/showEdadSimple")
    Call<List<Map<String,Object>>> obtenerEdadSimpleCjdr(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin,
            @Query("incluirEstadoIng") Boolean incluirEstadoIng
    );
}
