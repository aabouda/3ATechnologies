package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Projet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projectID;
	private String nomProjet;
	private String description;
	private Date dateDebutPrevu;
	private Date dateFinPrevu;
	private Date dateDebutReelle;
	private Date dateFinReelle;
	private String budgetPrevu;
	private String budgetReelle;
	private String phase;
	private String nbRessource;
	private Integer etatProjet;
	private Integer nbRessourceReelle;
	private float  avancement;
	
	
	private Integer calMessage;
	private Integer calSprint;
	private Integer calModuleApp;
	private Integer calEnvironnement;
	private Integer calVerssion;
	
	private Integer calBug;
	private Integer calRelease;
	
	
	
	
	public Integer getCalBug() {
		return calBug;
	}

	public void setCalBug(Integer calBug) {
		this.calBug = calBug;
	}

	public Integer getCalRelease() {
		return calRelease;
	}

	public void setCalRelease(Integer calRelease) {
		this.calRelease = calRelease;
	}

	public Integer getCalMessage() {
		return calMessage;
	}

	public void setCalMessage(Integer calMessage) {
		this.calMessage = calMessage;
	}

	public Integer getCalSprint() {
		return calSprint;
	}

	public void setCalSprint(Integer calSprint) {
		this.calSprint = calSprint;
	}

	public Integer getCalModuleApp() {
		return calModuleApp;
	}

	public void setCalModuleApp(Integer calModuleApp) {
		this.calModuleApp = calModuleApp;
	}

	public Integer getCalEnvironnement() {
		return calEnvironnement;
	}

	public void setCalEnvironnement(Integer calEnvironnement) {
		this.calEnvironnement = calEnvironnement;
	}

	public Integer getCalVerssion() {
		return calVerssion;
	}

	public void setCalVerssion(Integer calVerssion) {
		this.calVerssion = calVerssion;
	}

	public void setAvancement(float avancement) {
		this.avancement = avancement;
	}

	public Integer getNbRessourceReelle() {
		return nbRessourceReelle;
	}

	public void setNbRessourceReelle(Integer nbRessourceReelle) {
		this.nbRessourceReelle = nbRessourceReelle;
	}

	public float getAvancement() {
		return avancement;
	}

	public void setAvancement(Integer avancement) {
		this.avancement = avancement;
	}

	public long getProjectID() {
		return projectID;
	}

	public void setProjectID(long projectID) {
		this.projectID = projectID;
	}

	public String getNomProjet() {
		return nomProjet;
	}

	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateDebutPrevu() {
		return dateDebutPrevu;
	}

	public void setDateDebutPrevu(Date dateDebutPrevu) {
		this.dateDebutPrevu = dateDebutPrevu;
	}

	public Date getDateFinPrevu() {
		return dateFinPrevu;
	}

	public void setDateFinPrevu(Date dateFinPrevu) {
		this.dateFinPrevu = dateFinPrevu;
	}

	public Date getDateDebutReelle() {
		return dateDebutReelle;
	}

	public void setDateDebutReelle(Date dateDebutReelle) {
		this.dateDebutReelle = dateDebutReelle;
	}

	public Date getDateFinReelle() {
		return dateFinReelle;
	}

	public void setDateFinReelle(Date dateFinReelle) {
		this.dateFinReelle = dateFinReelle;
	}

	public String getBudgetPrevu() {
		return budgetPrevu;
	}

	public void setBudgetPrevu(String budgetPrevu) {
		this.budgetPrevu = budgetPrevu;
	}

	public String getBudgetReelle() {
		return budgetReelle;
	}

	public void setBudgetReelle(String budgetReelle) {
		this.budgetReelle = budgetReelle;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getNbRessource() {
		return nbRessource;
	}

	public void setNbRessource(String nbRessource) {
		this.nbRessource = nbRessource;
	}

	public Integer getEtatProjet() {
		return etatProjet;
	}

	public void setEtatProjet(Integer etatProjet) {
		this.etatProjet = etatProjet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	


	public Projet(long projectID, String nomProjet, String description, Date dateDebutPrevu, Date dateFinPrevu,
			Date dateDebutReelle, Date dateFinReelle, String budgetPrevu, String budgetReelle, String phase,
			String nbRessource, Integer etatProjet, Integer nbRessourceReelle, float avancement, Integer calMessage,
			Integer calSprint, Integer calModuleApp, Integer calEnvironnement, Integer calVerssion, Integer calBug,
			Integer calRelease) {
		super();
		this.projectID = projectID;
		this.nomProjet = nomProjet;
		this.description = description;
		this.dateDebutPrevu = dateDebutPrevu;
		this.dateFinPrevu = dateFinPrevu;
		this.dateDebutReelle = dateDebutReelle;
		this.dateFinReelle = dateFinReelle;
		this.budgetPrevu = budgetPrevu;
		this.budgetReelle = budgetReelle;
		this.phase = phase;
		this.nbRessource = nbRessource;
		this.etatProjet = etatProjet;
		this.nbRessourceReelle = nbRessourceReelle;
		this.avancement = avancement;
		this.calMessage = calMessage;
		this.calSprint = calSprint;
		this.calModuleApp = calModuleApp;
		this.calEnvironnement = calEnvironnement;
		this.calVerssion = calVerssion;
		this.calBug = calBug;
		this.calRelease = calRelease;
	}

	public Projet() {
		super();
	}

}
