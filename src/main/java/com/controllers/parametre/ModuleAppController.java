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
import com.dao.IModuleAppRepository;
import com.entities.Environnement;
import com.entities.ModuleApp;
import com.util.HibernateUtility;




@RestController
public class ModuleAppController {

	@Autowired
	private IModuleAppRepository ModuleApprepository;
	

	
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@RequestMapping(value = "/api/SaveModuleApp", method = RequestMethod.GET)
	public void saveModuleApp(String idProject, 
			String description, 
			String nom,String nb_Cas_Test,  HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		String hsql1 = "insert into Module_App (id_Projet, nom_Module_App, nb_Cas_Test, description) values "
										+ "( '" + idProject + "','" + nom + "','" + nb_Cas_Test + "','" +  description + "')";
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	
	
	@RequestMapping(value = "/api/updateModuleApp", method = RequestMethod.GET)
	public void updateModuleApp(String idmoduleApp,String name, String description,String nb_Cas_Test, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update Module_App set nom_Module_App = '" + name + "' , description = '" + description +  "',  nb_Cas_Test = '" + nb_Cas_Test +  
						"' where id_module_App = " + idmoduleApp ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		LogDB.insertLogDB("ModuleApp", "updateModuleApp: "+ hsql1, request);

	}

	@RequestMapping(value = "/api/ModuleApp", method = RequestMethod.GET)
	public List<ModuleApp> getModuleApp(  HttpServletRequest request) {
		return ModuleApprepository.findAll();
	}

	@RequestMapping(value = "/api/GetModuleAppId")
	public List<ModuleApp> getModuleAppId(Integer moduleAppID,  HttpServletRequest request) {
		//LogDB.insertLogDB("ModuleApp", "saveModuleApp: "+ projectID, request);
		return ModuleApprepository.findModuleApp(Long.valueOf(moduleAppID));
	}
	
	
	@RequestMapping(value = "/api/ModuleAppProjetId", method = RequestMethod.GET)
	public List<ModuleApp> getEnvironnementProjet(Integer projectID,  HttpServletRequest request) {
		return ModuleApprepository.findProjetId(Long.valueOf(projectID));

	}
	
	
	
	
}
