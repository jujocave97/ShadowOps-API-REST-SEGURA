package com.es.shadowOps.controller;

import com.es.shadowOps.service.ServiceAsignacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asignaciones")
public class ControllerAsignacion {
    @Autowired
    private ServiceAsignacion serviceAsignacion;

}
