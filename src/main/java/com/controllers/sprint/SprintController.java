package com.controllers.sprint;

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
public class SprintController {

	@Autowired
	private ISprintRepository sprintrepository;
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@RequestMapping(value = "/api/sprintProjetId", method = RequestMethod.GET)
	public List<Sprint> getAlerteProjet(Integer projectID,  HttpServletRequest request) {
		return sprintrepository.findProjetId(Long.valueOf(projectID));

	}
	
	@RequestMapping(value = "/api/sprintId", method = RequestMethod.GET)
	public List<Sprint> getSprintId(Integer sprintID,  HttpServletRequest request) {
		return sprintrepository.findSprintId(Long.valueOf(sprintID));
	}
	
	
	
	@RequestMapping(value = "/api/AddSprint", method = RequestMethod.GET)
	public String addSprint(String projectID, 
			String commentairesSpNv, 
			String detailsSpNv, 
			String dateFinEstimeSpNv, 
			String dateDebutEstimeSpNv,
			String resumeSpNv, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String[] dateDebutIn = dateDebutEstimeSpNv.split("/");
		if(dateDebutIn.length<2){
			dateDebutIn = dateDebutEstimeSpNv.split("-");
		}
		dateDebutEstimeSpNv = dateDebutIn[2]+"/"+ dateDebutIn[1]+"/"+dateDebutIn[0];
		

		String[] dateFinIn = dateFinEstimeSpNv.split("/");
		if(dateFinIn.length<2){
			dateFinIn = dateFinEstimeSpNv.split("-");
		}
		dateFinEstimeSpNv = dateFinIn[2]+"/"+ dateFinIn[1]+"/"+dateFinIn[0];
		
		
		String hsql1 = "insert into sprint (id_projet, resume_sp, details_sp, commentaires_sp, date_debut_estime_sp, date_fin_estime_sp, avancement_sp) values "
										+ "( " + projectID + ",'" + resumeSpNv + "','" 
										+ detailsSpNv + "','" + commentairesSpNv + "', '" + dateDebutEstimeSpNv  + "','" + dateFinEstimeSpNv +  "',0)";
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		Session session4 = sessionFactory.openSession();
		session4.getTransaction().begin();
		hsql1 = "select  Max(id_sp) as id from sprint where resume_sp = '" + resumeSpNv  + "' and   id_projet =  " + projectID  ;
		System.out.println(hsql1);
		Query sqlQuery3 = session4.createSQLQuery(hsql1);
		String id = sqlQuery3.list().get(0).toString();
		session4.close();
		return id;
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
	
	
	@RequestMapping(value = "/api/UpdateSprint", method = RequestMethod.GET)
	public void updateSprint(String idSp, 
			String commentairesSp, 
			String detailsSp, 
			String dateFinEstimeSp, 
			String dateDebutEstimeSp,
			String resumeSp, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String[] dateDebutIn = dateDebutEstimeSp.split("/");
		if(dateDebutIn.length<2){
			dateDebutIn = dateDebutEstimeSp.split("-");
		}
		dateDebutEstimeSp = dateDebutIn[2]+"/"+ dateDebutIn[1]+"/"+dateDebutIn[0];

		String[] dateFinIn = dateFinEstimeSp.split("/");
		if(dateFinIn.length<2){
			dateFinIn = dateFinEstimeSp.split("-");
		}
		dateFinEstimeSp = dateFinIn[2]+"/"+ dateFinIn[1]+"/"+dateFinIn[0];
		
		
		
		String hsql1 = "update sprint  set resume_sp = '" + resumeSp + "', "
										+ "details_sp = '"+ detailsSp + "', "
										+ "commentaires_sp = '" + commentairesSp + "', "
										+ "date_debut_estime_sp =  '" + dateDebutEstimeSp  + "', "
										+ "date_fin_estime_sp =  '" + dateFinEstimeSp +  "'"
						+ " Where id_sp = " + idSp;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
}
