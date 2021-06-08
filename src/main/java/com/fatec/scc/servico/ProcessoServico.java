package com.fatec.scc.servico;

import org.springframework.web.servlet.ModelAndView;

import com.fatec.scc.model.Processo;

public interface ProcessoServico {
	public Iterable<Processo> findAll() ;
	public Processo findByCpf(String cpf);
	public void deleteById(Long id);
	public Processo findById(Long id);
	public ModelAndView saveOrUpdate (Processo processo);
	public String obtemEndereco(String cep);
	public String sendMail(Processo processo); 
}
