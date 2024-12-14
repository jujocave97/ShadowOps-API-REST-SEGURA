package com.es.shadowOps.dto;

public class AsignacionDTOInsert {
    private String agente;
    private String mision;
    private int duration;

    public AsignacionDTOInsert(String agente, String mision, int duration) {
        this.agente = agente;
        this.mision = mision;
        this.duration = duration;
    }

    public AsignacionDTOInsert() {
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
}
