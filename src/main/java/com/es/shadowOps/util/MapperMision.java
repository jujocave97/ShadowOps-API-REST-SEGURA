package com.es.shadowOps.util;

import com.es.shadowOps.dto.MisionDTO;
import com.es.shadowOps.model.Mision;

import java.util.ArrayList;
import java.util.List;

public class MapperMision {
    public static List<MisionDTO> convertirMisionesAMisionesDTO(List<Mision> misions){
        List<MisionDTO> misionDTOS = new ArrayList<>();
        for(Mision m: misions){
            misionDTOS.add(new MisionDTO(
                    m.getNombre(),m.getLugar(),m.getTipo().toString(),m.getDescripcion(),m.getRecompensa()
            ));
        }

        return misionDTOS;
    }

    public static Mision actualizarMision(MisionDTO misionDTO, Mision mision){
        mision.setNombre(misionDTO.getNombre());
        mision.setLugar(mision.getLugar());
        mision.setTipo(misionDTO.getTipo());
        mision.setDescripcion(misionDTO.getDescripcion());
        mision.setRecompensa(mision.getRecompensa());

        return mision;
    }

    public static MisionDTO convertirMisionEnMisionDTO(Mision mision){
        return new MisionDTO(
                mision.getNombre(), mision.getLugar(), mision.getTipo().toString(), mision.getDescripcion(), mision.getRecompensa()
        );
    }
}
