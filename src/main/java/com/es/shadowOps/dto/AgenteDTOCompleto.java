package com.es.shadowOps.dto;

public class AgenteDTOCompleto {
    private String nombreClave;
    private String nombre;
    private String password;
    private String roles;
    private double bounty;
    public AgenteDTOCompleto() {
    }

    public AgenteDTOCompleto(String nombreClave, String nombre, String password, String roles) {
        this.nombreClave = nombreClave;
        this.nombre = nombre;
        this.password = password;
        this.roles = roles;
    }

    public AgenteDTOCompleto(String nombreClave, String nombre, String password, String roles, double bounty) {
        this.nombreClave = nombreClave;
        this.nombre = nombre;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
