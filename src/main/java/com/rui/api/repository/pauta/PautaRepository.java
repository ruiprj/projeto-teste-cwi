package com.rui.api.repository.pauta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rui.api.model.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long>, PautaRepositoryQuery {
	
	// coloquei aqui um método nativo do spring jpa p/ testes
	// poderia utilizar também JPQL dependendo pelo padrão utilizado na empresa/projeto
	public List<Pauta> findAll();

}
