package com.es.shadowOps.dto;

public class AgenteDTO {
    private String nombreClave;
    private String roles;
    private double bounty;

    public AgenteDTO() {
    }

    public AgenteDTO(String nombreClave, String roles, double bounty) {
        this.nombreClave = nombreClave;
        this.roles = roles;
        this.bounty = bounty;
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

    public String getNombreClave() {
        return nombreClave;
    }

    public void setNombreClave(String nombreClave) {
        this.nombreClave = nombreClave;
    }
}
