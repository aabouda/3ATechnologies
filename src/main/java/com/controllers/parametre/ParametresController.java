package com.controllers.parametre;

import java.io.IOException;
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
import com.dao.IParametresRepository;
import com.entities.Alertes;
import com.entities.Parametres;
import com.util.HibernateUtility;




@RestController
public class ParametresController {

	@Autowired
	private IParametresRepository parametresrepository;
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@RequestMapping(value = "/api/saveParametre", method = RequestMethod.GET)
	public void saveParametre(String projectID,String timeExecutionBatsh, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update Parametres set time_execution_batsh = '"+ timeExecutionBatsh +"' where id_Projet = " + projectID ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		LogDB.insertLogDB("Parametres", "updateParametres: "+ hsql1, request);

	}
	
	@RequestMapping(value = "/api/parametreProjetId", method = RequestMethod.GET)
	public List<Parametres> getparametreProjet(Integer projectID,  HttpServletRequest request) {
		return parametresrepository.findProjetId(Long.valueOf(projectID));

	}
	
	@RequestMapping(value = "/api/executeCommande", method = RequestMethod.GET)
	public void executeCommande(Integer projectID,  HttpServletRequest request) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("curl", "-XDELETE","\"http://localhost:9200/test/?pretty=true\"");
	    pb.redirectErrorStream(true);
	    Process p = pb.start();
	    
	    ProcessBuilder pb2 = new ProcessBuilder("curl", "-XPUT","-H \"Content-Type: application/json\"","\"http://localhost:9200/_bulk\"", "--data-binary @c:\\log.json");
	    pb2.redirectErrorStream(true);
	    Process p2 = pb2.start();
	    
	   
	    
	}
	
}
