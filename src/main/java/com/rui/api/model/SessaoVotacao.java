package com.rui.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "sessao_votacao", schema = "public")
@lombok.Getter
@lombok.Setter
@EqualsAndHashCode
@ToString
public class SessaoVotacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "pauta_id")
	private Pauta pauta;
	
	private Boolean aberta;
	
	@Column(name = "tempo_de_abertura_em_segundos")
	private Integer tempoDeAberturaEmSegundos;

}
