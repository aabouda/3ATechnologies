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
	@ManyToOne
	private UserProjet userProjet;

	public Tache() {
		super();
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

}
