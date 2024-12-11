package com.es.shadowOps.controller;

import com.es.shadowOps.dto.AgenteDTOLogin;
import com.es.shadowOps.dto.AgenteDTORegistro;
import com.es.shadowOps.service.ServiceAgente;
import com.es.shadowOps.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agentes")
public class ControllerAgente {
    @Autowired
    private ServiceAgente serviceAgente;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public String login(
            @RequestBody AgenteDTOLogin agenteDTOLogin
    ) {

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(agenteDTOLogin.getNombreClave(), agenteDTOLogin.getPassword())// modo de autenticación
            );
        } catch (Exception e) {
            System.out.println("Excepcion en authentication");
            // lanzar Error
        }

        // Generamos el token
        String token = "";
        try {
            token = tokenService.generateToken(authentication);
        } catch (Exception e) {
            System.out.println("Excepcion en generar token");
            // lanzar error
        }

        return token;

    }

    @PostMapping("/registro")
    public ResponseEntity<AgenteDTORegistro> registro(
        @RequestBody AgenteDTORegistro agenteDTORegistro
    ){
        return null;
    }
}
