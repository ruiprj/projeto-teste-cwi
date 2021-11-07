package com.rui.api.repository.sessaovotacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rui.api.model.Pauta;
import com.rui.api.model.SessaoVotacao;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long>, SessaoVotacaoRepositoryQuery {
	
	public SessaoVotacao findByPauta(Pauta pauta);

}
