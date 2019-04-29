package com.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idEnvironnement", "nomEnvironnement" })
@Entity
public class Environnement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idEnvironnement;
	private Long idProjet;
	private String nomEnvironnement;
	private String description;
	public Long getIdEnvironnement() {
		return idEnvironnement;
	}
	public void setIdEnvironnement(Long idEnvironnement) {
		this.idEnvironnement = idEnvironnement;
	}
	public Long getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}
	public String getNomEnvironnement() {
		return nomEnvironnement;
	}
	public void setNomEnvironnement(String nomEnvironnement) {
		this.nomEnvironnement = nomEnvironnement;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Environnement(Long idEnvironnement, Long idProjet, String nomEnvironnement, String description) {
		super();
		this.idEnvironnement = idEnvironnement;
		this.idProjet = idProjet;
		this.nomEnvironnement = nomEnvironnement;
		this.description = description;
	}
	public Environnement() {
		super();
		// TODO Auto-generated constructor stub
	}


}
