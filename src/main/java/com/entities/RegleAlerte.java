package com.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idRegleAlerte" })
@Entity
public class RegleAlerte {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idRegleAlerte;
	private Long idprojet;
	private Long couleur;
	private String description;
	private Float minVal;
	private Float maxVal;
	private Float valeur;

	public Long getIdRegleAlerte() {
		return idRegleAlerte;
	}

	public void setIdRegleAlerte(Long idRegleAlerte) {
		this.idRegleAlerte = idRegleAlerte;
	}

	public Long getIdprojet() {
		return idprojet;
	}

	public void setIdprojet(Long idprojet) {
		this.idprojet = idprojet;
	}

	public Long getCouleur() {
		return couleur;
	}

	public void setCouleur(Long couleur) {
		this.couleur = couleur;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getMinVal() {
		return minVal;
	}

	public void setMinVal(Float minVal) {
		this.minVal = minVal;
	}

	public Float getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(Float maxVal) {
		this.maxVal = maxVal;
	}

	public Float getValeur() {
		return valeur;
	}

	public void setValeur(Float valeur) {
		this.valeur = valeur;
	}

	public RegleAlerte(Long idRegleAlerte, Long idprojet, Long couleur, String description, Float minVal, Float maxVal,
			Float valeur) {
		super();
		this.idRegleAlerte = idRegleAlerte;
		this.idprojet = idprojet;
		this.couleur = couleur;
		this.description = description;
		this.minVal = minVal;
		this.maxVal = maxVal;
		this.valeur = valeur;
	}

	public RegleAlerte() {
		super();
		// TODO Auto-generated constructor stub
	}

}
