package com.es.shadowOps.service;

import com.es.shadowOps.dto.MisionDTO;
import com.es.shadowOps.model.Asignacion;
import com.es.shadowOps.model.Mision;
import com.es.shadowOps.repository.RepositoryMision;
import com.es.shadowOps.util.MapperMision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ServiceMision {
    @Autowired
    private RepositoryMision repositoryMision;

    public MisionDTO insertarMision( MisionDTO misionDTO){
        if(misionDTO.getNombre().isEmpty() || misionDTO.getLugar().isEmpty() || misionDTO.getRecompensa() <= 0 ||
            misionDTO.getTipo().isEmpty()){
            // throw exception
        }

        Mision mision = new Mision(
                misionDTO.getNombre(), misionDTO.getLugar(), misionDTO.getTipo(), misionDTO.getDescripcion(),
                misionDTO.getRecompensa()
        );

        // TODO: consultar restricciones como que no pueden venir campos vacios etc...
        repositoryMision.save(mision);
        return misionDTO;
    }

    public List<MisionDTO> getAllMisiones(){
        List<Mision> misions = repositoryMision.findAll();
        return MapperMision.convertirMisionesAMisionesDTO(misions);
    }

    public void actualizarMision (String id, MisionDTO misionDTO){
        Long idL = 0L;

        try{
            idL = Long.parseLong(id);
        }catch (NumberFormatException e){
            // throw exception
        }


        if(repositoryMision.findById(idL).isEmpty() || misionDTO.getNombre().isEmpty() ||
            misionDTO.getTipo().isEmpty() || misionDTO.getLugar().isEmpty() || misionDTO.getRecompensa() <= 0){
            // throw exeption
        }

        Mision mision = repositoryMision.findById(idL).orElse(null);
        if(mision == null){
            // trow exeption
        }
        repositoryMision.save(MapperMision.actualizarMision(misionDTO,mision));

    }

    public MisionDTO eliminarMision (String nombre){
        if(repositoryMision.findByNombre(nombre).isEmpty()){
            // throw exception la mision no existe
        }

        Mision mision = repositoryMision.findByNombre(nombre).orElse(null);
        MisionDTO misionDTO = MapperMision.convertirMisionEnMisionDTO(mision);
        repositoryMision.delete(mision);
        return misionDTO;
    }


}
