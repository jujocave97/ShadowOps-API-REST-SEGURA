package com.es.shadowOps.repository;

import com.es.shadowOps.model.Agente;
import com.es.shadowOps.model.Asignacion;
import com.es.shadowOps.model.Mision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryAsignacion extends JpaRepository<Asignacion, Long> {
    //List<Optional<Asignacion>> findAllByNombre(Agente agente);
}
