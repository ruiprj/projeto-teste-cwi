package com.rui.api.repository.voto;

import java.util.List;

import com.rui.api.dto.VotosComputadosDTO;
import com.rui.api.model.Voto;

public interface VotoRepositoryQuery {
	
	public List<Voto> buscarPorPautaEAssociado(Voto voto);
	
	public VotosComputadosDTO totalDeVotosPorPauta(Long pautaId);

}
