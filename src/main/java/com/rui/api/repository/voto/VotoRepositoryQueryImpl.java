package com.rui.api.repository.voto;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.rui.api.dto.VotosComputadosDTO;
import com.rui.api.filter.VotoFilter;
import com.rui.api.model.Voto;

public class VotoRepositoryQueryImpl implements VotoRepositoryQuery {
	
	@Autowired
	private EntityManager manager;
	
	// se fosse necessária paginação a abordagem teria sido diferente, utilizando Page/PageImpl
	@Override
	public List<Voto> buscarPorPautaEAssociado(Voto voto) {
		VotoFilter filter = new VotoFilter();
		filter.setPautaId(voto.getPauta().getId());
		filter.setAssociadoId(voto.getAssociado().getId());
		
		Query query = this.manager.createNativeQuery(filter.getSql(), Voto.class);

		for (Map.Entry<String, Object> par1 : filter.getWhere().entrySet()) {
			query.setParameter(par1.getKey(), par1.getValue());
		}

		@SuppressWarnings("unchecked")
		List<Voto> lista = query.getResultList();

		return lista;
	}

	@Override
	public VotosComputadosDTO totalDeVotosPorPauta(Long pautaId) {
		String sql =  "  select "
					+ "       A.id as pauta_id, "
					+ "       Case When B.voto_sim is null Then 0 Else B.voto_sim End as total_votos_sim, "
					+ "       Case When C.voto_nao is null Then 0 Else C.voto_nao End as total_votos_nao "
					+ "  from public.pauta A "
					+ "  Left Join ( "
					+ "	    select pauta_id, Count(opcao) as voto_sim from public.voto where opcao = 'SIM' "
					+ "	    group by pauta_id "
					+ "  ) B On A.id = B.pauta_id "
					+ "  Left Join ( "
					+ "  	select pauta_id, Count(opcao) as voto_nao from public.voto where opcao = 'NAO' "
					+ "	    group by pauta_id "
					+ "  ) C On A.id = C.pauta_id "
					+ "  where 1=1 and A.id = :pautaId_p";
		
		Query query = this.manager.createNativeQuery(sql, VotosComputadosDTO.class);
		query.setParameter("pautaId_p", pautaId);
		
		VotosComputadosDTO votosComputados = (VotosComputadosDTO) query.getSingleResult();
		
		return votosComputados;
	}

}
