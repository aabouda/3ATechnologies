package com.controllers.societe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.ISocieteRepository;
import com.entities.Societe;
import com.util.HibernateUtility;
@RestController
public class SocieteController {

	@Autowired
	private ISocieteRepository Societerepository;
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@RequestMapping(value = "/api/societe", method = RequestMethod.GET)
	public List<Societe> getAlerteProjet(Integer projectID,  HttpServletRequest request) {
		return Societerepository.findAll();

	}
	
	@RequestMapping(value = "/api/societeId", method = RequestMethod.GET)
	public List<Societe> getSocieteId(Integer SocieteID,  HttpServletRequest request) {
		return Societerepository.findSocieteId(Long.valueOf(SocieteID));
	}
	
	
	
	@RequestMapping(value = "/api/AddSociete", method = RequestMethod.GET)
	public String addSociete(String nom,
						 String description,
						 String nombre_salarie,
						 String nombre_abonnement_t1,
						 String nombre_abonnement_t2,
						 String nombre_abonnement_t3,
						 String date_debut,
						 String date_fin,
						 String adresse,
						 String code_postal,
						 String paye,
						 String code_tva,
						 String active, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		
		 
		
		
		
		String hsql1 = "insert into Societe (nom, description, nombre_salarie, nombre_abonnement_t1, nombre_abonnement_t2, nombre_abonnement_t3, "
										+ "date_debut, date_fin, adresse, code_postal, paye, code_tva, active) values "
										+ "( " + nom + ",'" + description + "','" 
										+ nombre_salarie + "','" + nombre_abonnement_t1 + "', '" + nombre_abonnement_t2  + "','" + nombre_abonnement_t3 +  "','"
										+ date_debut + "','" + date_fin + "', '" + adresse  + "','" + code_postal +  "','"	+ paye + "', '" + code_tva  + "','" + active +  "')";
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		Session session4 = sessionFactory.openSession();
		session4.getTransaction().begin();
		hsql1 = "select  Max(id_societe) as id from Societe where nom = '" + nom  + "' "   ;
		System.out.println(hsql1);
		Query sqlQuery3 = session4.createSQLQuery(hsql1);
		String id = sqlQuery3.list().get(0).toString();
		session4.close();
		return id;
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
	
	
	@RequestMapping(value = "/api/UpdateSociete", method = RequestMethod.GET)
	public void updateSociete(String id_societe, 
			String nom,
			 String description,
			 String nombre_salarie,
			 String nombre_abonnement_t1,
			 String nombre_abonnement_t2,
			 String nombre_abonnement_t3,
			 String date_debut,
			 String date_fin,
			 String adresse,
			 String code_postal,
			 String paye,
			 String code_tva,
			 String active, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		String hsql1 = "update Societe  set nom = '" + nom + "', "
										+ "description = '"+ description + "', "
										+ "nombre_salarie = '" + nombre_salarie + "', "
										+ "nombre_abonnement_t1 =  '" + nombre_abonnement_t1  + "', "
										+ "nombre_abonnement_t2 =  '" + nombre_abonnement_t2 +  "'"
										+ "nombre_abonnement_t3 =  '" + nombre_abonnement_t3 +  "'"
										+ "date_debut =  '" + date_debut +  "'"
										+ "date_fin =  '" + date_fin +  "'"
										+ "adresse =  '" + adresse +  "'"
										+ "code_postal =  '" + code_postal +  "'"
										+ "paye =  '" + paye +  "'"
										+ "code_tva =  '" + code_tva +  "'"
										+ "active =  '" + active +  "'"
						+ " Where id_societe = " + id_societe;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
}
