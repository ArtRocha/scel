package com.fatec.scel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@GetMapping("/")
	public ModelAndView menu() {
		return new ModelAndView("paginaMenu");
	}

	@GetMapping("/login")
	public ModelAndView autenticacao() {
		return new ModelAndView ("paginaLogin");
		}
	

 	@GetMapping ("/cliente/cadastrar") 
 	public ModelAndView cadastrarCliente() {
 			return new ModelAndView ("cadastrarCliente"); }
 	
 	@GetMapping("/advogado/cadastrar")
 public ModelAndView cadastrarAdvogado() { 
 		return new ModelAndView("cadastrarAdvogado"); 
 
 	} 
	@GetMapping("/audiencia/cadastrar")
 public ModelAndView cadastrarAudiencia() { 
 		return new ModelAndView("cadastrarAudiencia"); 
 
 	}
	@GetMapping("/processo/cadastrar")
 public ModelAndView cadastrarProcesso() { 
 		return new ModelAndView("cadastrarProcesso"); 
 
 	}
 }