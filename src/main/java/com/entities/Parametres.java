package com.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idParametres", "idProjet" })
@Entity
public class Parametres {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idParametres;
	private Long idProjet;
	private Long timeExecutionBatsh;
	
	
	public Long getIdParametres() {
		return idParametres;
	}


	public void setIdParametres(Long idParametres) {
		this.idParametres = idParametres;
	}


	public Long getIdProjet() {
		return idProjet;
	}


	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}


	public Long getTimeExecutionBatsh() {
		return timeExecutionBatsh;
	}


	public void setTimeExecutionBatsh(Long timeExecutionBatsh) {
		this.timeExecutionBatsh = timeExecutionBatsh;
	}


	public Parametres(Long idParametres, Long idProjet, Long timeExecutionBatsh) {
		super();
		this.idParametres = idParametres;
		this.idProjet = idProjet;
		this.timeExecutionBatsh = timeExecutionBatsh;
	}


	public Parametres() {
		super();
		// TODO Auto-generated constructor stub
	}


}
