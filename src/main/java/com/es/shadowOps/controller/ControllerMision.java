package com.es.shadowOps.controller;

import com.es.shadowOps.service.ServiceMision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/misiones")
public class ControllerMision {
    @Autowired
    private ServiceMision serviceMision;
}
