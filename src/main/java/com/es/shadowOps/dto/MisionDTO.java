package com.es.shadowOps.dto;

public class MisionDTO {
    private String nombre;
    private String lugar;
    private String tipo;
    private String descripcion;
    private Double recompensa;

    public MisionDTO() {
    }

    public MisionDTO(String nombre, String lugar, String tipo, String descripcion, Double recompensa) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.recompensa = recompensa;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Double recompensa) {
        this.recompensa = recompensa;
    }
}
