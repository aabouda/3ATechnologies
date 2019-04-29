package com.entities;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    private Long idRole;
	private String roleName;
	
	public Long getUserroleid() {
		return idRole;
	}
	public void setUserroleid(Long userroleid) {
		this.idRole = userroleid;
	}
	public String getRole() {
		return roleName;
	}
	public void setRole(String role) {
		this.roleName = role;
	}
	public Role(Long userroleid, String role) {
		super();
		this.idRole = userroleid;
		this.roleName = role;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	
	
	
}
