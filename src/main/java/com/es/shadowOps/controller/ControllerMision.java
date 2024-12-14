package com.es.shadowOps.controller;

import com.es.shadowOps.dto.MisionDTO;
import com.es.shadowOps.model.Mision;
import com.es.shadowOps.service.ServiceMision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/misiones")
public class ControllerMision {
    @Autowired
    private ServiceMision serviceMision;

    @PostMapping("/")
    public ResponseEntity<MisionDTO> insertarMision(
            @RequestBody MisionDTO misionDTO
    ){
        serviceMision.insertarMision(misionDTO);
        return new ResponseEntity<MisionDTO>(misionDTO,HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<List<MisionDTO>> gatAllMisiones(){
        // Solo auntenticados pueden usar esta ruta
        List<MisionDTO> misionDTOList = serviceMision.getAllMisiones();
        return new ResponseEntity<List<MisionDTO>>(misionDTOList,HttpStatus.OK);
    }


    @PutMapping("/{nombre}")
    public ResponseEntity<MisionDTO> actualizarMision(
            @RequestParam String nombre, @RequestBody MisionDTO misionDTO
    ){
        serviceMision.actualizarMision(nombre,misionDTO);
        return new ResponseEntity<MisionDTO>(misionDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<MisionDTO> eliminarMision(
            @RequestParam String nombre
    ){
        MisionDTO misionDTO = serviceMision.eliminarMision(nombre);
        return new ResponseEntity<MisionDTO>(misionDTO,HttpStatus.OK);
    }



}
