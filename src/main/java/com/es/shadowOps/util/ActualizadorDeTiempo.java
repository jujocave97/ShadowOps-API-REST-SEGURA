package com.es.shadowOps.util;

import com.es.shadowOps.model.Asignacion;
import com.es.shadowOps.repository.RepositoryAsignacion;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ActualizadorDeTiempo {
    @Async
    public static void cambiarEstadoMision(Asignacion asignacion, RepositoryAsignacion repositoryAsignacion){
        Thread hilo = new Thread(() ->{
            try{
                Thread.sleep(asignacion.getTiempoLimite().toMillis());
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
