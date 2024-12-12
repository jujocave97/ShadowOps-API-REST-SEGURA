package com.es.shadowOps.service;

import com.es.shadowOps.dto.AgenteDTO;
import com.es.shadowOps.dto.AgenteDTOCompleto;
import com.es.shadowOps.model.Agente;
import com.es.shadowOps.repository.RepositoryAgente;
import com.es.shadowOps.util.MapperAgente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Agente agente = MapperAgente.crearAgenteAPartirDelDTOCompleto(agenteDTOCompleto);
        repositoryAgente.save(agente);

        return agenteDTOCompleto;
    }

    public List<AgenteDTOCompleto> getAll(){
        List<Agente> agentes = repositoryAgente.findAll();
        return MapperAgente.convertirAgentesEnAgentesDTO(agentes);
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
        // TODO: TERMINAR ESTO , pensar en implementar un método para comprobar los campos

        repositoryAgente.save(a);

        return agenteDTOCompleto;
    }

    public AgenteDTO eliminarAgente(String nombreClave){
        if(repositoryAgente.findById(nombreClave).isEmpty()){
            // throw exception doesnt exist
        }
        Agente a = repositoryAgente.findById(nombreClave).orElse(null);
        repositoryAgente.delete(a);

        return MapperAgente.crearAgenteDTO(a);
    }

}
