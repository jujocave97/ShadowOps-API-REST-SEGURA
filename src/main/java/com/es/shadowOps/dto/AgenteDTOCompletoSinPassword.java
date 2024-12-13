package com.es.shadowOps.dto;

public class AgenteDTOCompletoSinPassword {
    private String nombreClave;
    private String nombre;
    private String roles;
    private double bounty;
    public AgenteDTOCompletoSinPassword() {
    }

    public AgenteDTOCompletoSinPassword(String nombreClave, String nombre, String roles) {
        this.nombreClave = nombreClave;
        this.nombre = nombre;
        this.roles = roles;
    }

    public AgenteDTOCompletoSinPassword(String nombreClave, String nombre, String roles, double bounty) {
        this.nombreClave = nombreClave;
        this.nombre = nombre;
        this.roles = roles;
        this.bounty = bounty;
    }

    public String getNombreClave() {
        return nombreClave;
    }

    public void setNombreClave(String nombreClave) {
        this.nombreClave = nombreClave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public double getBounty() {
        return bounty;
    }

    public void setBounty(double bounty) {
        this.bounty = bounty;
    }
}
