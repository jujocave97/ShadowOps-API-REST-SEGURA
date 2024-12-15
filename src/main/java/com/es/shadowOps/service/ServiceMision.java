package com.es.shadowOps.service;

import com.es.shadowOps.dto.MisionDTO;
import com.es.shadowOps.error.errores.BadRequestException;
import com.es.shadowOps.error.errores.NotFoundException;
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
                throw new BadRequestException("Los campos no pueden estar vacíos");
        }

        Mision mision = new Mision(
                misionDTO.getNombre(), misionDTO.getLugar(), misionDTO.getTipo(), misionDTO.getDescripcion(),
                misionDTO.getRecompensa()
        );

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
            throw new NumberFormatException("Introduce un formato de ID válido");
        }


        if(repositoryMision.findById(idL).isEmpty() || misionDTO.getNombre().isEmpty() ||
            misionDTO.getTipo().isEmpty() || misionDTO.getLugar().isEmpty() || misionDTO.getRecompensa() <= 0){
            throw new BadRequestException("Los campos no pueden estar vacíos");
        }

        Mision mision = repositoryMision.findById(idL).orElseThrow(() -> new NotFoundException("No se ha encontrado la misión"));

        repositoryMision.save(MapperMision.actualizarMision(misionDTO,mision));

    }

    public MisionDTO eliminarMision (String nombre){
        if(repositoryMision.findByNombre(nombre).isEmpty()){
            throw new NotFoundException("No se ha encontrado la misión que quieres eliminar");
        }

        Mision mision = repositoryMision.findByNombre(nombre).orElseThrow(() -> new NotFoundException("No se ha encontrado la misión"));
        MisionDTO misionDTO = MapperMision.convertirMisionEnMisionDTO(mision);
        repositoryMision.delete(mision);
        return misionDTO;
    }


}
