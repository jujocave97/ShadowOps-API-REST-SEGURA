package com.es.shadowOps.error.errores;

public class GenericInternalException extends RuntimeException{
    private static final String DESCRIPCION = "Not Authorized (401)";

    public GenericInternalException( String mensaje){
        super(DESCRIPCION+", "+ mensaje);
    }
}
