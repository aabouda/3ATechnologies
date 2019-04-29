package com.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idSp", "dateDebutEstimeSp" })
@Entity
public class Risque {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idRisque;
	private Long idProjet;
	private String risque;
	private String detailsRs;
	private Long impact;
	private Long probabilite;
	private Long criticite;

	public Risque(Long idRisque, Long idProjet, String risque, String detailsRs, Long impact, Long probabilite,
			Long criticite) {
		super();
		this.idRisque = idRisque;
		this.idProjet = idProjet;
		this.risque = risque;
		this.detailsRs = detailsRs;
		this.impact = impact;
		this.probabilite = probabilite;
		this.criticite = criticite;
	}

	public Long getIdRisque() {
		return idRisque;
	}

	public void setIdRisque(Long idRisque) {
		this.idRisque = idRisque;
	}

	public Long getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}

	public String getRisque() {
		return risque;
	}

	public void setRisque(String risque) {
		this.risque = risque;
	}

	public String getDetailsRs() {
		return detailsRs;
	}

	public void setDetailsSp(String detailsRs) {
		this.detailsRs = detailsRs;
	}

	public Long getImpact() {
		return impact;
	}

	public void setImpact(Long impact) {
		this.impact = impact;
	}

	public Long getProbabilite() {
		return probabilite;
	}

	public void setProbabilite(Long probabilite) {
		this.probabilite = probabilite;
	}

	public Long getCriticite() {
		return criticite;
	}

	public void setCriticite(Long criticite) {
		this.criticite = criticite;
	}

	public Risque() {
		super();
		// TODO Auto-generated constructor stub
	}

}
