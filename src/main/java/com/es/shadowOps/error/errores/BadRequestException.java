package com.es.shadowOps.error.errores;

public class BadRequestException extends RuntimeException{
    private static final String DESCRIPCION = "Not Authorized (401)";

    public BadRequestException( String mensaje){
        super(DESCRIPCION+", "+ mensaje);
    }
}
