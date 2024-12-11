package com.es.shadowOps.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "misiones")
public class Mision {

    public enum Tipo {
        RESCATE, RECONOCIMIENTO, SABOTAJE, ASALTO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String lugar;
    private Tipo tipo;
    private String descripcion;
    private double recompensa;

    @OneToMany(mappedBy = "mision", cascade = CascadeType.ALL)
    private List<Asignacion> asignaciones;


    public Mision() {
    }

    public Mision(String nombre, String lugar, Tipo tipo, String descripcion, double recompensa, List<Asignacion> asignaciones) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.recompensa = recompensa;
        this.asignaciones = asignaciones;
    }

    public Mision(String nombre, String lugar, Tipo tipo, String descripcion, double recompensa) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.recompensa = recompensa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(double recompensa) {
        this.recompensa = recompensa;
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }
}

