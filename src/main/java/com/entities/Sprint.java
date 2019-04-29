package com.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idSp", "dateDebutEstimeSp" })
@Entity
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idSp;
	private Long idProjet;
	private String resumeSp;
	private String detailsSp;
	private String commentairesSp;
	private Date dateDebutEstimeSp;
	private Date dateFinEstimeSp;
	private Date dateDebutReels;
	private Date dateFinReels;
	private Double avancementSp;

	
	
	public Long getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}

	public Double getAvancementSp() {
		return avancementSp;
	}

	public void setAvancementSp(Double avancementSp) {
		this.avancementSp = avancementSp;
	}

	public Long getIdSp() {
		return idSp;
	}

	public void setIdSp(Long idSp) {
		this.idSp = idSp;
	}


	public String getResumeSp() {
		return resumeSp;
	}

	public void setResumeSp(String resumeSp) {
		this.resumeSp = resumeSp;
	}

	public String getDetailsSp() {
		return detailsSp;
	}

	public void setDetailsSp(String detailsSp) {
		this.detailsSp = detailsSp;
	}

	public String getCommentairesSp() {
		return commentairesSp;
	}

	public void setCommentairesSp(String commentairesSp) {
		this.commentairesSp = commentairesSp;
	}

	public Date getDateDebutEstimeSp() {
		return dateDebutEstimeSp;
	}

	public void setDateDebutEstimeSp(Date dateDebutEstimeSp) {
		this.dateDebutEstimeSp = dateDebutEstimeSp;
	}

	public Date getDateFinEstimeSp() {
		return dateFinEstimeSp;
	}

	public void setDateFinEstimeSp(Date dateFinEstimeSp) {
		this.dateFinEstimeSp = dateFinEstimeSp;
	}

	public Date getDateDebutReels() {
		return dateDebutReels;
	}

	public void setDateDebutReels(Date dateDebutReels) {
		this.dateDebutReels = dateDebutReels;
	}

	public Date getDateFinReels() {
		return dateFinReels;
	}

	public void setDateFinReels(Date dateFinReels) {
		this.dateFinReels = dateFinReels;
	}

	public Sprint(Long idSp, Long idprojet, String resumeSp, String detailsSp, String commentairesSp,
			Date dateDebutEstimeSp, Date dateFinEstimeSp, Date dateDebutReels, Date dateFinReels, Double avancementSp) {
		super();
		this.idSp = idSp;
		this.idProjet = idprojet;
		this.resumeSp = resumeSp;
		this.detailsSp = detailsSp;
		this.commentairesSp = commentairesSp;
		this.dateDebutEstimeSp = dateDebutEstimeSp;
		this.dateFinEstimeSp = dateFinEstimeSp;
		this.dateDebutReels = dateDebutReels;
		this.dateFinReels = dateFinReels;
		this.avancementSp = avancementSp;
	}

	public Sprint() {
		super();
		// TODO Auto-generated constructor stub
	}

}
