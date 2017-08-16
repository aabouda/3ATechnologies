package com.startup;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.interfaces.IprojectService;
import com.interfaces.IuserService;

@Service
@Transactional
public class StartUpInit implements ApplicationRunner {

	private IprojectService serviceProjet;
	private IuserService serviceUser;

	@Autowired
	public StartUpInit(IprojectService serviceProjet, IuserService userService) {
		this.serviceProjet = serviceProjet;
		this.serviceUser = userService;
	}

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		serviceProjet.updateProjectState();
	}
}
