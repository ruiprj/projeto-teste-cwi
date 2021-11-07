package com.rui.api.filter;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;

@lombok.Getter
@lombok.Setter
public class VotoFilter implements Serializable {

	// Nessa classe optei por fazer tudo via sql, q é a forma com a qual trabalho hoje. Fazer uso de Criteria ou outras funções do JPA
	// não seria um problema 
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long pautaId;
	private Long associadoId;
	private String opcao;
	
	@JsonIgnore
	private String orderBy = "1";
	
	@JsonIgnore
	private HashMap<String, Object> where = new HashMap<>();

	@JsonIgnore
	private String sqlWhere = "";

	// Se a lista fosse paginada, seria aqui em seriam setadas as configurações
	// private Boolean paginar = false;
	// private Integer pageableSize = 10;
	// private Integer pageablePage = 0;
	
	public String getSql() {
		String sql = "SELECT * FROM public.voto where 1=1 ";

		this.where = new HashMap<>();
		this.sqlWhere = "";

		if (this.id != null) {
			this.sqlWhere = this.sqlWhere + " and id = :id_p ";
			where.put("id_p", this.id);
		}
		
		if (this.pautaId != null) {
			this.sqlWhere = this.sqlWhere + " and pauta_id = :pauta_id_p ";
			where.put("pauta_id_p", this.pautaId);
		}
		
		if (this.associadoId != null) {
			this.sqlWhere = this.sqlWhere + " and associado_id = :associado_id_p ";
			where.put("associado_id_p", this.associadoId);
		}
		
		if (this.opcao != null) {
			this.sqlWhere = this.sqlWhere + " and opcao = :opcao_p ";
			where.put("opcao_p", this.opcao);
		}


		return sql + this.sqlWhere + " ORDER BY " + this.orderBy;
	}
	
	public String getSqlCount() {
		String sql = "SELECT count(*) FROM publico.voto where 1=1 ";
		return sql + this.sqlWhere;
	}

}
