package com.controllers.alertes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.controllers.ia.SAD;
import com.dao.IRegleAlertesRepository;
import com.entities.RegleAlerte;
import com.util.HibernateUtility;
@RestController
public class RegleAlertesController {

	@Autowired
	private IRegleAlertesRepository alertesrepository;
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@RequestMapping(value = "/api/regleAllAlertesProjetId", method = RequestMethod.GET)
	public List<RegleAlerte> getRegleAllAlertesProjetId(Integer projectID,  HttpServletRequest request) {
		return alertesrepository.findAllAlerteProjetId(Long.valueOf(projectID));

	}
	
	@RequestMapping(value = "/api/updateRegleAlerteDesc", method = RequestMethod.GET)
	public void updateRegleAlerteDesc(String idRegleAlerte, String valRegleAlerte, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		String hsql1 = "update regle_alerte  set description = '" + valRegleAlerte + "'  Where id_regle_alerte = " + idRegleAlerte;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
	@RequestMapping(value = "/api/updateRegleAlerteMax", method = RequestMethod.GET)
	public void updateRegleAlerteMax(String idRegleAlerte, String valRegleAlerte, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		String hsql1 = "update regle_alerte  set max_val = '" + valRegleAlerte + "'  Where id_regle_alerte = " + idRegleAlerte;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
	@RequestMapping(value = "/api/updateRegleAlerteMin", method = RequestMethod.GET)
	public void updateRegleAlerteMin(String idRegleAlerte, String valRegleAlerte, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		String hsql1 = "update regle_alerte  set min_val = '" + valRegleAlerte + "'  Where id_regle_alerte = " + idRegleAlerte;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}

	
}
