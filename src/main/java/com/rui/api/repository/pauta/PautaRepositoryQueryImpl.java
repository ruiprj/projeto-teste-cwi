package com.rui.api.repository.pauta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import com.rui.api.model.Pauta;

public class PautaRepositoryQueryImpl implements PautaRepositoryQuery {
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<Pauta> listarTodos() {
		
		String sql = "select * from public.pauta";
		
		Query query = this.manager.createNativeQuery(sql, Pauta.class);

		@SuppressWarnings("unchecked")
		List<Pauta> lista = query.getResultList();

		return lista;
		
	}

}
