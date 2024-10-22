package com.pronacej.Pronacej.Utils;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PaspeService {


    @GET("/pronacej/v1/paspe/showTD")
    Call<List<Map<String, Object>>> obtenerTD(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin,
            @Query("incluirEstadoIng") Boolean incluirEstadoIng
    );

    @GET("/pronacej/v1/paspe/showIE")
    Call<List<Map<String, Object>>> obtenerIE(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin,
            @Query("incluirEstadoIng") Boolean incluirEstadoIng
    );

    @GET("/pronacej/v1/paspe/showIL")
    Call<List<Map<String, Object>>> obtenerIL(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin,
            @Query("incluirEstadoIng") Boolean incluirEstadoIng
    );



    @GET("/pronacej/v1/paspe/showPopulation")
    Call<List<Map<String,Object>>> obtenerePopulation(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin,
            @Query("incluirEstadoIng") Boolean incluirEstadoIng
    );

    // Reportes Diario CJDR
    @GET("pronacej/v1/dailyPaspe/showReportPaspe")
    Call<List<Map<String, Object>>> obtenerReporteDiarioCjdr(
            @Query("fecha_seleccionada")String fecha_seleccionada);

    // Reporte de Edad Simple CJDR
    @GET("/pronacej/v1/paspe/showEdadSimple")
    Call<List<Map<String,Object>>> obtenerEdadSimplePaspe(
            @Query("fechaInicio") String fechaInicio,
            @Query("fechaFin") String fechafin,
            @Query("incluirEstadoIng") Boolean incluirEstadoIng
    );
}
