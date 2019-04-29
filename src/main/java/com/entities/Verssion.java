package com.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idVerssion", "nomVerssion" })
@Entity
public class Verssion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idVerssion;
	private Long idProjet;
	private String nomVerssion;
	private String description;
	public Long getIdVerssion() {
		return idVerssion;
	}
	public void setIdVerssion(Long idVerssion) {
		this.idVerssion = idVerssion;
	}
	public Long getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}
	public String getNomVerssion() {
		return nomVerssion;
	}
	public void setNomVerssion(String nomVerssion) {
		this.nomVerssion = nomVerssion;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Verssion(Long idVerssion, Long idProjet, String nomVerssion, String description) {
		super();
		this.idVerssion = idVerssion;
		this.idProjet = idProjet;
		this.nomVerssion = nomVerssion;
		this.description = description;
	}
	public Verssion() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
}
