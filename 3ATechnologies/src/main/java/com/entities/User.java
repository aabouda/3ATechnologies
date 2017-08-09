package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userID;
	private String firstName;
	private String lastName;
	@NotNull
	@Column(unique = true)
	private String username;
	@NotNull
	private String password;
	@NotNull
	@Column(unique = true)
	private String email;
	private String picture;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<UserProjet> projets;

	public User() {
		super();
	}

	public User(long userID, String firstName, String lastName, String username, String password, String email,
			String picture) {
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.picture = picture;
	}

	public User(String firstName, String lastName, String username, String password, String email, String picture) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.picture = picture;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getPicture() {
		return picture;
	}

	public long getUserID() {
		return userID;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserProjet> getProjets() {
		return projets;
	}

	public void setProjets(List<UserProjet> projets) {
		this.projets = projets;
	}

}
