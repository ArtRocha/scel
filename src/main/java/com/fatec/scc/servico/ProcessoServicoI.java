package com.fatec.scc.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scc.model.Processo;
import com.fatec.scc.model.ProcessoRepository;
import com.fatec.scc.model.Endereco;

@Service
public class ProcessoServicoI implements ProcessoServico{
	Logger logger = LogManager.getLogger(ProcessoServicoI.class);
	@Autowired
	private ProcessoRepository repository;
	@Autowired
	private JavaMailSender mailSender;
	
	public Iterable<Processo> findAll() {
		return repository.findAll();
	}
	public Processo findByCpf(String cpf) {
		return repository.findByCpf(cpf);
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
			String endereco = obtemEndereco(processo.getCep()); 
			if (endereco != "") { processo.setEndereco(endereco); 
				repository.save(processo); 
				logger.info(">>>>>> 4. comando save executado  "); 
				sendMail(processo); 
				modelAndView.addObject("processos", repository.findAll()); 
			} 
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
	public String obtemEndereco(String cep) {
		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/{cep}/json/";
		Endereco endereco = template.getForObject(url, Endereco.class, cep);
		logger.info(">>>>>> 3. obtem endereco ==> " + endereco.toString());
		return endereco.getLogradouro();
	}
	
	public String sendMail(Processo processo) {         
		SimpleMailMessage message = new SimpleMailMessage();         
		message.setFrom("giulia-ratti@hotmail.com");         
		message.setTo(processo.getEmail());         
		message.setSubject("Confirmação do cadastro de processo");         
		message.setText(processo.toString());          
		try {           
			mailSender.send(message);             
			logger.info(">>>>>> 5. Envio do e-mail processado com sucesso.");             
			return "Email enviado";         
		} catch (Exception e) {             
			e.printStackTrace();             
			return "Erro ao enviar e-mail.";         
		} 
	}
	
}
