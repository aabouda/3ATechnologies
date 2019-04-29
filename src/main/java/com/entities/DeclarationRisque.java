package com.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idUserStory", "dateDebutEstimeUs" })
@Entity
public class DeclarationRisque {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idDeclarationRisque;
	private Long idRisque;
	private String resume;
	private Date dateDebutRisque;
	private Date dateFinRisque;
	private Integer statut;
	private String commentaires;

	public DeclarationRisque(Long idDeclarationRisque, Long idRisque, String resume, Date dateDebutRisque,
			Date dateFinRisque, Integer statut, String commentaires) {
		super();
		this.idDeclarationRisque = idDeclarationRisque;
		this.idRisque = idRisque;
		this.resume = resume;
		this.dateDebutRisque = dateDebutRisque;
		this.dateFinRisque = dateFinRisque;
		this.statut = statut;
		this.commentaires = commentaires;
	}

	public Long getIdDeclarationRisque() {
		return idDeclarationRisque;
	}

	public void setIdDeclarationRisque(Long idDeclarationRisque) {
		this.idDeclarationRisque = idDeclarationRisque;
	}

	public Long getIdRisque() {
		return idRisque;
	}

	public void setIdRisque(Long idRisque) {
		this.idRisque = idRisque;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Date getDateDebutRisque() {
		return dateDebutRisque;
	}

	public void setDateDebutRisque(Date dateDebutRisque) {
		this.dateDebutRisque = dateDebutRisque;
	}

	public Date getDateFinRisque() {
		return dateFinRisque;
	}

	public void setDateFinRisque(Date dateFinRisque) {
		this.dateFinRisque = dateFinRisque;
	}

	public Integer getStatut() {
		return statut;
	}

	public void setStatut(Integer statut) {
		this.statut = statut;
	}

	public String getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	public DeclarationRisque() {
		super();
		// TODO Auto-generated constructor stub
	}

}
