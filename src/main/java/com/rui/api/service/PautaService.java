package com.rui.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.api.model.Pauta;
import com.rui.api.repository.pauta.PautaRepository;

@Service
public class PautaService {

	@Autowired
	private PautaRepository repository;
	
	// aqui eu optei pela abordagem explícita pensando em debugar o código
	
	public List<Pauta> listarTodos() {
		List<Pauta> lista = this.repository.listarTodos();
		
		return lista;
	}

	@Transactional
	public void salvar(Pauta pauta) {
		this.repository.save(pauta);
	}
	
	public Pauta buscarPorId(Long id) {
		return this.repository.findById(id).orElse(null);
	}
	
}
