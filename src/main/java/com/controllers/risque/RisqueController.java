package com.controllers.risque;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.IRisqueRepository;
import com.entities.Risque;
import com.util.HibernateUtility;
@RestController
public class RisqueController {

	@Autowired
	private IRisqueRepository risquerepository;
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@RequestMapping(value = "/api/risqueProjetId", method = RequestMethod.GET)
	public List<Risque> getAlerteProjet(Integer projectID,  HttpServletRequest request) {
		return risquerepository.findProjetId(Long.valueOf(projectID));

	}
	
	@RequestMapping(value = "/api/risqueId", method = RequestMethod.GET)
	public List<Risque> getrisqueId(Integer risqueID,  HttpServletRequest request) {
		return risquerepository.findRisqueId(Long.valueOf(risqueID));
	}
	
	
	
	@RequestMapping(value = "/api/Addrisque", method = RequestMethod.GET)
	public String addrisque(String projectID, 
			String risque, 
			String detailsRs, 
			String impact,
			String probabilite,
			String criticite, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		
		String hsql1 = "insert into risque (id_projet, risque, details_Rs, impact, probabilite, criticite) values "
										+ "( " + projectID + ",'" + risque + "','" 
										+ detailsRs + "','" + impact + "', '" + probabilite  + "','" + criticite +  "')";
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		Session session4 = sessionFactory.openSession();
		session4.getTransaction().begin();
		hsql1 = "select  Max(id_risque) as id from risque where risque = '" + risque  + "' and   id_projet =  " + projectID  ;
		System.out.println(hsql1);
		Query sqlQuery3 = session4.createSQLQuery(hsql1);
		String id = sqlQuery3.list().get(0).toString();
		session4.close();
		return id;
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
	
	
	@RequestMapping(value = "/api/Updaterisque", method = RequestMethod.GET)
	public void updaterisque(String idRisque, 
			String risque, 
			String detailsRs, 
			String impact,
			String probabilite,
			String criticite, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		
		
		String hsql1 = "update risque  set risque = '" + risque + "', "
										+ "details_Rs = '"+ detailsRs + "', "
										+ "impact = '" + impact + "', "
										+ "probabilite =  '" + probabilite  + "', "
										+ "criticite =  '" + criticite +  "'"
						+ " Where id_risque = " + idRisque;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
}
