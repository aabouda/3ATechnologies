package com.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserProjetID implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idProjetPK;
	private int idUserPK;

	public UserProjetID() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProjetID other = (UserProjetID) obj;
		if (idProjetPK != other.idProjetPK)
			return false;
		if (idUserPK != other.idUserPK)
			return false;
		return true;
	}

	public int getIdProjetPK() {
		return idProjetPK;
	}

	public int getIdUserPK() {
		return idUserPK;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idProjetPK;
		result = prime * result + idUserPK;
		return result;
	}

	public void setIdProjetPK(int idProjetPK) {
		this.idProjetPK = idProjetPK;
	}

	public void setIdUserPK(int idUserPK) {
		this.idUserPK = idUserPK;
	}

}
