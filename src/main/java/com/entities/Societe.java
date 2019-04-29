package com.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idAlerte", "dateAlerte" })
@Entity
public class Societe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_societe;
	private String nom;
	private String description;
	private Integer nombre_salarie;
	private Integer nombre_abonnement_t1;
	private Integer nombre_abonnement_t2;
	private Integer nombre_abonnement_t3;
	private Date date_debut;
	private Date date_fin;
	private String adresse;
	private String code_postal;
	private String paye;
	private String code_tva;
	private Integer active;

	public Long getId_societe() {
		return id_societe;
	}

	public void setId_societe(Long id_societe) {
		this.id_societe = id_societe;
	}

	public String getNom() {
		return nom;
	}

	public Societe(Long id_societe, String nom, String description, Integer nombre_salarie,
			Integer nombre_abonnement_t1, Integer nombre_abonnement_t2, Integer nombre_abonnement_t3, Date date_debut,
			Date date_fin, String adresse, String code_postal, String paye, String code_tva, Integer active) {
		super();
		this.id_societe = id_societe;
		this.nom = nom;
		this.description = description;
		this.nombre_salarie = nombre_salarie;
		this.nombre_abonnement_t1 = nombre_abonnement_t1;
		this.nombre_abonnement_t2 = nombre_abonnement_t2;
		this.nombre_abonnement_t3 = nombre_abonnement_t3;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.adresse = adresse;
		this.code_postal = code_postal;
		this.paye = paye;
		this.code_tva = code_tva;
		this.active = active;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNombre_salarie() {
		return nombre_salarie;
	}

	public void setNombre_salarie(Integer nombre_salarie) {
		this.nombre_salarie = nombre_salarie;
	}

	public Integer getNombre_abonnement_t1() {
		return nombre_abonnement_t1;
	}

	public void setNombre_abonnement_t1(Integer nombre_abonnement_t1) {
		this.nombre_abonnement_t1 = nombre_abonnement_t1;
	}

	public Integer getNombre_abonnement_t2() {
		return nombre_abonnement_t2;
	}

	public void setNombre_abonnement_t2(Integer nombre_abonnement_t2) {
		this.nombre_abonnement_t2 = nombre_abonnement_t2;
	}

	public Integer getNombre_abonnement_t3() {
		return nombre_abonnement_t3;
	}

	public void setNombre_abonnement_t3(Integer nombre_abonnement_t3) {
		this.nombre_abonnement_t3 = nombre_abonnement_t3;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getPaye() {
		return paye;
	}

	public void setPaye(String paye) {
		this.paye = paye;
	}

	public String getCode_tva() {
		return code_tva;
	}

	public void setCode_tva(String code_tva) {
		this.code_tva = code_tva;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Societe() {
		super();
		// TODO Auto-generated constructor stub
	}

}
