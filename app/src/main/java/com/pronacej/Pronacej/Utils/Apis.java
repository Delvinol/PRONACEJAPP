package com.pronacej.Pronacej.Utils;

public class Apis {


    public static final String URL_001 = "http://appconsulta.pronacej.gob.pe:8081";
    //public static final String URL_001 = "http://192.168.0.101:8080";


    /// CENTRO JUVENIL CJDR
    public static CjdrService getCjdrService(){
        return Client.getClient(URL_001).create(CjdrService.class);
    }


    // CENTRO JUVENIL SOA
    public static SoaService getSoaService(){
        return Client.getClient(URL_001).create(SoaService.class);
    }


    public static LoginService getApiService(){
        return Client.getClient(URL_001).create(LoginService.class);
    }


    public static PaspeService getPaspeService(){
        return Client.getClient(URL_001).create(PaspeService.class);
    }


    public static InversionService getInversionService(){
        return Client.getClient(URL_001).create(InversionService.class);
    }

    public static RecursoService getRecursoService(){
        return Client.getClient(URL_001).create(RecursoService.class);
    }

    public static SeguridadService getSeguridadService(){
        return Client.getClient(URL_001).create(SeguridadService.class);
    }




}

