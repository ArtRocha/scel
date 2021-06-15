package com.fatec.scc.model;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AudienciaRepository extends CrudRepository<Audiencia, Long> {
	public Optional<Audiencia> findById(@Param("isbn") Long Id);
}
