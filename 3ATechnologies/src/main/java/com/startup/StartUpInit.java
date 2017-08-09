package com.startup;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennumeration.EtatProjet;
import com.entities.Projet;
import com.interfaces.IprojectService;

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
		service.updateProjectState();
	}
}
