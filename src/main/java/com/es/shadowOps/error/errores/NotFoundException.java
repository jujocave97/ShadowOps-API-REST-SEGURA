package com.es.shadowOps.error.errores;

public class NotFoundException extends RuntimeException{
    private static final String DESCRIPCION = "Not Authorized (401)";

    public NotFoundException( String mensaje){
        super(DESCRIPCION+", "+ mensaje);
    }
}
