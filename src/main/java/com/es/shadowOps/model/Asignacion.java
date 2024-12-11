package com.es.shadowOps.model;

import jakarta.persistence.*;

import java.time.Duration;

// para implementar Duration : asignacion.setTiempoLimite(Duration.ofMinutes(3)); asignacion.setTiempoLimite(Duration.ofHours(1));

@Entity
@Table(name = "asignaciones")
public class Asignacion {

    public enum Estado{
        COMPLETADO, EN_CURSO, FALLIDA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agente_id")
    private Agente agente;

    @ManyToOne
    @JoinColumn(name = "mision_id")
    private Mision mision;

    @Convert(converter = DurationConverter.class)
    private Duration tiempoLimite;

    private Estado estado;

    public Asignacion() {
    }

    public Asignacion(Agente agente, Mision mision, Duration tiempoLimite, Estado estado) {
        this.agente = agente;
        this.mision = mision;
        this.tiempoLimite = tiempoLimite;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public Mision getMision() {
        return mision;
    }

    public void setMision(Mision mision) {
        this.mision = mision;
    }

    public Duration getTiempoLimite() {
        return tiempoLimite;
    }

    public void setTiempoLimite(Duration tiempoLimite) {
        this.tiempoLimite = tiempoLimite;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
