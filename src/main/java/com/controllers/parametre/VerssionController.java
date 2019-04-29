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
import com.dao.IVerssionRepository;
import com.entities.ModuleApp;
import com.entities.Verssion;
import com.util.HibernateUtility;




@RestController
public class VerssionController {

	@Autowired
	private IVerssionRepository Verssionrepository;
	

	
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@RequestMapping(value = "/api/SaveVerssion", method = RequestMethod.GET)
	public void saveVerssion(String idProject, 
			String description, 
			String nom,  HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		String hsql1 = "insert into Verssion (id_Projet, nom_Verssion, description) values "
										+ "( '" + idProject + "','" + nom + "','" +  description + "')";
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	
	
	@RequestMapping(value = "/api/updateVerssion", method = RequestMethod.GET)
	public void updateVerssion(String idverssion,String name, String description, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update Verssion set nom_Verssion = '" + name + "' , description = '" + description +  
						"' where id_Verssion = " + idverssion ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		LogDB.insertLogDB("Verssion", "updateVerssion: "+ hsql1, request);

	}

	@RequestMapping(value = "/api/Verssion", method = RequestMethod.GET)
	public List<Verssion> getVerssion(  HttpServletRequest request) {
		return Verssionrepository.findAll();
	}

	@RequestMapping(value = "/api/GetVerssionId")
	public List<Verssion> getVerssionId(Integer verssionID,  HttpServletRequest request) {
		//LogDB.insertLogDB("Verssion", "saveVerssion: "+ projectID, request);
		return Verssionrepository.findVerssion(Long.valueOf(verssionID));
	}
	

	@RequestMapping(value = "/api/VerssionProjetId", method = RequestMethod.GET)
	public List<Verssion> getEnvironnementProjet(Integer projectID,  HttpServletRequest request) {
		return Verssionrepository.findProjetId(Long.valueOf(projectID));

	}
	
}
