package com.es.shadowOps.service;

import com.es.shadowOps.dto.AsignacionDTOActualizar;
import com.es.shadowOps.dto.AsignacionDTOInsert;
import com.es.shadowOps.dto.AsignacionDTOEstado;
import com.es.shadowOps.error.errores.BadRequestException;
import com.es.shadowOps.error.errores.NotAcceptableException;
import com.es.shadowOps.error.errores.NotFoundException;
import com.es.shadowOps.model.Agente;
import com.es.shadowOps.model.Asignacion;
import com.es.shadowOps.model.Mision;
import com.es.shadowOps.repository.RepositoryAgente;
import com.es.shadowOps.repository.RepositoryAsignacion;
import com.es.shadowOps.repository.RepositoryMision;
import com.es.shadowOps.util.ActualizadorDeTiempo;
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
            throw new BadRequestException("Los campos no pueden estar vacíos");
        }

        Agente a = repositoryAgente.findById(asignacionDTOInsert.getAgente()).orElseThrow(() -> new NotFoundException("No se ha encontrado el agente al que quieres asignar la misión"));

        if(comprobarSiTieneMisionAsignadaEnCurso(a)){
            throw new NotAcceptableException("El agente ya tiene una misión en curso asignada");
        }

        Mision m = repositoryMision.findByNombre(asignacionDTOInsert.getMision()).orElseThrow(() -> new NotFoundException("La misión no se encuentra"));

        if(comprobarSiTieneAgenteAsignado(m)){
            throw new NotAcceptableException("La misión ya tiene a un agente asignado");
        }

        Duration duration = Duration.ofMinutes(asignacionDTOInsert.getDuration());

        Asignacion asignacion = new Asignacion(
                a,m,duration, Asignacion.Estado.EN_CURSO
        );

        repositoryAsignacion.save(asignacion);
        // lanzo un hilo para controlar el tiempo de la asignación.
        ActualizadorDeTiempo.cambiarEstadoMision(asignacion.getId(), repositoryAsignacion,asignacion.getTiempoLimite());

        return asignacionDTOInsert;

    }


    public List<AsignacionDTOEstado> getAllAsignaciones(){
        List<Asignacion> asignaciones = repositoryAsignacion.findAll();
        return MapperAsignacion.convertirAsignacionesAAsignacionesDTO(asignaciones);
    }

    public List<AsignacionDTOEstado> getAllAsignacionesAgente(String agente){
        Agente a = repositoryAgente.findById(agente).orElseThrow(() -> new NotFoundException("No se ha encontrado el agente"));

        List<Asignacion> asignacionList = repositoryAsignacion.findAll().stream().filter(asignacion -> asignacion.getAgente() == a).toList();
        return MapperAsignacion.convertirAsignacionesAAsignacionesDTO(asignacionList);
    }

    public AsignacionDTOEstado actualizarAsignacion (String id, AsignacionDTOActualizar asignacionDTOActualizar){
        Long idL = 0L;
        try {
            idL = Long.parseLong(id);
        }catch (NumberFormatException e){
            throw new NumberFormatException("Introduce un ID válido");
        }

        Asignacion asignacion = repositoryAsignacion.findById(idL).orElseThrow(() -> new NotFoundException("No se ha encontrado la asignación"));

        Agente agente = repositoryAgente.findById(asignacionDTOActualizar.getAgente()).orElseThrow(() -> new NotFoundException("El agente no existe"));

        Mision mision = repositoryMision.findByNombre(asignacionDTOActualizar.getMision()).orElseThrow(() -> new NotFoundException("La misión no existe"));

        asignacion.setAgente(agente);
        asignacion.setMision(mision);
        asignacion.setEstado(asignacionDTOActualizar.getEstado());
        Duration duration = Duration.ofMinutes(asignacionDTOActualizar.getDuration());
        asignacion.setTiempoLimite(duration);

        if(asignacion.getEstado().equals(Asignacion.Estado.COMPLETADO)){
            agente.setBounty(agente.getBounty()+mision.getRecompensa());
        }

        if(asignacion.getAgente().getBounty() >= 100000000){
            agente.setRoles("WARLORD");
        }


        repositoryAsignacion.save(asignacion);

        return new AsignacionDTOEstado(asignacion.getId(),asignacionDTOActualizar.getAgente(), asignacionDTOActualizar.getMision(),asignacionDTOActualizar.getDuration(), asignacionDTOActualizar.getEstado());
    }


    public AsignacionDTOEstado eliminarAsignacion(String id){
        Long idL = 0L;
        try {
            idL = Long.parseLong(id);
        }catch (NumberFormatException e){
            throw new NumberFormatException("Introduce un ID válido");
        }

        Asignacion asignacion = repositoryAsignacion.findById(idL).orElseThrow(() -> new NotFoundException("No se ha encontrado la asignación que quieres eliminar"));

        repositoryAsignacion.delete(asignacion);

        return new AsignacionDTOEstado(asignacion.getId(),asignacion.getAgente().getNombreClave(),asignacion.getMision().getNombre(),
                asignacion.getTiempoLimite().toMinutesPart(),asignacion.getEstado().name());
    }

    public boolean comprobarSiTieneMisionAsignadaEnCurso(Agente a){
        List<Asignacion> asignacionList = repositoryAsignacion.findAll().stream().filter(asignacion -> asignacion.getAgente().equals(a)).toList();

        for (Asignacion asignacion: asignacionList){
            if(asignacion.getEstado().equals(Asignacion.Estado.EN_CURSO))
                return true;
        }

        return false;

    }

    public boolean comprobarSiTieneAgenteAsignado( Mision m){
        if(repositoryAsignacion.findAll().stream().filter(asignacion -> asignacion.getMision().getId().equals(m.getId())).findFirst().isPresent()){
            return true;
        }
        return false;
    }

}
