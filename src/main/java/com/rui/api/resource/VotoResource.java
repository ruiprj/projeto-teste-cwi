package com.rui.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rui.api.dto.VotosComputadosDTO;
import com.rui.api.model.Voto;
import com.rui.api.service.VotoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cwi/voto")
public class VotoResource {

	@Autowired
	private VotoService service;
	
	@ApiOperation("Criar voto")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void criar(@RequestBody Voto voto) {
		this.service.salvar(voto);
	}
	
	@ApiOperation("Listar total de votos por id da pauta")
	@GetMapping("totais-por-pauta/{pautaId}")
	public ResponseEntity<VotosComputadosDTO> listarTodos(@PathVariable Long pautaId) {
		VotosComputadosDTO votosComputados = this.service.totalDeVotosPorPauta(pautaId);
		
		return ResponseEntity.ok().body(votosComputados);
	}
	
}
