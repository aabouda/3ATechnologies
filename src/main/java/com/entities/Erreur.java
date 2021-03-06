package com.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "description"})
public class Erreur {
	private String name;

    private String description;

    public Erreur(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @JsonProperty("nom")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
