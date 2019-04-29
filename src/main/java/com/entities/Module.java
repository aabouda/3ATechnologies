package com.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Module implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
	private Long idmodule;
	private String nommodule;
	
	@OneToMany(mappedBy="module",fetch=FetchType.LAZY)
	private Collection<User> user;
	@JsonIgnore
	@XmlTransient
	public Collection<User> getUser() {
	return user;
	}
	public Long getIdmodule() {
		return idmodule;
	}
	public void setIdmodule(Long idmodule) {
		this.idmodule = idmodule;
	}
	public String getNommodule() {
		return nommodule;
	}
	public void setNommodule(String nommodule) {
		this.nommodule = nommodule;
	}
	public void setUser(Collection<User> user) {
		this.user = user;
	}
	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Module(Long idmodule, String nommodule, Collection<User> user) {
		super();
		this.idmodule = idmodule;
		this.nommodule = nommodule;
		this.user = user;
	}
	
	
	
}
