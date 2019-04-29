package com.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idLangue" })
@Entity
public class Langues {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLangue;
	private String langue1;
	private String langue2;
	private String langue3;
	private Integer module;
	private String descModule;
	public Long getIdLangue() {
		return idLangue;
	}
	public void setIdLangue(Long idLangue) {
		this.idLangue = idLangue;
	}
	public String getLangue1() {
		return langue1;
	}
	public void setLangue1(String langue1) {
		this.langue1 = langue1;
	}
	public String getLangue2() {
		return langue2;
	}
	public void setLangue2(String langue2) {
		this.langue2 = langue2;
	}
	public String getLangue3() {
		return langue3;
	}
	public void setLangue3(String langue3) {
		this.langue3 = langue3;
	}
	public Integer getModule() {
		return module;
	}
	public void setModule(Integer module) {
		this.module = module;
	}
	public String getDescModule() {
		return descModule;
	}
	public void setDescModule(String descModule) {
		this.descModule = descModule;
	}
	public Langues(Long idLangue, String langue1, String langue2, String langue3, Integer module, String descModule) {
		super();
		this.idLangue = idLangue;
		this.langue1 = langue1;
		this.langue2 = langue2;
		this.langue3 = langue3;
		this.module = module;
		this.descModule = descModule;
	}
	public Langues() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
