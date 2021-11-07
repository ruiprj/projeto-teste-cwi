package com.rui.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rui.api.model.Pauta;
import com.rui.api.service.PautaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cwi/pauta")
public class PautaResource {

	@Autowired
	private PautaService service;
	
	// nos métodos get e post abaixo eu optei por duas abordagens diferentes de reposta do http status
	// em meu trabalho atual não utilizamos esse padrão
	
	@ApiOperation("Listar todas as pautas")
	@GetMapping("todos")
	public ResponseEntity<List<Pauta>> listarTodos() {
		List<Pauta> lista = this.service.listarTodos();
		
		return ResponseEntity.ok().body(lista);
	}
	
	@ApiOperation("Criar pauta")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void criar(@RequestBody Pauta pauta) {
		this.service.salvar(pauta);
	}
	
}
