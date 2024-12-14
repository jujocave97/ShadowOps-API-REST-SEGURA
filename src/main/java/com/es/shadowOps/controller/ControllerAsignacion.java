package com.es.shadowOps.controller;

import com.es.shadowOps.dto.AsignacionDTOActualizar;
import com.es.shadowOps.dto.AsignacionDTOInsert;
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
    public ResponseEntity<AsignacionDTOInsert> insertarAsignacion(
            @RequestBody AsignacionDTOInsert asignacionDTOInsert
    ){
        serviceAsignacion.insertarAsignacion(asignacionDTOInsert);
        return new ResponseEntity<AsignacionDTOInsert>(asignacionDTOInsert, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<AsignacionDTOEstado>> getAllAsignaciones(){
        List<AsignacionDTOEstado> asignacionDTOEstados = serviceAsignacion.getAllAsignaciones();
        return new ResponseEntity<>(asignacionDTOEstados, HttpStatus.OK);
    }

    @GetMapping("/{agente}")
    public ResponseEntity<List<AsignacionDTOEstado>> getAsignacionesAgente(
            @PathVariable String agente
    ){
        List<AsignacionDTOEstado> asignacionDTOEstados = serviceAsignacion.getAllAsignacionesAgente(agente);
        return new ResponseEntity<List<AsignacionDTOEstado>>(asignacionDTOEstados,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignacionDTOEstado> editarAsignacion(
            @PathVariable String id, @RequestBody AsignacionDTOActualizar asignacionDTOActualizar
    ){
        AsignacionDTOEstado asignacionDTOEstado = serviceAsignacion.actualizarAsignacion(id,asignacionDTOActualizar);
        return new ResponseEntity<AsignacionDTOEstado>(asignacionDTOEstado,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AsignacionDTOEstado> eliminarAsignacion(
            @PathVariable String id
    ){
        AsignacionDTOEstado asignacionDTOEstado = serviceAsignacion.eliminarAsignacion(id);
        return new ResponseEntity<AsignacionDTOEstado>(asignacionDTOEstado,HttpStatus.OK);
    }
}
