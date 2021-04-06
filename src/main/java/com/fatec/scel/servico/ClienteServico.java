package com.fatec.scel.servico;

import org.springframework.web.servlet.ModelAndView;

import com.fatec.scel.model.Cliente;
 
public interface ClienteServico {
    public Iterable<Cliente> findAll() ;
    public Cliente findByCpf(String cpf);
    public void deleteById(Long id);
    public Cliente findById(Long id);
    public ModelAndView saveOrUpdate (Cliente cliente);
    public String obtemEndereco(String cep);

}