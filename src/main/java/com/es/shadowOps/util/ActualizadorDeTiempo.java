package com.es.shadowOps.util;

import com.es.shadowOps.model.Asignacion;
import com.es.shadowOps.repository.RepositoryAsignacion;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class ActualizadorDeTiempo {
    @Async
    public static void cambiarEstadoMision(long id, RepositoryAsignacion repositoryAsignacion, Duration duration){
        Thread hilo = new Thread(() ->{
            try{
                Thread.sleep(duration.toMillis());
                Asignacion asignacion = repositoryAsignacion.findById(id).orElse(null);
                if (asignacion == null){
                    throw new RuntimeException();
                }
                if(!asignacion.getEstado().equals(Asignacion.Estado.COMPLETADO)){
                    asignacion.setEstado("FALLIDA");
                    repositoryAsignacion.save(asignacion);
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                // Throw exception
            }
        });
        hilo.start();
    }
}
