package com.controllers.alertes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.IAlertesRepository;

import com.entities.Alertes;
import com.util.HibernateUtility;
@RestController
public class AlertesController {

	@Autowired
	private IAlertesRepository alertesrepository;
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@RequestMapping(value = "/api/alertesProjetId", method = RequestMethod.GET)
	public List<Alertes> getAlerteProjet(Integer projectID,  HttpServletRequest request) {
		return alertesrepository.findProjetId(Long.valueOf(projectID));

	}
	
	@RequestMapping(value = "/api/alerteId", method = RequestMethod.GET)
	public List<Alertes> getAlerteId(Integer alerteID,  HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update alertes set lu = 1 where id_alerte =  " + alerteID;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		return alertesrepository.findAlerteId(Long.valueOf(alerteID));
	}
	
}
