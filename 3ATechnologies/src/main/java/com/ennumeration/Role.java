package com.ennumeration;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Role {
	@JsonProperty("Scrum Master")
	ScrumMaster, 
	@JsonProperty("Product Owner")
	ProductOwner, 
	@JsonProperty("Team Member")
	TeamMember, 
	Directeur
}
