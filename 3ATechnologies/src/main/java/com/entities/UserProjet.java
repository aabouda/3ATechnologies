package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ennumeration.Role;

@Entity
public class UserProjet implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private UserProjetID userProjetID;
//	@OneToMany(mappedBy="userProjet",cascade=CascadeType.PERSIST,fetch = FetchType.EAGER)
//	private List<Tache> listTaches;
	private Role role;
	@ManyToOne
	@JoinColumn(name = "idProjetPK", insertable = false, updatable = false)
	private Projet projet;
	@ManyToOne
	@JoinColumn(name = "idUserPK", insertable = false, updatable = false)
	private User user;

	public UserProjet() {
		super();
	}

//	public List<Tache> getListTaches() {
//		return listTaches;
//	}

	public Projet getProjet() {
		return projet;
	}

	public Role getRole() {
		return role;
	}

	public User getUser() {
		return user;
	}

	public UserProjetID getUserProjetID() {
		return userProjetID;
	}

//	public void setListTaches(List<Tache> listTaches) {
//		this.listTaches = listTaches;
//	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserProjetID(UserProjetID userProjetID) {
		this.userProjetID = userProjetID;
	}

}
