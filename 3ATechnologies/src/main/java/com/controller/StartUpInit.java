package com.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennumeration.EtatProjet;
import com.entity.Projet;
import com.service.IprojectService;

@Service
@Transactional
public class StartUpInit implements ApplicationRunner {

	private IprojectService service;

	@Autowired
	public StartUpInit(IprojectService service) {
		this.service = service;
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		List<Projet> Lp = service.getAllProject();
		LocalDate now = java.time.LocalDate.now();
		for (Projet projet : Lp) {
			if (projet.getEtatProjet().equals(EtatProjet.Clôturer)) {

			} else {
				if (((now.toString()).equals(projet.getDateDebut()))
						|| (projet.getEtatProjet().equals(EtatProjet.enCours))) {
					projet.setEtatProjet(EtatProjet.enCours);
				} else if ((now.toString()).equals(projet.getDateFin())) {
					projet.setEtatProjet(EtatProjet.Clôturer);
				} else {
					projet.setEtatProjet(EtatProjet.àVenir);
				}
			}
			service.updateProject(projet);
		}

		
		
	}
}
