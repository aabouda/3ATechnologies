package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idAssignation", "idUs" })
@Entity
public class Assignation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idAssignation;
	private Long idUS;
	private Long idUser;
	private Integer assigne;
	private Integer reviseur;

	public Long getIdAss() {
		return idUser;
	}

	public void setIdAss(Long idAss) {
		this.idUser = idAss;
	}

	public Long getIdUs() {
		return idUS;
	}

	public void setIdUs(Long idUs) {
		this.idUS = idUs;
	}

	public Long getIdAssignation() {
		return idAssignation;
	}

	public void setIdAssignation(Long idAssignation) {
		this.idAssignation = idAssignation;
	}

	public Integer getAssigne() {
		return assigne;
	}

	public void setAssigne(Integer assigne) {
		this.assigne = assigne;
	}

	public Integer getReviseur() {
		return reviseur;
	}

	public void setReviseur(Integer reviseur) {
		this.reviseur = reviseur;
	}

	public Assignation(Long idAss, Long idUs, Long idAssignation, Integer assigne, Integer reviseur) {
		super();
		this.idUser = idAss;
		this.idUS = idUs;
		this.idAssignation = idAssignation;
		this.assigne = assigne;
		this.reviseur = reviseur;
	}

	public Assignation() {
		super();
		// TODO Auto-generated constructor stub
	}

}
