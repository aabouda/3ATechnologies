package com.controllers.bug;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.IBugEnvironnementRepository;
import com.dao.IBugEtatRepository;
import com.dao.IBugImpactRepository;
import com.dao.IBugRepository;
import com.dao.IBugVersionRepository;
import com.util.HibernateUtility;
@RestController
public class BugEtatController {

	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@Autowired
	private IBugRepository bugrepository;
	
	@Autowired
	private IBugEtatRepository bugEtatrepository;
	
	@Autowired
	private IBugEnvironnementRepository bugEnvironnementrepository;
	
	@Autowired
	private IBugVersionRepository bugVersionrepository;
	
	@Autowired
	private IBugImpactRepository bugImpactrepository;
	
	
	@RequestMapping(value = "/api/bugProjetId", method = RequestMethod.GET)
	public List getBugProjet(Integer projectID, Integer idCategorie,  HttpServletRequest request) {
		String req = "";
		if(idCategorie==1){
		req = "Select count(*) as qt, labelle "
				+ "from bug b, bug_etat e "
				+ "where id_bug_etat = e.id_Bug and  id_Projet = " + projectID 
				+ " group by id_bug_etat order by qt";
		}else if(idCategorie==2){
			req = "Select count(*), labelle "
					+ "from bug b, bug_impact e "
					+ "where id_bug_impact = e.id_Bug and  id_Projet = " + projectID 
					+ " group by id_bug_impact ";
		}else if(idCategorie==3){
		req = "Select count(*), labelle "
				+ "from bug b, bug_environnement e "
				+ "where id_bug_environnement = e.id_Bug and  id_Projet = " + projectID 
				+ " group by id_bug_environnement ";
		}else if(idCategorie==4){
		req = "Select count(*), labelle "
				+ "from bug b, bug_version e "
				+ "where id_bug_version = e.id_Bug and  id_Projet = " + projectID 
				+ " group by id_bug_version ";
		}else if(idCategorie==5){
			req = "Select count(*), e.labelle, v.labelle "
					+ "from bug b, bug_etat e, bug_environnement v "
					+ "where id_bug_etat = e.id_Bug and id_bug_environnement = v.id_Bug  and  id_Projet = " + projectID 
					+ " group by id_bug_etat, id_bug_environnement ";
		}else if(idCategorie==6){
				req = "Select count(*), e.labelle, v.labelle "
						+ "from bug b, bug_impact e, bug_environnement v "
						+ "where id_bug_impact = e.id_Bug and id_bug_environnement = v.id_Bug  and  id_Projet = " + projectID 
						+ " group by id_bug_impact, id_bug_environnement ";
		}else if(idCategorie==7){
			req = "Select count(*), e.labelle, v.labelle "
					+ "from bug b, bug_etat e, bug_version v "
					+ "where id_bug_etat = e.id_Bug and id_bug_version = v.id_Bug and  id_Projet = " + projectID 
					+ " group by id_bug_etat, id_bug_version ";
		}else if(idCategorie==8){
			req = "Select count(*), e.labelle, v.labelle "
					+ "from bug b, bug_impact e, bug_version v "
					+ "where id_bug_impact = e.id_Bug and id_bug_version = v.id_Bug  and  id_Projet = " + projectID 
					+ " group by id_bug_impact, id_bug_version ";
		}
		
		
		
		
		System.out.println(req);
		
		Session session = sessionFactory.openSession();
		return session.createSQLQuery(req).list();
		

	}
	
	
	
	
	
	
}
