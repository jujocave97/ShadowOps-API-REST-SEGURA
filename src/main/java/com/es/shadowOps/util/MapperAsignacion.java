package com.es.shadowOps.util;

import com.es.shadowOps.dto.AsignacionDTOActualizar;
import com.es.shadowOps.dto.AsignacionDTOEstado;
import com.es.shadowOps.model.Asignacion;

import java.util.ArrayList;
import java.util.List;

public class MapperAsignacion {
    public static List<AsignacionDTOEstado> convertirAsignacionesAAsignacionesDTO(List<Asignacion> asignacionList){
        List<AsignacionDTOEstado> asignacionDTOS = new ArrayList<>();
        for(Asignacion a : asignacionList){
            asignacionDTOS.add(new AsignacionDTOEstado(a.getId(),a.getAgente().getNombreClave(),a.getMision().getNombre(),a.getTiempoLimite().toMinutesPart(),a.getEstado().name()));
        }

        return asignacionDTOS;
    }


}
