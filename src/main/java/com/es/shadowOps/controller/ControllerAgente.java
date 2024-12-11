package com.es.shadowOps.controller;

import com.es.shadowOps.dto.AgenteDTOLogin;
import com.es.shadowOps.dto.AgenteDTOCompleto;
import com.es.shadowOps.service.ServiceAgente;
import com.es.shadowOps.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        // publico
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
    public ResponseEntity<AgenteDTOCompleto> registro(
        @RequestBody AgenteDTOCompleto agenteDTOCompleto
    ){
        // publico
        serviceAgente.registro(agenteDTOCompleto);

        return new ResponseEntity<AgenteDTOCompleto>(agenteDTOCompleto, HttpStatus.OK);
    }

    // nota: crear un getAll ordenado por bounty ?

    @GetMapping("/")
    public ResponseEntity<List<AgenteDTOCompleto>> getAll(){
        // solo puede acceder un Warlord
        List<AgenteDTOCompleto> agenteDTOCompletos = serviceAgente.getAll();

        return new ResponseEntity<List<AgenteDTOCompleto>>(agenteDTOCompletos,HttpStatus.OK);
    }

    @GetMapping("/{nombreClave}")
    public ResponseEntity<AgenteDTOCompleto> getAgenteNombre(
            @PathVariable String nombreClave
    ){
        // ruta privada solo el warlord o el agente con su mismo nombre puede acceder
        AgenteDTOCompleto agenteDTOCompleto = serviceAgente.getAgentePorNombre(nombreClave);
        return new ResponseEntity<AgenteDTOCompleto>(agenteDTOCompleto,HttpStatus.OK);
    }

    @PutMapping("{nombreClave}")
    public  ResponseEntity<AgenteDTOCompleto> actualizarAgente(
            @PathVariable String nombreClave, @RequestBody AgenteDTOCompleto agenteDTOCompleto
    ){
        // ruta privada solo el warlord o el agente con su mismo nombre puede acceder
        return null;
    }
}
