package com.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bug implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idBug;
	private Integer idBugExt;
	private Integer idProjet;
	private Integer idBugEtat;
	private Integer idBugEnvironnement;
	private Integer idBugVersion;
	private Integer idBugImpact;

	public Long getIdBug() {
		return idBug;
	}

	public void setIdBug(Long idBug) {
		this.idBug = idBug;
	}

	public Integer getidProjet() {
		return idProjet;
	}

	public void setidProjet(Integer idProjet) {
		this.idProjet = idProjet;
	}

	public Integer getIdBugExt() {
		return idBugExt;
	}

	public void setIdBugExt(Integer idBugExt) {
		this.idBugExt = idBugExt;
	}

	public Integer getIdBugEtat() {
		return idBugEtat;
	}

	public void setIdBugEtat(Integer idBugEtat) {
		this.idBugEtat = idBugEtat;
	}

	public Integer getIdBugEnvironnement() {
		return idBugEnvironnement;
	}

	public void setIdBugEnvironnement(Integer idBugEnvironnement) {
		this.idBugEnvironnement = idBugEnvironnement;
	}

	public Integer getIdBugVersion() {
		return idBugVersion;
	}

	public void setIdBugVersion(Integer idBugVersion) {
		this.idBugVersion = idBugVersion;
	}

	public Integer getIdBugImpact() {
		return idBugImpact;
	}

	public void setIdBugImpact(Integer idBugImpact) {
		this.idBugImpact = idBugImpact;
	}

	public Bug(Long idBug, Integer idBugExt, Integer idProjet, Integer idBugEtat, Integer idBugEnvironnement,
			Integer idBugVersion, Integer idBugImpact) {
		super();
		this.idBug = idBug;
		this.idBugExt = idBugExt;
		this.idProjet = idProjet;
		this.idBugEtat = idBugEtat;
		this.idBugEnvironnement = idBugEnvironnement;
		this.idBugVersion = idBugVersion;
		this.idBugImpact = idBugImpact;
	}

	public Bug() {
		super();
		// TODO Auto-generated constructor stub
	}

}
