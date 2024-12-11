package com.es.shadowOps.repository;

import com.es.shadowOps.model.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryAsignacion extends JpaRepository<Asignacion, Long> {
}
