package com.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ennumeration.EtatProjet;

@Entity
public class Projet implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long projectID;
	private String name;
	private String description;
	private String dateDebut;
	private String dateFin;
	private String budget;
	private EtatProjet etatProjet;
	
	
	public Projet() {
		super();
	}
	public Projet(long projectID, String name, String description, String dateDebut, String dateFin, String budget) {
		super();
		this.projectID = projectID;
		this.name = name;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.budget = budget;
	}
	public Projet(String name, String description, String dateDebut, String dateFin, String budget, EtatProjet etatProjet) {
		super();
		this.name = name;
		this.description = description;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.budget = budget;
		this.etatProjet = etatProjet;
	}
	public String getBudget() {
		return budget;
	}
	public String getDateDebut() {
		return dateDebut;
	}
	public String getDateFin() {
		return dateFin;
	}
	public String getDescription() {
		return description;
	}
	public String getName() {
		return name;
	}
	public long getProjectID() {
		return projectID;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}
	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProjectID(long projectID) {
		this.projectID = projectID;
	}
	public EtatProjet getEtatProjet() {
		return etatProjet;
	}
	public void setEtatProjet(EtatProjet etatProjet) {
		this.etatProjet = etatProjet;
	}
	
	
	

}
