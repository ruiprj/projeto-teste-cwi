package com.rui.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.rui.api.dto.StatusVotoAssociadoDTO;
import com.rui.api.dto.VotosComputadosDTO;
import com.rui.api.error.exception.CpfInvalidoException;
import com.rui.api.error.exception.NegocioException;
import com.rui.api.model.Associado;
import com.rui.api.model.Pauta;
import com.rui.api.model.SessaoVotacao;
import com.rui.api.model.Voto;
import com.rui.api.repository.voto.VotoRepository;

@Service
public class VotoService {

	@Autowired
	private VotoRepository repository;
	
	@Autowired
	private PautaService pautaService;
	
	@Autowired
	private SessaoVotacaoService sessaoVotacaoService;
	
	@Autowired
	private AssociadoService associadoService;

	@Transactional
	public void salvar(Voto voto) {
		SessaoVotacao sessao = this.sessaoVotacaoService.buscarPorPauta(voto.getPauta());
		
		if (!sessao.getAberta()) {
			throw new NegocioException("O voto não pode ser computado pois a sessão da pauta está fechada para votação");
		}
		
		List<Voto> listaVotosJaRealizados = buscarPorPautaEAssociado(voto);
		if (listaVotosJaRealizados != null && !listaVotosJaRealizados.isEmpty()) {
			throw new NegocioException("O voto não pode ser computado pois o associado já votou");
		}
		
		Associado associado = this.associadoService.buscarPorId(voto.getAssociado().getId());
		checarStatusDoAssociado(associado.getCpf());
		
		this.repository.save(voto);
	}
	
	// O método abaixo busca o objeto desejado de forma mais rápida. Poderia também ser feito no método 'public void salvar(Voto voto)'
	// utilizando o método nativo p/ gerar uma lista, e realizar um foreach p/ conseguir o mesmo resultado. Porém, no caso de uma base de dados
	// muito grande ocorreria lentidão, que seria algo a ser considerado
	public List<Voto> buscarPorPautaEAssociado(Voto voto) {
		List<Voto> lista = this.repository.buscarPorPautaEAssociado(voto);
		
		return lista;
	}
	
	public VotosComputadosDTO totalDeVotosPorPauta(Long pautaId) {
		Pauta pauta = this.pautaService.buscarPorId(pautaId);
		if (pauta == null) {
			throw new NegocioException("Não existe pauta com o código especificado");
		}
		
		VotosComputadosDTO votosComputados = this.repository.totalDeVotosPorPauta(pautaId);
		if (votosComputados.getTotalVotosSim().equals(0) && votosComputados.getTotalVotosNao().equals(0)) {
			throw new NegocioException("Não existem votos computados para a pauta solicitada");
		}
		
		return votosComputados;
	}
	
	// optei por colocar esse método de checagem na classe VotoService pois o serviço retorna especificamente o status para votação
	private void checarStatusDoAssociado(String cpf) {
		String url = "https://user-info.herokuapp.com/users/" + cpf;
		RestTemplate restTemplate = new RestTemplate();
//	    StatusVotoAssociadoDTO status = new StatusVotoAssociadoDTO();
		
		try {
			
			restTemplate.getForObject(url, StatusVotoAssociadoDTO.class);
			
		} catch (HttpClientErrorException e) {
			
			throw new CpfInvalidoException("O associado não pode votar com CPF inválido");
			
		}
	}
	
}
