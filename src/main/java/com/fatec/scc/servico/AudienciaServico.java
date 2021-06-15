package com.fatec.scc.servico;

import org.springframework.web.servlet.ModelAndView;

import com.fatec.scc.model.Audiencia;

public interface AudienciaServico {
	public Iterable<Audiencia> findAll() ;
	public void deleteById(Long id);
	public Audiencia findById(Long id);
	public ModelAndView saveOrUpdate (Audiencia audiencia);
	public String obtemEndereco(String cep);
}
