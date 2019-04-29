package com.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idAlerte", "dateAlerte" })
@Entity
public class Alertes {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idAlerte;
	private Long idProjet;
	private String titreAlerte;
	private Date dateAlerte;
	private Integer niveauAletre;
	private String typeAlerte;
	private String description;
	private Integer lu;

	public Integer getLu() {
		return lu;
	}

	public void setLu(Integer lu) {
		this.lu = lu;
	}

	public Long getIdAlerte() {
		return idAlerte;
	}

	public void setIdAlerte(Long idAlerte) {
		this.idAlerte = idAlerte;
	}

	public Long getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}

	public String getTitreAlerte() {
		return titreAlerte;
	}

	public void setTitreAlerte(String titreAlerte) {
		this.titreAlerte = titreAlerte;
	}

	public Date getDateAlerte() {
		return dateAlerte;
	}

	public void setDateAlerte(Date dateAlerte) {
		this.dateAlerte = dateAlerte;
	}

	public Integer getNiveauAletre() {
		return niveauAletre;
	}

	public void setNiveauAletre(Integer niveauAletre) {
		this.niveauAletre = niveauAletre;
	}

	public String getTypeAlerte() {
		return typeAlerte;
	}

	public void setTypeAlerte(String typeAlerte) {
		this.typeAlerte = typeAlerte;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Alertes(Long idAlerte, Long idProjet, String titreAlerte, Date dateAlerte, Integer niveauAletre,
			String typeAlerte, String description, Integer lu) {
		super();
		this.idAlerte = idAlerte;
		this.idProjet = idProjet;
		this.titreAlerte = titreAlerte;
		this.dateAlerte = dateAlerte;
		this.niveauAletre = niveauAletre;
		this.typeAlerte = typeAlerte;
		this.description = description;
		this.lu = lu;
	}

	public Alertes() {
		super();
		// TODO Auto-generated constructor stub
	}

}
