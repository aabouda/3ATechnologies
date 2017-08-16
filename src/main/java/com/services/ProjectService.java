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
	public void addProject(Projet projet) {
		entityManager.persist(entityManager.merge(projet));
		entityManager.flush();
	}

	@Override
	public List<Projet> getAllProject() {
		TypedQuery<Projet> q = entityManager.createQuery("SELECT p FROM Projet p", Projet.class);
		return q.getResultList();
	}

	@Override
	public void closeProjet(Projet projet) {
		projet.setEtatProjet(EtatProjet.Clôturer);

	}

	@Override
	public Projet findById(long id) {
		return entityManager.find(Projet.class, id);
	}

	@Override
	public void updateProject(Projet projet) {
		entityManager.merge(projet);

	}

	@Override
	public List<Projet> getByName(String name) {
		TypedQuery<Projet> q = entityManager.createQuery("SELECT p FROM Projet p where p.name LIKE :name",
				Projet.class);
		q.setParameter("name", '%' + name + '%');
		return q.getResultList();
	}

	@Override
	public Projet getProjectByName(String name) {
		try {
			TypedQuery<Projet> q = entityManager.createQuery("SELECT p FROM Projet p where p.name =:name",
					Projet.class);
			q.setParameter("name", name);
			return q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Projet> getByState(EtatProjet etatProjet) {
		TypedQuery<Projet> q = entityManager.createQuery("SELECT p FROM Projet p where p.etatProjet =:etatProjet",
				Projet.class);
		q.setParameter("etatProjet", etatProjet);
		return q.getResultList();
	}

	@Override
	public List<Projet> getNextStartDate(long daysToAdd) {
		LocalDate lookedDate = (java.time.LocalDate.now()).plusDays(daysToAdd);
		TypedQuery<Projet> q = entityManager.createQuery("SELECT p FROM Projet p where p.dateDebut =:lookedDate",
				Projet.class);
		q.setParameter("lookedDate", lookedDate.toString());
		return q.getResultList();
	}

	@Override
	public List<Projet> getNextEndDate(long daysToAdd) {
		LocalDate lookedDate = (java.time.LocalDate.now()).plusDays(daysToAdd);
		TypedQuery<Projet> q = entityManager.createQuery("SELECT p FROM Projet p where p.dateFin =:lookedDate",
				Projet.class);
		q.setParameter("lookedDate", lookedDate.toString());
		return q.getResultList();
	}

	@Override
	public List<Projet> getByInterval(int interval) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProjectState() {
		List<Projet> Lp = this.getAllProject();
		LocalDate now = java.time.LocalDate.now();
		for (Projet projet : Lp) {
			if (projet.getEtatProjet().equals(EtatProjet.Clôturer)) {
			} else {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate dateFin = LocalDate.parse(projet.getDateFin(), formatter);
				LocalDate dateDebut = LocalDate.parse(projet.getDateDebut(), formatter);
				if ((now.toString()).equals(projet.getDateDebut())
						|| ((dateDebut.isBefore(now) && dateFin.isAfter(now)))) {
					projet.setEtatProjet(EtatProjet.enCours);
				} else if (dateFin.isBefore(now)) {
					projet.setEtatProjet(EtatProjet.Clôturer);
				} else if (dateDebut.isAfter(now)) {
					projet.setEtatProjet(EtatProjet.àVenir);
				}

			}

			this.updateProject(projet);
		}

	}

	@Override
	public List<UserProjet> getTeamMembers(long projectID) {
		TypedQuery<Object> q = entityManager
				.createQuery("SELECT p.teamMembers FROM Projet p where p.projectID  =:projectID", Object.class);
		q.setParameter("projectID", projectID);
		List<Object> results = q.getResultList();
		List<UserProjet> teamMembers = new ArrayList<>();
		for (Object result : results) {
			teamMembers.add((UserProjet) result);
		}
		return teamMembers;
	}

	@Override
	public List<UserProjet> getTeamMembers(Projet projet) {
		TypedQuery<Object> q = entityManager
				.createQuery("SELECT p.teamMembers FROM Projet p where p.projectID  =:projectID", Object.class);
		q.setParameter("projectID", projet.getProjectID());
		List<Object> results = q.getResultList();
		List<UserProjet> teamMembers = new ArrayList<>();
		for (Object result : results) {
			teamMembers.add((UserProjet) result);
		}
		return teamMembers;
	}

	@Override
	public void addTeamMembers(String projectName, List<UserProjet> members) {
		Projet x = getProjectByName(projectName);
		List<UserProjet> Lup = new ArrayList<>();
		for (UserProjet userProjet : members) {
			UserProjet abba = new UserProjet();
			abba.setProjet(x);
			abba.setUser(userProjet.getUser());
			abba.setRole(userProjet.getRole());
			abba.setUserProjetID(new UserProjetID(x.getProjectID(), userProjet.getUser().getUserID()));
			Lup.add(abba);
		}
		x.setTeamMembers(Lup);
		for (UserProjet userProjet : Lup) {
			entityManager.persist(userProjet);
		}

		updateProject(x);

	}

	@Override
	public void UpdateTeamMembers(long projectID, List<UserProjet> members) {
		Projet x = findById(projectID);
		
		List<UserProjet> Lup = getTeamMembers(projectID);
		for (UserProjet userProjet : Lup) {
			entityManager.remove(userProjet);
		}
		updateProject(x);
		Lup.clear();		
		for (UserProjet userProjet : members) {
		UserProjet abba = new UserProjet();
		abba.setProjet(x);
		abba.setUser(userProjet.getUser());
		abba.setRole(userProjet.getRole());
		abba.setUserProjetID(new UserProjetID(x.getProjectID(), userProjet.getUser().getUserID()));
		Lup.add(abba);
	}
		x.setTeamMembers(Lup);
		for (UserProjet userProjet : Lup) {
			entityManager.persist(userProjet);
		}

		updateProject(x);


	}

	@Override
	public boolean verifyExistance(String projectName) {
		return (getProjectByName(projectName) != null);
	}

	@Override
	public List<String> getAllProjectNames() {
		List<String> Names = new ArrayList<>();
		for (Projet projet : getAllProject()) {
			Names.add(projet.getName());
		}
		return Names;
	}
	
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
    }

}