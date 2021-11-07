package com.rui.api.service;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rui.api.error.exception.NegocioException;
import com.rui.api.model.Pauta;
import com.rui.api.model.SessaoVotacao;
import com.rui.api.repository.sessaovotacao.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoService {

	@Autowired
	private SessaoVotacaoRepository repository;
	
	@Transactional
	public void salvar(SessaoVotacao sessaoVotacao) {
		
		// regra de negócio criada para respeitar o mapeamento OneToOne da classe Pauta (considerei que cada Pauta só pode ter uma SessaoVotacao)
		SessaoVotacao sessao = buscarPorPauta(sessaoVotacao.getPauta());
		
		if (sessao != null) {
			throw new NegocioException("Já existe sessão com a pauta especificada");
		}
		
		// sempre deve ser salva como aberta
		sessaoVotacao.setAberta(true);
		
		if (sessaoVotacao.getTempoDeAberturaEmSegundos() == null) {
			sessaoVotacao.setTempoDeAberturaEmSegundos(60);
		}
		
		SessaoVotacao sessaoVotacaoSalva = this.repository.save(sessaoVotacao);
		
		fecharSessaoAposTempoEspecificado(sessaoVotacaoSalva);

	}

	@Transactional
	private void fecharSessaoAposTempoEspecificado(SessaoVotacao sessaoVotacao) {
		
		// o tempo passado em segundos é convertido em milissegundos para chamada da função que torna a pauta fechada/indisponível
		Integer tempoEmMilissegundos = sessaoVotacao.getTempoDeAberturaEmSegundos().intValue() * 1000;
		
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				sessaoVotacao.setAberta(false);
				repository.save(sessaoVotacao);
				
			}
			
		}, tempoEmMilissegundos);
		
	}
	
	public SessaoVotacao buscarPorPauta(Pauta pauta) {
		SessaoVotacao sessao = this.repository.findByPauta(pauta);
		
		return sessao;
	}
	
}
