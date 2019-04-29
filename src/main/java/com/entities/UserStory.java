package com.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idUserStory", "dateDebutEstimeUs" })
@Entity
public class UserStory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUserStory;
	private Long idSprint;
	private String resumeUs;
	private String detailsUs;
	private Integer statutUs;
	private Integer avancementUs;
	private Integer moduleUs;
	private Integer typeUs;
	private Integer prioriteUs;
	private Integer forceUs;
	private Date dateDebutEstimeUs;
	private Date dateFinEstimeUs;
	private Integer heuresEstimeUs;
	private Date dateDebutReelsUs;
	private Date dateFinReelsUs;
	private Integer heuresReelsUs;
	private String commentairesUs;
	private Integer id_User;

	
	
	public Integer getId_User() {
		return id_User;
	}

	public void setId_User(Integer id_User) {
		this.id_User = id_User;
	}

	public Long getIdSprint() {
		return idSprint;
	}

	public void setIdSprint(Long idSprint) {
		this.idSprint = idSprint;
	}

	public Integer getAvancementUs() {
		return avancementUs;
	}

	public void setAvancementUs(Integer avancementUs) {
		this.avancementUs = avancementUs;
	}

	public Long getIdUserStory() {
		return idUserStory;
	}

	public void setIdUserStory(Long idUserStory) {
		this.idUserStory = idUserStory;
	}

	public Long getIdSpring() {
		return idSprint;
	}

	public void setIdSpring(Long idSpring) {
		this.idSprint = idSpring;
	}

	public String getResumeUs() {
		return resumeUs;
	}

	public void setResumeUs(String resumeUs) {
		this.resumeUs = resumeUs;
	}

	public String getDetailsUs() {
		return detailsUs;
	}

	public void setDetailsUs(String detailsUs) {
		this.detailsUs = detailsUs;
	}

	public Integer getStatutUs() {
		return statutUs;
	}

	public void setStatutUs(Integer statutUs) {
		this.statutUs = statutUs;
	}

	public Integer getModuleUs() {
		return moduleUs;
	}

	public void setModuleUs(Integer moduleUs) {
		this.moduleUs = moduleUs;
	}

	public Integer getTypeUs() {
		return typeUs;
	}

	public void setTypeUs(Integer typeUs) {
		this.typeUs = typeUs;
	}

	public Integer getPrioriteUs() {
		return prioriteUs;
	}

	public void setPrioriteUs(Integer prioriteUs) {
		this.prioriteUs = prioriteUs;
	}

	public Integer getForceUs() {
		return forceUs;
	}

	public void setForceUs(Integer forceUs) {
		this.forceUs = forceUs;
	}

	public Date getDateDebutEstimeUs() {
		return dateDebutEstimeUs;
	}

	public void setDateDebutEstimeUs(Date dateDebutEstimeUs) {
		this.dateDebutEstimeUs = dateDebutEstimeUs;
	}

	public Date getDateFinEstimeUs() {
		return dateFinEstimeUs;
	}

	public void setDateFinEstimeUs(Date dateFinEstimeUs) {
		this.dateFinEstimeUs = dateFinEstimeUs;
	}

	public Integer getHeuresEstimeUs() {
		return heuresEstimeUs;
	}

	public void setHeuresEstimeUs(Integer heuresEstimeUs) {
		this.heuresEstimeUs = heuresEstimeUs;
	}

	public Date getDateDebutReelsUs() {
		return dateDebutReelsUs;
	}

	public void setDateDebutReelsUs(Date dateDebutReelsUs) {
		this.dateDebutReelsUs = dateDebutReelsUs;
	}

	public Date getDateFinReelsUs() {
		return dateFinReelsUs;
	}

	public void setDateFinReelsUs(Date dateFinReelsUs) {
		this.dateFinReelsUs = dateFinReelsUs;
	}

	public Integer getHeuresReelsUs() {
		return heuresReelsUs;
	}

	public void setHeuresReelsUs(Integer heuresReelsUs) {
		this.heuresReelsUs = heuresReelsUs;
	}

	public String getCommentairesUs() {
		return commentairesUs;
	}

	public void setCommentairesUs(String commentairesUs) {
		this.commentairesUs = commentairesUs;
	}

	public UserStory(Long idUserStory, Long idSpring, String resumeUs, String detailsUs, Integer statutUs,
			Integer moduleUs, Integer typeUs, Integer prioriteUs, Integer forceUs, Date dateDebutEstimeUs,
			Date dateFinEstimeUs, Integer heuresEstimeUs, Date dateDebutReelsUs, Date dateFinReelsUs,
			Integer heuresReelsUs, String commentairesUs, Integer avancementUs, Integer id_User) {
		super();
		this.idUserStory = idUserStory;
		this.idSprint = idSpring;
		this.resumeUs = resumeUs;
		this.detailsUs = detailsUs;
		this.statutUs = statutUs;
		this.moduleUs = moduleUs;
		this.typeUs = typeUs;
		this.prioriteUs = prioriteUs;
		this.forceUs = forceUs;
		this.dateDebutEstimeUs = dateDebutEstimeUs;
		this.dateFinEstimeUs = dateFinEstimeUs;
		this.heuresEstimeUs = heuresEstimeUs;
		this.dateDebutReelsUs = dateDebutReelsUs;
		this.dateFinReelsUs = dateFinReelsUs;
		this.heuresReelsUs = heuresReelsUs;
		this.commentairesUs = commentairesUs;
		this.avancementUs = avancementUs;
		this.id_User = id_User; 
	}

	public UserStory() {
		super();
		// TODO Auto-generated constructor stub
	}

}
