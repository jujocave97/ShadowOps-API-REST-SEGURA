package com.es.shadowOps.service;

import com.es.shadowOps.dto.AgenteDTORegistro;
import com.es.shadowOps.repository.RepositoryAgente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAgente {
    @Autowired
    private RepositoryAgente repositoryAgente;

    public AgenteDTORegistro registro( AgenteDTORegistro agenteDTORegistro){
        if(repositoryAgente.findById(agenteDTORegistro.getNombreClave()).isPresent()){
            //throw exception duplicado
        }


    }
}
