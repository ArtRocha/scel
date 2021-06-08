package com.fatec.scc.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scc.model.Processo;
import com.fatec.scc.model.ProcessoRepository;

@Service
public class ProcessoServicoI implements ProcessoServico{
	Logger logger = LogManager.getLogger(ProcessoServicoI.class);
	@Autowired
	private ProcessoRepository repository;
	
	public Iterable<Processo> findAll() {
		return repository.findAll();
	}
	public void deleteById(Long id) {
		repository.deleteById(id);
		logger.info(">>>>>> 2. comando exclusao executado para o id => " + id);
	}
	public Processo findById(Long id) {
		return repository.findById(id).get();
	}
	public ModelAndView saveOrUpdate (Processo processo) { 
		ModelAndView modelAndView = new ModelAndView("consultarProcesso"); 
		try { 
			repository.save(processo); 
			logger.info(">>>>>> 4. comando save executado  "); 
			modelAndView.addObject("processos", repository.findAll()); 
		} catch (Exception e) { 
			// captura validacoes na camada de persistencia 
			modelAndView.setViewName("cadastrarProcesso"); 
			if (e.getMessage().contains("could not execute statement")) {
				modelAndView.addObject("message", "Dados invalidos - processo já cadastrado."); 
				logger.info(">>>>>> 5. processo ja cadastrado ==> " + e.getMessage()); 
			} else { 
				modelAndView.addObject("message", "Erro não esperado - contate o administrador"); 
				logger.error(">>>>>> 5. erro nao esperado ==> " + e.getMessage()); 
			} 
		} 
		return modelAndView; 
	}	
}
