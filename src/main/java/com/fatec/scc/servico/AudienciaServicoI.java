package com.fatec.scc.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scc.model.Audiencia;
import com.fatec.scc.model.AudienciaRepository;
import com.fatec.scc.model.Endereco;

@Service
public class AudienciaServicoI implements AudienciaServico{
	Logger logger = LogManager.getLogger(AudienciaServicoI.class);
	@Autowired
	private AudienciaRepository repository;
	
	public Iterable<Audiencia> findAll() {
		return repository.findAll();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
		logger.info(">>>>>> 2. comando exclusao executado para o id => " + id);
	}
	public Audiencia findById(Long id) {
		return repository.findById(id).get();
	}
	public ModelAndView saveOrUpdate (Audiencia audiencia) { 
		ModelAndView modelAndView = new ModelAndView("consultarAudiencia"); 
		try { 
			String endereco = obtemEndereco(audiencia.getCep()); 
			if (endereco != "") { audiencia.setEndereco(endereco); 
				repository.save(audiencia); 
				logger.info(">>>>>> 4. comando save executado  "); 
				modelAndView.addObject("audiencias", repository.findAll()); 
			} 
		} catch (Exception e) { 
			// captura validacoes na camada de persistencia 
			modelAndView.setViewName("cadastrarAudiencia"); 
			if (e.getMessage().contains("could not execute statement")) {
				modelAndView.addObject("message", "Dados invalidos - audiencia já cadastrado."); 
				logger.info(">>>>>> 5. audiencia ja cadastrado ==> " + e.getMessage()); 
			} else { 
				modelAndView.addObject("message", "Erro não esperado - contate o administrador"); 
				logger.error(">>>>>> 5. erro nao esperado ==> " + e.getMessage()); 
			} 
		} 
		return modelAndView; 
	}
	public String obtemEndereco(String cep) {
		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/{cep}/json/";
		Endereco endereco = template.getForObject(url, Endereco.class, cep);
		logger.info(">>>>>> 3. obtem endereco ==> " + endereco.toString());
		return endereco.getLogradouro();
	}
	
	
}
