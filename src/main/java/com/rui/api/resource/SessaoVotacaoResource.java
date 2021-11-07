package com.rui.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rui.api.model.SessaoVotacao;
import com.rui.api.service.SessaoVotacaoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cwi/sessao-votacao")
public class SessaoVotacaoResource {

	@Autowired
	private SessaoVotacaoService service;
	
	@ApiOperation("Criar sessão")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void criar(@RequestBody SessaoVotacao sessaoVotacao) {
		this.service.salvar(sessaoVotacao);
	}
	
}
