package com.es.shadowOps.service;

import com.es.shadowOps.dto.AsignacionDTO;
import com.es.shadowOps.dto.AsignacionDTOEstado;
import com.es.shadowOps.model.Agente;
import com.es.shadowOps.model.Asignacion;
import com.es.shadowOps.model.Mision;
import com.es.shadowOps.repository.RepositoryAgente;
import com.es.shadowOps.repository.RepositoryAsignacion;
import com.es.shadowOps.repository.RepositoryMision;
import com.es.shadowOps.util.MapperAsignacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAsignacion {
    @Autowired
    private RepositoryAsignacion repositoryAsignacion;
    @Autowired
    private RepositoryAgente repositoryAgente;
    @Autowired
    private RepositoryMision repositoryMision;

    public AsignacionDTO insertarAsignacion(AsignacionDTO asignacionDTO){
        if (asignacionDTO.getAgente().isEmpty() || asignacionDTO.getMision().isEmpty() || asignacionDTO.getDuration() <= 0){
            // throw exception
        }

        Agente a = repositoryAgente.findById(asignacionDTO.getAgente()).orElse(null);
        if( a == null){
            // throw exception
        }

        Mision m = repositoryMision.findByNombre(asignacionDTO.getMision()).orElse(null);
        if( m == null){
            // throw exception
        }

        Duration duration = Duration.ofMinutes(asignacionDTO.getDuration());

        Asignacion asignacion = new Asignacion(
                a,m,duration, Asignacion.Estado.EN_CURSO
        );

        repositoryAsignacion.save(asignacion);

        return asignacionDTO;

    }


    public List<AsignacionDTOEstado> getAllAsignaciones(){
        List<Asignacion> asignaciones = repositoryAsignacion.findAll();
        return MapperAsignacion.convertirAsignacionesAAsignacionesDTO(asignaciones);
    }

}
