package com.controllers.risque;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.IDeclarationRisqueRepository;
import com.entities.DeclarationRisque;
import com.util.HibernateUtility;
@RestController
public class DeclarationRisqueController {

	@Autowired
	private IDeclarationRisqueRepository declarationRisquerepository;
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	
	
	@RequestMapping(value = "/api/DeclarationRisqueId", method = RequestMethod.GET)
	public List<DeclarationRisque> getUserStoryId(Integer risqueID,  HttpServletRequest request) {
		return declarationRisquerepository.findRisqueId(Long.valueOf(risqueID));
	}
	
	
	
	@RequestMapping(value = "/api/AddDeclarationRisque", method = RequestMethod.GET)
	public void addDeclarationRisque(String idRisque, 
			String resume,
			String commentaires, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date date = new Date();
		String dateDebutRisque = formater.format(date);
		String hsql1 = "insert into declaration_risque (id_risque, date_debut_risque, resume, statut, commentaires) values "
										+ "( '" + idRisque + "','" + dateDebutRisque + "','"  + resume  + "', 0 ,'" + commentaires  + "' )";
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
	
	
	@RequestMapping(value = "/api/cloturerDeclarationRisque", method = RequestMethod.GET)
	public void cloturerDeclarationRisque(String idDeclarationRisque, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date date = new Date();
		String date_fin_risque = formater.format(date);
		
		
		String hsql1 = "update declaration_risque set statut = 3, date_fin_risque = '"+date_fin_risque+"'  where  id_declaration_risque = " + idDeclarationRisque ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
	
	@RequestMapping(value = "/api/ouvrirDeclarationRisque", method = RequestMethod.GET)
	public void ouvrirDeclarationRisque(String idDeclarationRisque, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date date = new Date();
		String date_fin_risque = formater.format(date);
		
		
		String hsql1 = "update declaration_risque set statut = 0, date_fin_risque = NULL  where  id_declaration_risque = " + idDeclarationRisque + " ;";
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}

	
	
	
}
