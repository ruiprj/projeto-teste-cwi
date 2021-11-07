package com.rui.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rui.api.model.Associado;
import com.rui.api.service.AssociadoService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cwi/associado")
public class AssociadoResource {
	
	@Autowired
	private AssociadoService service;
	
	@ApiOperation("Criar associado")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void criar(@RequestBody Associado associado) {
		log.info("Criando associado...");
		
		this.service.salvar(associado);
	}

}
