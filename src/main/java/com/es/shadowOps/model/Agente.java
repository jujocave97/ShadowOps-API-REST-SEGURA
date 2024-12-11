package com.es.shadowOps.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "agentes")
public class Agente {

    @Id
    private String nombreClave;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String roles;
    private double bounty;

    @OneToMany(mappedBy = "agente", cascade = CascadeType.ALL)
    private List<Asignacion> asignaciones;

    public Agente() {
    }

    public Agente(String nombreClave, String nombre, String password, String roles, double bounty, List<Asignacion> asignaciones) {
        this.nombreClave = nombreClave;
        this.nombre = nombre;
        this.password = password;
        this.roles = roles;
        this.bounty = bounty;
        this.asignaciones = asignaciones;
    }

    public Agente(String nombreClave, String nombre, String password, String roles, double bounty) {
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

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }
}
