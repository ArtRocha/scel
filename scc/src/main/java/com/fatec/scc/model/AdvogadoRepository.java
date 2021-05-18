package com.fatec.scc.model;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvogadoRepository extends CrudRepository<Advogado, Long> {
	public Advogado findByCpf(@Param("isbn") String cpf);
}
