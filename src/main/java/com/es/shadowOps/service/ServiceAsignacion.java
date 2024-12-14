package com.es.shadowOps.service;

import com.es.shadowOps.dto.AsignacionDTOActualizar;
import com.es.shadowOps.dto.AsignacionDTOInsert;
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
import java.util.List;

@Service
public class ServiceAsignacion {
    @Autowired
    private RepositoryAsignacion repositoryAsignacion;
    @Autowired
    private RepositoryAgente repositoryAgente;
    @Autowired
    private RepositoryMision repositoryMision;

    public AsignacionDTOInsert insertarAsignacion(AsignacionDTOInsert asignacionDTOInsert){
        if (asignacionDTOInsert.getAgente().isEmpty() || asignacionDTOInsert.getMision().isEmpty() || asignacionDTOInsert.getDuration() <= 0){
            // throw exception
        }

        Agente a = repositoryAgente.findById(asignacionDTOInsert.getAgente()).orElse(null);
        if( a == null){
            // throw exception
        }

        Mision m = repositoryMision.findByNombre(asignacionDTOInsert.getMision()).orElse(null);
        if( m == null){
            // throw exception
        }

        Duration duration = Duration.ofMinutes(asignacionDTOInsert.getDuration());

        Asignacion asignacion = new Asignacion(
                a,m,duration, Asignacion.Estado.EN_CURSO
        );

        repositoryAsignacion.save(asignacion);

        return asignacionDTOInsert;

    }


    public List<AsignacionDTOEstado> getAllAsignaciones(){
        List<Asignacion> asignaciones = repositoryAsignacion.findAll();
        return MapperAsignacion.convertirAsignacionesAAsignacionesDTO(asignaciones);
    }

    public List<AsignacionDTOEstado> getAllAsignacionesAgente(String agente){
        Agente a = repositoryAgente.findById(agente).orElse(null);
        if(a == null){
            // Throw exception
        }

        List<Asignacion> asignacionList = repositoryAsignacion.findAll().stream().filter(asignacion -> asignacion.getAgente() == a).toList();
        return MapperAsignacion.convertirAsignacionesAAsignacionesDTO(asignacionList);
    }

    public AsignacionDTOEstado actualizarAsignacion (String id, AsignacionDTOActualizar asignacionDTOActualizar){
        Long idL = 0L;
        try {
            idL = Long.parseLong(id);
        }catch (NumberFormatException e){
            // throw exception
        }

        Asignacion asignacion = repositoryAsignacion.findById(idL).orElse(null);
        if(asignacion == null){
            // throw exception
        }

        Agente agente = repositoryAgente.findById(asignacionDTOActualizar.getAgente()).orElse(null);
        if(agente == null){
            // throw excecption
        }

        Mision mision = repositoryMision.findByNombre(asignacionDTOActualizar.getMision()).orElse(null);
        if(mision == null){
            // throw exception
        }

        asignacion.setAgente(agente);
        asignacion.setMision(mision);
        asignacion.setEstado(asignacionDTOActualizar.getEstado());
        Duration duration = Duration.ofMinutes(asignacionDTOActualizar.getDuration());
        asignacion.setTiempoLimite(duration);

        repositoryAsignacion.save(asignacion);

        return new AsignacionDTOEstado(asignacion.getId(),asignacionDTOActualizar.getAgente(), asignacionDTOActualizar.getMision(),asignacionDTOActualizar.getDuration(), asignacionDTOActualizar.getEstado());
    }


    public AsignacionDTOEstado eliminarAsignacion(String id){
        Long idL = 0L;
        try {
            idL = Long.parseLong(id);
        }catch (NumberFormatException e){
            // throw exception
        }

        Asignacion asignacion = repositoryAsignacion.findById(idL).orElse(null);

        if(asignacion == null){
            // throw exception
        }

        repositoryAsignacion.delete(asignacion);

        return new AsignacionDTOEstado(asignacion.getId(),asignacion.getAgente().getNombreClave(),asignacion.getMision().getNombre(),
                asignacion.getTiempoLimite().toMinutesPart(),asignacion.getEstado().name());
    }

}
