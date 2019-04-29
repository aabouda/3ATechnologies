package com.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idModuleApp", "nomModuleApp" })
@Entity
public class ModuleApp {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idModuleApp;
	private Long idProjet;
	private Long nb_Cas_Test;
	private String nomModuleApp;
	private String description;
	
	
	
	public Long getNb_Cas_Test() {
		return nb_Cas_Test;
	}
	public void setNb_Cas_Test(Long nb_Cas_Test) {
		this.nb_Cas_Test = nb_Cas_Test;
	}
	public Long getIdModuleApp() {
		return idModuleApp;
	}
	public void setIdModuleApp(Long idModuleApp) {
		this.idModuleApp = idModuleApp;
	}
	public Long getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}
	public String getNomModuleApp() {
		return nomModuleApp;
	}
	public void setNomModuleApp(String nomModuleApp) {
		this.nomModuleApp = nomModuleApp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ModuleApp(Long idModuleApp, Long idProjet, Long nb_Cas_Test, String nomModuleApp, String description) {
		super();
		this.idModuleApp = idModuleApp;
		this.idProjet = idProjet;
		this.nb_Cas_Test = nb_Cas_Test;
		this.nomModuleApp = nomModuleApp;
		this.description = description;
	}
	public ModuleApp() {
		super();
		// TODO Auto-generated constructor stub
	}


}
