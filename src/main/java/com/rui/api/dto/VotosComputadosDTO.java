package com.rui.api.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@lombok.Getter
@lombok.Setter
public class VotosComputadosDTO {

	@Id
	@Column(name = "pauta_id")
	private Long pautaId;
	
	@Column(name = "total_votos_sim")
	private Integer totalVotosSim;
	
	@Column(name = "total_votos_nao")
	private Integer totalVotosNao;
	
}
