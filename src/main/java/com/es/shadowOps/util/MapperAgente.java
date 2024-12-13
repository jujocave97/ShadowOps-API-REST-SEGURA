package com.es.shadowOps.util;

import com.es.shadowOps.dto.AgenteDTO;
import com.es.shadowOps.dto.AgenteDTOCompleto;
import com.es.shadowOps.model.Agente;

import java.util.ArrayList;
import java.util.List;

public class MapperAgente {
    public static List<AgenteDTOCompleto> convertirAgentesEnAgentesDTO(List<Agente> agentes){
        List<AgenteDTOCompleto> agenteDTOCompletos = new ArrayList<>();
        for( Agente a : agentes){
            AgenteDTOCompleto agenteDTOCompleto = new AgenteDTOCompleto(
                    a.getNombreClave(),a.getNombre(),a.getRoles(), a.getBounty()
            );
            agenteDTOCompletos.add(agenteDTOCompleto);
        }

        return agenteDTOCompletos;
    }

    public static  Agente crearAgenteAPartirDelDTOCompleto(AgenteDTOCompleto agenteDTOCompleto) {
        Agente agente = new Agente();
        agente.setNombreClave(agenteDTOCompleto.getNombreClave());
        agente.setNombre(agenteDTOCompleto.getNombre());
        agente.setPassword(agenteDTOCompleto.getPassword());
        agente.setRoles(agenteDTOCompleto.getRoles());
        agente.setBounty(0.0);
        return agente;
    }

    public static AgenteDTO crearAgenteDTO(Agente a){
        AgenteDTO agenteDTO = new AgenteDTO(
            a.getNombreClave(),a.getRoles(),a.getBounty()
        );
        return agenteDTO;
    }
}
