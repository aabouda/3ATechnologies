package com.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class UserProjetID implements Serializable {

	private static final long serialVersionUID = 1L;
	private long idProjetPK;
	private long idUserPK;

	public UserProjetID() {
		super();
	}

	
	public UserProjetID(long idProjetPK, long idUserPK) {
		super();
		this.idProjetPK = idProjetPK;
		this.idUserPK = idUserPK;
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

	public long getIdProjetPK() {
		return idProjetPK;
	}

	public long getIdUserPK() {
		return idUserPK;
	}

	@Override
	public int hashCode() {
		final long prime = 31;
		long result = 1;
		result = prime * result + idProjetPK;
		result = prime * result + idUserPK;
		return (int) result;
	}

	public void setIdProjetPK(long idProjetPK) {
		this.idProjetPK = idProjetPK;
	}

	public void setIdUserPK(long idUserPK) {
		this.idUserPK = idUserPK;
	}

}
