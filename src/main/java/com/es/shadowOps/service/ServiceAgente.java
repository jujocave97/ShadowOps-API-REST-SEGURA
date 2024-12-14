package com.es.shadowOps.service;

import com.es.shadowOps.dto.AgenteDTO;
import com.es.shadowOps.dto.AgenteDTOCompleto;
import com.es.shadowOps.model.Agente;
import com.es.shadowOps.repository.RepositoryAgente;
import com.es.shadowOps.util.MapperAgente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceAgente implements UserDetailsService {
    @Autowired
    private RepositoryAgente repositoryAgente;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // BUSCO EL USUARIO POR SU NOMBRE EN LA BDD
        Agente agente = repositoryAgente
                .findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario No encontrado"));

        /* RETORNAMOS UN USERDETAILS
        El método loadUserByUsername nos fuerza a devolver un objeto de tipo UserDetails.
        Tenemos que convertir nuestro objeto de tipo Usuario a un objeto de tipo UserDetails
        ¡No os preocupéis, esto es siempre igual!
         */
        List<GrantedAuthority> authorities = Arrays.stream(agente.getRoles().split(","))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.trim()))
                .collect(Collectors.toList());

        UserDetails userDetails = User // User pertenece a SpringSecurity
                .builder()
                .username(agente.getNombreClave())
                .password(agente.getPassword())
                .roles(agente.getRoles())
                .build();

        return userDetails;
    }

    public AgenteDTOCompleto registro(AgenteDTOCompleto agenteDTOCompleto){
        if(repositoryAgente.findById(agenteDTOCompleto.getNombreClave()).isPresent()){
            //throw exception duplicated
        }

        if(agenteDTOCompleto.getPassword().length() > 15 || agenteDTOCompleto.getPassword().length() < 3){
            // throw exception invalid password
        }


        Agente agente = MapperAgente.crearAgenteAPartirDelDTOCompleto(agenteDTOCompleto);
        agente.setPassword(passwordEncoder.encode(agente.getPassword()));
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
                a.getNombreClave(),a.getNombre(),a.getRoles(), a.getBounty()
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

        agenteDTOCompleto.setPassword("****************");
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
