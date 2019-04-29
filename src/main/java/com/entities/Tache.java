package com.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tache implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTache;
	private String userStory;
	private String tacheName;
	private int estimation;
	
	@ManyToOne
	private UserProjet userProjet;

	public Tache() {
		super();
	}

	
	
	public Tache(int idTache, String userStory, String tacheName, int estimation, UserProjet userProjet) {
		super();
		this.idTache = idTache;
		this.userStory = userStory;
		this.tacheName = tacheName;
		this.estimation = estimation;
		this.userProjet = userProjet;
	}



	public int getIdTache() {
		return idTache;
	}


	public void setIdTache(int idTache) {
		this.idTache = idTache;
	}


	public UserProjet getUserProjet() {
		return userProjet;
	}

	public void setUserProjet(UserProjet userProjet) {
		this.userProjet = userProjet;
	}


	public String getUserStory() {
		return userStory;
	}


	public void setUserStory(String userStory) {
		this.userStory = userStory;
	}


	public String getTacheName() {
		return tacheName;
	}


	public void setTacheName(String tacheName) {
		this.tacheName = tacheName;
	}


	public int getEstimation() {
		return estimation;
	}


	public void setEstimation(int estimation) {
		this.estimation = estimation;
	}

	
}
