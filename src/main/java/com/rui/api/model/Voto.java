package com.rui.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "voto", schema = "public")
@lombok.Getter
@lombok.Setter
@EqualsAndHashCode
@ToString
public class Voto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "pauta_id")
	private Pauta pauta;
	
	@ManyToOne
	@JoinColumn(name = "associado_id")
	private Associado associado;
	
	@Enumerated(EnumType.STRING)
	private Opcao opcao;

}
