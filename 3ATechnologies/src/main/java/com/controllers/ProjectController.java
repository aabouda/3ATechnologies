package com.controllers;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ennumeration.EtatProjet;
import com.entities.Projet;
import com.entities.UserProjet;
import com.entities.UserProjetID;
import com.interfaces.IprojectService;

@RestController
@RequestMapping("projet")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ProjectController {

	@Autowired
	private IprojectService service;
	private String projectNameToAdd;

	@PostMapping("projet")
	public void CreateProject(@RequestBody Projet projet) throws ParseException {
		LocalDate now = java.time.LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateFin = LocalDate.parse(projet.getDateFin(), formatter);
		LocalDate dateDebut = LocalDate.parse(projet.getDateDebut(), formatter);
		if ((now.toString()).equals(projet.getDateDebut())|| ((dateDebut.isBefore(now) && dateFin.isAfter(now)))) {
			projet.setEtatProjet(EtatProjet.enCours);
		} else {
			projet.setEtatProjet(EtatProjet.àVenir);
		}
		this.projectNameToAdd = projet.getName();
		service.addProject(projet);
	}
	
	@PostMapping("addTeamMembers")
	public void addTeamMembers(@RequestBody List<UserProjet> Lup) throws ParseException {
		System.err.println(Lup.size());
		System.err.println(projectNameToAdd);
		service.addTeamMembers(projectNameToAdd, Lup);
	}

	@GetMapping("showAllProject")
	public ResponseEntity<List<Projet>> getAllProject() {
		List<Projet> list = service.getAllProject();
		return new ResponseEntity<List<Projet>>(list, HttpStatus.OK);
	}

	@GetMapping("getProjectById")
	public ResponseEntity<Projet> getProject(@RequestParam("id") String projectID) {
		Projet projet = service.findById(Long.valueOf(projectID));
		return new ResponseEntity<Projet>(projet, HttpStatus.OK);
	}

	@GetMapping("getProjectByName")
	public ResponseEntity<List<Projet>> getProjectByName(@RequestParam("name") String projectName) {
		List<Projet> list = service.getByName(projectName);
		return new ResponseEntity<List<Projet>>(list, HttpStatus.OK);
	}

	@GetMapping("getTeamMembersByProjet")
	public ResponseEntity<List<UserProjet>> getTeamMembersByProjet(@RequestParam("projectID") String projectID) {
		List<UserProjet> list = service.getTeamMembers(Long.valueOf(projectID));
		return new ResponseEntity<List<UserProjet>>(list, HttpStatus.OK);
	}

	@GetMapping("projet")
	public ResponseEntity<List<Projet>> filtreProject(@RequestParam("etatProjet") String etatProjet) {
		EtatProjet etat = null;
		if (etatProjet.equals("1")) {
			etat = EtatProjet.Clôturer;
		} else if (etatProjet.equals("2")) {
			etat = EtatProjet.enCours;
		} else if (etatProjet.equals("3")) {
			etat = EtatProjet.àVenir;
		} else {
			List<Projet> list = service.getAllProject();
			return new ResponseEntity<List<Projet>>(list, HttpStatus.OK);
		}
		List<Projet> list = service.getByState(etat);
		return new ResponseEntity<List<Projet>>(list, HttpStatus.OK);
	}

	@DeleteMapping("projet")
	public ResponseEntity<Void> closeProjet(@RequestParam("id") String id) {
		service.closeProjet(service.findById(Long.valueOf(id)));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
