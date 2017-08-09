package com.interfaces;

import java.util.List;

import com.ennumeration.EtatProjet;
import com.entities.Projet;

public interface IprojectService {

	/**
	 * This Method allowed to add a new Project to the DataBase. It will compare
	 * sysDate() with the start date of the project to switch project state to
	 * "finished" or "" or ""
	 * 
	 * @param projet
	 */
	public void addProject(Projet projet);

	public List<Projet> getAllProject();

	public void closeProjet(Projet projet);

	public Projet findById(long id);

	public void updateProject(Projet projet);

	public void updateProjectState();

	public List<Projet> getByName(String name);

	public List<Projet> getByState(EtatProjet etatProjet);

	public List<Projet> getNextStartDate(long daysToAdd);

	public List<Projet> getNextEndDate(long daysToAdd);

	public List<Projet> getByInterval(int interval);

}