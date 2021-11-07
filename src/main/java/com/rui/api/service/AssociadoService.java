package com.rui.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.api.model.Associado;
import com.rui.api.repository.associado.AssociadoRepository;

@Service
public class AssociadoService {
	
	@Autowired
	private AssociadoRepository repository;
	
	@Transactional
	public void salvar(Associado associado) {
		this.repository.save(associado);
	}
	
	public Associado buscarPorId(Long id) {
		return this.repository.findById(id).orElse(null);
	}
	
}
