package com.fatec.scc.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scc.model.Audiencia;
import com.fatec.scc.servico.AudienciaServico;

@Controller
@RequestMapping(path = "/sig")
public class AudienciaController {
	Logger logger = LogManager.getLogger(AudienciaController.class);
	@Autowired
	AudienciaServico servico;

	@GetMapping("/audiencias")
	public ModelAndView retornaFormDeConsultaTodosAudiencias() {
		ModelAndView modelAndView = new ModelAndView("consultarAudiencias");
		modelAndView.addObject("audiencias", servico.findAll());
		return modelAndView;
	}

	@GetMapping("/audiencia")
	public ModelAndView retornaFormDeCadastroDeAudiencia(Audiencia audiencia) {
		ModelAndView mv = new ModelAndView("cadastrarAudiencia");
		mv.addObject("audiencia", audiencia);
		return mv;
	}

	@GetMapping("/audiencias/{id}")
	public ModelAndView excluirNoFormDeConsultaAudiencia(@PathVariable("id") Long id) {
		servico.deleteById(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id =>  " + id);
		ModelAndView modelAndView = new ModelAndView("consultarAudiencia");
		modelAndView.addObject("audiencias", servico.findAll());
		return modelAndView;
	}

	@PostMapping("/audiencias")
	public ModelAndView save(@Valid Audiencia audiencia, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarAudiencia");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarAudiencia");
		} else {
			modelAndView = servico.saveOrUpdate(audiencia);
		}
		return modelAndView;
	}

	@PostMapping("/audiencias/{id}")
	public ModelAndView atualizaAudiencias(@PathVariable("id") Long id, @Valid Audiencia audiencia, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarAudiencias");
		if (result.hasErrors()) {
			audiencia.setId(id);
			return new ModelAndView("atualizarAudiencias");
		}
		// programacao defensiva - deve-se verificar se o Cliente existe antes de
		// atualizar
		Audiencia umAudiencia = servico.findById(id);
		umAudiencia.setData(audiencia.getData());
		umAudiencia.setLocal(audiencia.getLocal());
		//adicionar horario aqui
		umAudiencia.setCep(audiencia.getCep());// deve-se obter o endereco antes de salvar
		modelAndView = servico.saveOrUpdate(umAudiencia);

		return modelAndView;
	}
}
