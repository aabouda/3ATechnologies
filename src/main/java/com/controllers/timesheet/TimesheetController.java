package com.controllers.timesheet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.ISprintRepository;
import com.entities.Sprint;
import com.util.HibernateUtility;
@RestController
public class TimesheetController {

	@Autowired
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@RequestMapping(value = "/api/moisAnneeProjetId", method = RequestMethod.GET)
	public List getAlerteProjet(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT m.idmois_annee,  m.annee, m.mois " + 
				"FROM 3atechnologies.timesheet t,  3atechnologies.mois_annee m  " + 
				"where  t.idmoisanee = m.idmois_annee and t.idprojet =  " +  projectID + 
				" group by m.idmois_annee,  m.annee, m.mois " + 
				"order by m.annee DESC, m.mois  DESC ;";
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	

	
	@RequestMapping(value = "/api/listeUsersProjet")
	public List listeUsersProjet(Long projetId) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT t.idtimesheet, t.iduser, u.nom, u.lastname , t.nbjours, m.annee, m.mois " + 
				"FROM timesheet t,  mois_annee m , users u " + 
				"where t.iduser = u.userid and t.idmoisanee = m.idmois_annee and   t.idprojet =  " +  projetId + 
				" order by m.annee , m.mois, u.nom  ;";
		System.out.println(sql);
		return session
				.createSQLQuery(sql)
				.list();
	}
	
	
	@RequestMapping(value = "/api/userprojetIdMoisAnnee")
	public List listeUsersProjet(Long projetId, String idMois) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT t.idtimesheet, t.iduser, u.nom, u.lastname , t.nbjours, m.annee, m.mois, t.nbjoursP " + 
				"FROM timesheet t,  mois_annee m , users u " + 
				"where t.iduser = u.userid and t.idmoisanee = m.idmois_annee and   t.idprojet =  " +  projetId + " and  m.idmois_annee = " + idMois + 
				" order by m.annee, m.mois, u.nom  ;";
		System.out.println(sql);
		return session
				.createSQLQuery(sql)
				.list();
	}
	
	
	@RequestMapping(value = "/api/updateTimesheet", method = RequestMethod.GET)
	public void updateTimesheet(String idTimesheet, String nbJours, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		String hsql1 = "update timesheet  set nbjours = '" + nbJours + "'  Where idtimesheet = " + idTimesheet;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
	@RequestMapping(value = "/api/updateTimesheetP", method = RequestMethod.GET)
	public void updateTimesheetP(String idTimesheet, String nbJours, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		String hsql1 = "update timesheet  set nbjoursP = '" + nbJours + "'  Where idtimesheet = " + idTimesheet;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
	
}
