package com.es.shadowOps.repository;

import com.es.shadowOps.model.Agente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAgente extends JpaRepository<Agente, String> {

}
