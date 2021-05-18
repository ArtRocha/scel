package com.fatec.scc.servico;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scc.model.Advogado;
import com.fatec.scc.servico.AdvogadoServico;
import com.fatec.scc.model.AdvogadoRepository;
import com.fatec.scc.model.Endereco;
@Service
public class AdvogadoServicol implements AdvogadoServico{
	Logger logger = LogManager.getLogger(AdvogadoServicol.class);
	@Autowired
	private AdvogadoRepository repository;
	
	public Iterable<Advogado> findAll() {
		return repository.findAll();
	}
	public Advogado findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}
	public void deleteById(Long id) {
		repository.deleteById(id);
		logger.info(">>>>>> 2. comando exclusao executado para o id => " + id);
	}
	public Advogado findById(Long id) {
		return repository.findById(id).get();
	}
	public ModelAndView saveOrUpdate (Advogado advogado) {
		ModelAndView modelAndView = new ModelAndView("consultarAdvogado");
		try {
			String endereco = obtemEndereco(advogado.getCep());
			if (endereco != "") {
				advogado.setEndereco(endereco);
				repository.save(advogado);
				logger.info(">>>>>> 4. comando save executado  ");
				modelAndView.addObject("advogados", repository.findAll());
			}

		} catch (Exception e) { // captura validacoes na camada de persistencia
			modelAndView.setViewName("cadastrarAdvogado");
			modelAndView.addObject("message", "Dados invalidos - " + e.getMessage());
			logger.error(">>>>>> 4. erro nao esperado ==> " + e.getMessage());
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
