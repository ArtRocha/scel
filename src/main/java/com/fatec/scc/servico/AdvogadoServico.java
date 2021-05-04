package com.fatec.scc.servico;

import org.springframework.web.servlet.ModelAndView;

import com.fatec.scc.model.Advogado;

public interface AdvogadoServico {
	public Iterable<Advogado> findAll();
	public Advogado findByCpf(String cpf);
	public void deleteById(Long id);
	public Advogado findById(Long id);
	public ModelAndView saveOrUpdate (Advogado advogado);
	public String obtemEndereco(String cep);
}