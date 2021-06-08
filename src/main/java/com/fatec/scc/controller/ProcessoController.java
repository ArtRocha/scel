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
import com.fatec.scc.servico.ProcessoServico;
import com.fatec.scc.model.Processo;


@Controller
@RequestMapping(path = "/sig")
public class ProcessoController {
	Logger logger = LogManager.getLogger(ProcessoController.class);
	@Autowired
	ProcessoServico servico;

	@GetMapping("/processos")
	public ModelAndView retornaFormDeConsultaTodosProcessos() {
		ModelAndView modelAndView = new ModelAndView("consultarProcesso");
		modelAndView.addObject("processos", servico.findAll());
		return modelAndView;
	}

	@GetMapping("/processo")
	public ModelAndView retornaFormDeCadastroDe(Processo processo) {
		ModelAndView mv = new ModelAndView("cadastrarProcesso");
		mv.addObject("processo", processo);
		return mv;
	}

	@GetMapping("/processo/{id}")
	public ModelAndView excluirNoFormDeConsultaProcesso(@PathVariable("id") Long id) {
		servico.deleteById(id);
		logger.info(">>>>>> 1. servico de exclusao chamado para o id =>  " + id);
		ModelAndView modelAndView = new ModelAndView("consultarProcesso");
		modelAndView.addObject("processos", servico.findAll());
		return modelAndView;
	}

	@PostMapping("/processos")
	public ModelAndView save(@Valid Processo processo, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarProcesso");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarProcesso");
		} else {
			modelAndView = servico.saveOrUpdate(processo);
		}
		return modelAndView;
	}

	@PostMapping("/processos/{id}")
	public ModelAndView atualizaProcesso(@PathVariable("id") Long id, @Valid Processo processo, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarProcesso");
		if (result.hasErrors()) {
			processo.setId(id);
			return new ModelAndView("atualizarProcesso");
		}
		// programacao defensiva - deve-se verificar se o Processo existe antes de atualizar
		Processo umProcesso = servico.findById(id);
		umProcesso.setTribunal(processo.getTribunal());
		umProcesso.setOrgaoJudicial(processo.getOrgaoJudicial());
		umProcesso.setDataDeAbertura(processo.getDataDeAbertura());
		umProcesso.setDescProcesso(processo.getDescProcesso());
		umProcesso.setStatusProcesso(processo.getStatusProcesso());
		modelAndView = servico.saveOrUpdate(umProcesso);
		
		return modelAndView;
	}
}