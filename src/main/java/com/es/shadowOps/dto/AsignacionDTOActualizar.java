package com.es.shadowOps.dto;

public class AsignacionDTOActualizar {
    private String agente;
    private String mision;
    private int duration;
    private String estado;

    public AsignacionDTOActualizar() {
    }

    public AsignacionDTOActualizar(String agente, String mision, int duration, String estado) {
        this.agente = agente;
        this.mision = mision;
        this.duration = duration;
        this.estado = estado;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
