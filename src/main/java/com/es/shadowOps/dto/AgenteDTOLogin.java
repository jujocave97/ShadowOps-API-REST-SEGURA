package com.es.shadowOps.dto;

public class AgenteDTOLogin {
    private String nombreClave;
    private String password;

    public AgenteDTOLogin() {
    }

    public AgenteDTOLogin(String nombreClave, String password) {
        this.nombreClave = nombreClave;
        this.password = password;
    }

    public String getNombreClave() {
        return nombreClave;
    }

    public void setNombreClave(String nombreClave) {
        this.nombreClave = nombreClave;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
