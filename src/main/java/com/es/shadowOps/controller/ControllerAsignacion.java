package com.es.shadowOps.controller;

import com.es.shadowOps.dto.AsignacionDTO;
import com.es.shadowOps.dto.AsignacionDTOEstado;
import com.es.shadowOps.service.ServiceAsignacion;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignaciones")
public class ControllerAsignacion {
    @Autowired
    private ServiceAsignacion serviceAsignacion;

    @PostMapping("/")
    public ResponseEntity<AsignacionDTO> insertarAsignacion(
            @RequestBody AsignacionDTO asignacionDTO
    ){
        serviceAsignacion.insertarAsignacion(asignacionDTO);
        return new ResponseEntity<AsignacionDTO>(asignacionDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AsignacionDTOEstado>> getAllAsignaciones(){
        List<AsignacionDTOEstado> asignacionDTOEstados = serviceAsignacion.getAllAsignaciones();
        return new ResponseEntity<>(asignacionDTOEstados, HttpStatus.OK);
    }
}
