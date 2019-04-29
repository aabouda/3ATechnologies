package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roleProjet")
public class RoleProjet {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    private Long idRoleProjet;
	private String roleProjet;
	
	public Long getUserroleid() {
		return idRoleProjet;
	}
	public void setUserroleid(Long userroleid) {
		this.idRoleProjet = userroleid;
	}
	public String getRole() {
		return roleProjet;
	}
	public void setRole(String role) {
		this.roleProjet = role;
	}
	public RoleProjet(Long userroleid, String role) {
		super();
		this.idRoleProjet = userroleid;
		this.roleProjet = role;
	}
	public RoleProjet() {
		super();
	}
	 
	
	
	
}
