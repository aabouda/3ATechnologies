package com.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.entities.Statdashboard;
import com.util.HibernateUtility;

@RestController
public class StatTransaction {

	@Autowired
	

	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	private Integer nb_enregistrement = 25;
	 static final String VIEW_INDEX = "pages/index";
	
	 
	 
	 @RequestMapping(value = "/api/activiteRecentes", method = RequestMethod.GET)
		public List activiteRecentes(  HttpServletRequest request) {
			Session session = sessionFactory.openSession();
			String sql = "SELECT resume_us, date_debut_estime_us , heures_estime_us, details_us FROM user_story where avancement_us < 100 ; " ;
			System.out.println(sql);
			return session.createSQLQuery(sql).list();

		}
	 
	 
	 @RequestMapping(value = "/api/alerteProjet", method = RequestMethod.GET)
		public List alerteProjet(  HttpServletRequest request) {
			Session session = sessionFactory.openSession();
			String sql = "SELECT nom_projet, count(*)  FROM alertes, projet   where id_projet = projectid group by id_projet; " ;
			System.out.println(sql);
			return session.createSQLQuery(sql).list();

		}
	 
	 
	 @RequestMapping(value = "/api/avancementProjet", method = RequestMethod.GET)
		public List avancementProjet( HttpServletRequest request) {
			Session session = sessionFactory.openSession();
			String sql = "SELECT nom_projet, avancement  FROM projet " ;
			System.out.println(sql);
			return session.createSQLQuery(sql).list();

		}
	 
	 
	 
	 
	 @RequestMapping(value = "/api/nbAlert")
		public Integer nbAlert(HttpServletRequest request) {
			Session session = sessionFactory.openSession();
			String sql = "SELECT count(*) FROM alertes where lu = 0;";
			Query sqlQuery = session.createSQLQuery( sql );
			Integer tOTAL_TRANSACTION = Integer.parseInt(sqlQuery.list().get(0).toString());
			session.close();
			return tOTAL_TRANSACTION;
		}	
	 
	
	@RequestMapping(value = "/api/nbProjet")
	public Integer nbProjet(HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT count(*) FROM projet;";
		Query sqlQuery = session.createSQLQuery( sql );
		Integer tOTAL_TRANSACTION = Integer.parseInt(sqlQuery.list().get(0).toString());
		session.close();
		return tOTAL_TRANSACTION;
	}	
	

	@RequestMapping(value = "/api/nbRessource")
	public String nbRessource(HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT TRUNCATE(sum(nb_ressource),2) FROM projet;";
		Query sqlQuery = session.createSQLQuery( sql );
		String tOTAL_TRANSACTION = sqlQuery.list().get(0).toString();
		session.close();
		return tOTAL_TRANSACTION;
	}	
	

	@RequestMapping(value = "/api/avgAvancement")
	public String avgAvancement(HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT TRUNCATE(avg(avancement),2) FROM projet;";
		Query sqlQuery = session.createSQLQuery( sql );
		String tOTAL_TRANSACTION =sqlQuery.list().get(0).toString();
		session.close();
		return tOTAL_TRANSACTION;
	}	
	

	@RequestMapping(value = "/api/budgetReelle")
	public String budgetReelle(HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT TRUNCATE(sum(budget_reelle),2) FROM projet;";
		Query sqlQuery = session.createSQLQuery( sql );
		String tOTAL_TRANSACTION = sqlQuery.list().get(0).toString();
		session.close();
		return tOTAL_TRANSACTION;
	}	
		
		
		

		
		
		
	
	
	
	
	

	
	
	
	
	
}