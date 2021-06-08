package com.fatec.scc.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessoRepository extends CrudRepository<Processo, Long> {
	public Processo findByCpf(@Param("isbn") String cpf);
}

