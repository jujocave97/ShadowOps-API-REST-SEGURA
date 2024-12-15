package com.es.shadowOps.error.errores;

public class NotAcceptableException extends RuntimeException{
    private static final String DESCRIPCION = "Not Authorized (401)";

    public NotAcceptableException(String mensaje){
        super(DESCRIPCION+", "+ mensaje);
    }
}
