package com.entities;

import java.util.List;

public class UserDTO extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> roles;
	
	public UserDTO(User user){
		setUserId(user.getUserId());
		setUserName(user.getUserName());
		setEmail(user.getEmail());
		setEnabled(user.getEnabled());
		setLastname(user.getLastname());
		setModule(user.getModule());
		setNom(user.getNom());
		setPassword(user.getPassword());
		setPoste(user.getPoste());
		setTele(user.getTele());
		setUserrole(user.getUserrole());
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}
