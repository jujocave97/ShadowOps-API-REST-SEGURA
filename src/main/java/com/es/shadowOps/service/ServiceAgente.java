package com.es.shadowOps.service;

import com.es.shadowOps.dto.AgenteDTOCompleto;
import com.es.shadowOps.model.Agente;
import com.es.shadowOps.repository.RepositoryAgente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAgente {
    @Autowired
    private RepositoryAgente repositoryAgente;

    public AgenteDTOCompleto registro(AgenteDTOCompleto agenteDTOCompleto){
        if(repositoryAgente.findById(agenteDTOCompleto.getNombreClave()).isPresent()){
            //throw exception duplicated
        }

        if(agenteDTOCompleto.getPassword().length() > 15 || agenteDTOCompleto.getPassword().length() < 3){
            // throw exception invalid password
        }

        Agente agente = crearAgenteAPartirDelRegistro(agenteDTOCompleto);
        repositoryAgente.save(agente);

        return agenteDTOCompleto;
    }

    public List<AgenteDTOCompleto> getAll(){
        List<Agente> agentes = repositoryAgente.findAll();
        return convertirAgentesEnAgentesDTO(agentes);
    }

    public AgenteDTOCompleto getAgentePorNombre(String nombreClave){
        if(nombreClave.isEmpty() || nombreClave.isBlank()){
            // throw exception
        }
        Agente a = repositoryAgente.findById(nombreClave).orElse(null);
        return new AgenteDTOCompleto(
                a.getNombreClave(),a.getNombre(),a.getPassword(),a.getRoles(), a.getBounty()
            );
    }

    public AgenteDTOCompleto actualizarAgente(String nombreClave, AgenteDTOCompleto agenteDTOCompleto){
        if(repositoryAgente.findById(nombreClave).isEmpty()){
            // throw exception agente no existe
        }

        Agente a = repositoryAgente.findById(nombreClave).orElse(null);
        a.setNombre(agenteDTOCompleto.getNombre());
        a.setNombreClave(agenteDTOCompleto.getNombreClave());
        a.setPassword(agenteDTOCompleto.getPassword());
        a.setBounty(a.getBounty());
        // TODO: TERMINAR ESTO , pensar en implementar un metodo para comprobar los campos

        return null;
    }




    // pasarlo a utils o dejarlo aqui?
    private List<AgenteDTOCompleto> convertirAgentesEnAgentesDTO(List<Agente> agentes){
        List<AgenteDTOCompleto> agenteDTOCompletos = new ArrayList<>();
        for( Agente a : agentes){
            AgenteDTOCompleto agenteDTOCompleto = new AgenteDTOCompleto(
                    a.getNombreClave(),a.getNombre(),a.getPassword(),a.getRoles(), a.getBounty()
            );
            agenteDTOCompletos.add(agenteDTOCompleto);
        }

        return agenteDTOCompletos;
    }

    private  Agente crearAgenteAPartirDelRegistro(AgenteDTOCompleto agenteDTOCompleto) {
        Agente agente = new Agente();
        agente.setNombreClave(agenteDTOCompleto.getNombreClave());
        agente.setNombre(agenteDTOCompleto.getNombre());
        agente.setPassword(agenteDTOCompleto.getPassword());
        agente.setRoles(agenteDTOCompleto.getRoles());
        agente.setBounty(0.0);
        return agente;
    }
}
