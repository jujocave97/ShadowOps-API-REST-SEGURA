package com.es.shadowOps.controller;

import com.es.shadowOps.service.ServiceAsignacion;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asignaciones")
public class ControllerAsignacion {
    @Autowired
    private ServiceAsignacion serviceAsignacion;


}
