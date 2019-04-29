package com.controllers.parametre;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.controllers.LogDB;
import com.dao.IEnvironnementRepository;
import com.entities.Alertes;
import com.entities.Environnement;
import com.util.HibernateUtility;




@RestController
public class EnvironnementController {

	@Autowired
	private IEnvironnementRepository environnementrepository;
	

	
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@RequestMapping(value = "/api/SaveEnvironnement", method = RequestMethod.GET)
	public void saveEnvironnement(String idProject, 
			String description, 
			String nom,  HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		String hsql1 = "insert into Environnement (id_Projet, nom_Environnement, description) values "
										+ "( '" + idProject + "','" + nom + "','" +  description + "')";
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	
	
	@RequestMapping(value = "/api/updateEnvironnement", method = RequestMethod.GET)
	public void updateEnvironnement(String idEnvironnement,String name, String description, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update Environnement set nom_Environnement = '" + name + "' , description = '" + description +  "' where id_Environnement = " + idEnvironnement ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		LogDB.insertLogDB("Environnement", "updateEnvironnement: "+ hsql1, request);

	}

	@RequestMapping(value = "/api/Environnement", method = RequestMethod.GET)
	public List<Environnement> getEnvironnement(  HttpServletRequest request) {
		return environnementrepository.findAll();
	}

	@RequestMapping(value = "/api/GetEnvironnementId")
	public List<Environnement> getEnvironnementId(Integer environnementID,  HttpServletRequest request) {
		//LogDB.insertLogDB("Environnement", "saveEnvironnement: "+ projectID, request);
		return environnementrepository.findEnvironnement(Long.valueOf(environnementID));
	}
	
	
	@RequestMapping(value = "/api/environnementProjetId", method = RequestMethod.GET)
	public List<Environnement> getEnvironnementProjet(Integer projectID,  HttpServletRequest request) {
		return environnementrepository.findProjetId(Long.valueOf(projectID));

	}
	
	
	
	
}
