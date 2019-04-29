package com.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BugVersion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idBug;
	private String labelle;

	public Long getIdBug() {
		return idBug;
	}

	public void setIdBug(Long idBug) {
		this.idBug = idBug;
	}

	public String getLabelle() {
		return labelle;
	}

	public void setLabelle(String labelle) {
		this.labelle = labelle;
	}

	public BugVersion(Long idBug, String labelle) {
		super();
		this.idBug = idBug;
		this.labelle = labelle;
	}

	public BugVersion() {
		super();
		// TODO Auto-generated constructor stub
	}

}
