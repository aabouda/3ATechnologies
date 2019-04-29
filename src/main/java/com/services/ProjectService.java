package com.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ennumeration.EtatProjet;
import com.entities.Projet;
import com.entities.UserProjet;
import com.entities.UserProjetID;
import com.interfaces.IprojectService;

@Service
@Transactional
public class ProjectService implements IprojectService {
	
	@PersistenceContext
	private EntityManager entityManager;
    @Autowired
    public JavaMailSender emailSender; 

	
	
    @Override
	public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
    }



	@Override
	public void addProject(Projet projet) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void addTeamMembers(String projectName, List<UserProjet> members) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void UpdateTeamMembers(long projectID, List<UserProjet> members) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Projet> getAllProject() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void closeProjet(Projet projet) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Projet findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void updateProject(Projet projet) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void updateProjectState() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Projet> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Projet getProjectByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Projet> getByState(EtatProjet etatProjet) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Projet> getNextStartDate(long daysToAdd) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Projet> getNextEndDate(long daysToAdd) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Projet> getByInterval(int interval) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<UserProjet> getTeamMembers(long projectID) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<UserProjet> getTeamMembers(Projet projet) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean verifyExistance(String projectName) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public List<String> getAllProjectNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
