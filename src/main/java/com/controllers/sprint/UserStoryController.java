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

import com.dao.IUserStoryRepository;
import com.entities.Sprint;
import com.entities.UserStory;
import com.util.HibernateUtility;
@RestController
public class UserStoryController {

	@Autowired
	private IUserStoryRepository userStoryrepository;
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@RequestMapping(value = "/api/userStorySprintId", method = RequestMethod.GET)
	public List<UserStory> getUSSprint(Integer sprintID,  HttpServletRequest request) {
		return userStoryrepository.findSprintId(Long.valueOf(sprintID));
	}
	
	@RequestMapping(value = "/api/userStoryId", method = RequestMethod.GET)
	public List<UserStory> getUserStoryId(Integer userStoryID,  HttpServletRequest request) {
		return userStoryrepository.findUserStoryId(Long.valueOf(userStoryID));
	}
	
	@RequestMapping(value = "/api/AddUS", method = RequestMethod.GET)
	public void addUS(String idSp, 
			String dateDebutUS,
			String dateFinUS,
			String nbHuere,
			String statut,
			String avancementUS,
			String module,
			String typeUS,
			String priorite,
			String complexite, 
			String dateDebutEstUS,
			String dateFinEstUS,
			String detailsUS,
			String force_us,
			String nbHuereEst,
			String resumeUS, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		String[] dateDebutIn = dateDebutUS.split("/");
		if(dateDebutIn.length<2){
			dateDebutIn = dateDebutUS.split("-");
		}
		dateDebutUS = dateDebutIn[2]+"/"+ dateDebutIn[1]+"/"+dateDebutIn[0];
		

		String[] dateFinIn = dateFinUS.split("/");
		if(dateFinIn.length<2){
			dateFinIn = dateFinUS.split("-");
		}
		dateFinUS = dateFinIn[2]+"/"+ dateFinIn[1]+"/"+dateFinIn[0];
		
		
		String[] dateFinInEst = dateFinEstUS.split("/");
		if(dateFinInEst.length<2){
			dateFinInEst = dateFinEstUS.split("-");
		}
		dateFinEstUS = dateFinInEst[2]+"/"+ dateFinInEst[1]+"/"+dateFinInEst[0];
		
		
		
		String[] dateDebutEstIn = dateDebutEstUS.split("/");
		if(dateDebutEstIn.length<2){
			dateDebutEstIn = dateDebutEstUS.split("-");
		}
		dateDebutEstUS = dateDebutEstIn[2]+"/"+ dateDebutEstIn[1]+"/"+dateDebutEstIn[0];
		
		
		
		String hsql1 = "insert into user_story (commentaires_us, date_debut_estime_us, date_debut_reels_us, date_fin_estime_us, date_fin_reels_us, details_us,"
											+ "force_us,heures_estime_us,heures_reels_us,id_sprint,module_us,priorite_us,resume_us,statut_us,type_us,avancement_us, id_User ) values "
										+ "( '','" + dateDebutEstUS + "','" + dateDebutUS + "','" + dateFinEstUS + "', '" + dateFinUS  + "','" + detailsUS 
										+  "','" + force_us  + "','" + nbHuereEst + "','" + nbHuere + "', '" + idSp  + "','" + module + "','" + priorite + "','" + resumeUS + "', '" 
										+ statut  + "', '" + typeUS +"', '" + avancementUS +"', 0 )";
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}

	
	
	
	@RequestMapping(value = "/api/affecterUSMb", method = RequestMethod.GET)
	public void updateSprint(String idUserStory, 
			String idUser, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		String hsql1 = "update user_story  set id_User = '" + idUser + "'  Where id_user_story = " + idUserStory;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
	
	
}
