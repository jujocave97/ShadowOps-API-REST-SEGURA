package com.es.shadowOps.repository;

import com.es.shadowOps.model.Mision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryMision extends JpaRepository<Mision, Long> {
    Optional<Mision> findByNombre(String nombre);
}
