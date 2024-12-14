package com.es.shadowOps.error.errores;

public class DuplicatedException extends RuntimeException{
    private static final String DESCRIPCION = "Conflict (409)";

    public DuplicatedException( String mensaje){
        super(DESCRIPCION+", "+mensaje);
    }
}
