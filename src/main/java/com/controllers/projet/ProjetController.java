package com.controllers.projet;

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
import com.dao.IProjetRepository;
import com.entities.Projet;
import com.util.HibernateUtility;




@RestController
public class ProjetController {

	@Autowired
	private IProjetRepository projetrepository;
	

	
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	@RequestMapping(value = "/api/SaveProjet", method = RequestMethod.GET)
	public String saveProjet(String nameProject, 
			String description, 
			String dateDebut, 
			String dateFin, 
			String budget,
			String nbRessource,  
			String phase, HttpServletRequest request) {
		
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String[] dateDebutIn = dateDebut.split("/");
		if(dateDebutIn.length<2){
			dateDebutIn = dateDebut.split("-");
		}
		dateDebut = dateDebutIn[2]+"/"+ dateDebutIn[1]+"/"+dateDebutIn[0];

		String[] dateFinIn = dateFin.split("/");
		if(dateDebutIn.length<2){
			dateFinIn = dateFin.split("-");
		}
		dateFin = dateFinIn[2]+"/"+ dateFinIn[1]+"/"+dateFinIn[0];
		
		String hsql1 = "insert into projet (budget_prevu, budget_reelle, "
				+ "date_debut_prevu, date_debut_reelle, date_fin_prevu, date_fin_reelle, "
				+ "description, etat_projet, nb_ressource, nom_projet, phase, avancement) values "
										+ "( '" + budget + "','" + budget + "','" 
										+ dateDebut + "','" + dateDebut + "', '" + dateFin + "','" + dateFin + "', '"
										+ description  + "', 0 , '" + nbRessource + "', '"+ nameProject + "','" + phase + "',0)";
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		Session session4 = sessionFactory.openSession();
		session4.getTransaction().begin();
		hsql1 = "select  Max(projectid) as id from projet where  nom_projet =  '" + nameProject  + "'";
		System.out.println(hsql1);
		Query sqlQuery3 = session4.createSQLQuery(hsql1);
		String id = sqlQuery3.list().get(0).toString();
		session4.close();
		
		
		Session session5 = sessionFactory.openSession();
		session5.getTransaction().begin();
		hsql1 = "INSERT INTO regle_alerte (description,couleur,min_val,max_val,valeur,idProjet) VALUES "
				+ "('Taux de retard',1,0.01,0.20,56000.00,"+id+"),('Taux de retard',2,0.21,0.40,56000.00,"+id+"),"
				+ "('Taux de retard',3,0.41,999.00,56000.00,"+id+"),('Nb bugs bloquant',3,4.00,999.00,5.00,"+id+"),"
				+ "('Nb de bug en arbitrage',3,5.00,999.00,0.00,"+id+"),('Nb Total bug non pertinent',2,10.00,999.00,0.00,"+id+"),"
				+ "('Temps moyen de traitement de bug',1,15.00,999.00,0.00,"+id+"),('Temps moyen de traitement de bug',2,72.00,999.00,0.00,"+id+"),"
				+ "('Temps moyen de traitement de bug',3,24.00,999.00,0.00,"+id+"),('Densite defaut',3,0.40,999.00,0.00,"+id+"),"
				+ "('Cout non planifies',3,5.00,999.00,14730.00,"+id+"),('Cout de retard',3,5.00,999.00,14730.00,"+id+"),"
				+ "('Depassement cout/delai',3,0.00,999.00,324.00,"+id+"),('Emploi de ressources',1,0.00,0.50,177.16,"+id+"),"
				+ "('Emploi de ressources',2,0.51,0.80,177.16,"+id+"),('Emploi de ressources',3,0.81,999.00,177.16,"+id+"),"
				+ "('Disponibilite des ressources',2,0.70,999.00,177.16,"+id+"),('Capacite',2,0.00,0.80,177.16,"+id+"),"
				+ "('Ecart cout /dure/perimetre',2,-1000.00,0.00,6222.22,"+id+"),('Niveau de risque',2,1.00,4.00,12.20,"+id+"),"
				+ "('Niveau de risque',2,5.00,8.00,12.20,"+id+"),('Niveau de risque',3,9.00,12.00,12.20,"+id+"),"
				+ "('Niveau de risque',3,13.00,16.00,12.20,"+id+"),('Niveau de risque',3,17.00,999.00,12.20,"+id+"),"
				+ "('% risque realise',2,0.30,999.00,3.00,"+id+");";
		session5.createSQLQuery(hsql1).executeUpdate();
		session5.getTransaction().commit();
		session5.close();
		
		return id;
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);

	}
	
	
	
	@RequestMapping(value = "/api/updateProjet", method = RequestMethod.POST)
	public void updateProjet(String projectID,String name, String description, String dateDebut, String dateFin, String budget,  HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update projet set name = '" + name + "' , description = '" + description +  
						"', dateDebut = '" + dateDebut + "', dateFin = '" + dateFin + "', budget = '" + budget + 
						"' where projectID = " + projectID ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		LogDB.insertLogDB("Projet", "updateProjet: "+ hsql1, request);

	}
	
	
	
	
	@RequestMapping(value = "/api/cloturerProjet", method = RequestMethod.GET)
	public void cloturerProjet(String projectID, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update projet set etat_projet = 2  where projectID = " + projectID ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		LogDB.insertLogDB("Projet", "updateProjet: "+ hsql1, request);

	}
	
	
	

	@RequestMapping(value = "/api/ouvrirProjet", method = RequestMethod.GET)
	public void ouvrirProjet(String projectID, HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update projet set etat_projet = 1  where projectID = " + projectID ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		LogDB.insertLogDB("Projet", "updateProjet: "+ hsql1, request);

	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/api/updateAllProjet", method = RequestMethod.GET)
	public void updateAllProjet(String projectID,String nameProject, String dateDebutPrevu, String dateDebutReelle, String dateFinPrevu, String dateFinReelle,
			String budgetPrevu, String budgetReelle, String nbRessourcePrevu, String nbRessourceReelle, String phase,
			String avancement, String description, String etatProjet,  HttpServletRequest request) {
		
		String[] dateDebutIn = dateDebutPrevu.split("/");
		if(dateDebutIn.length<2){
			dateDebutIn = dateDebutPrevu.split("-");
		}
		dateDebutPrevu = dateDebutIn[2]+"/"+ dateDebutIn[1]+"/"+dateDebutIn[0];
		
		
		
		String[] dateFinOut = dateDebutReelle.split("/");
		if(dateFinOut.length<2){
			dateFinOut = dateDebutReelle.split("-");
		}
		dateDebutReelle = dateFinOut[2]+"/"+ dateFinOut[1]+"/"+dateFinOut[0];
		
		
		String[] dateFinPrIn = dateFinPrevu.split("/");
		if(dateFinPrIn.length<2){
			dateFinPrIn = dateFinPrevu.split("-");
		}
		dateFinPrevu = dateFinPrIn[2]+"/"+ dateFinPrIn[1]+"/"+dateFinPrIn[0];
		
		
		String[] dateFinReOut = dateFinReelle.split("/");
		if(dateFinReOut.length<2){
			dateFinReOut = dateFinReelle.split("-");
		}
		dateFinReelle = dateFinReOut[2]+"/"+ dateFinReOut[1]+"/"+dateFinReOut[0];
		
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update projet set nom_projet = '" + nameProject + "' , date_debut_prevu = '" + dateDebutPrevu +  
						"', date_debut_reelle = '" + dateDebutReelle + "', date_fin_prevu = '" + dateFinPrevu + "', date_fin_reelle = '" + dateFinReelle + 
						"', budget_prevu = '" + budgetPrevu + "', budget_reelle = '" + budgetReelle + "', nb_ressource = '" + nbRessourcePrevu + 
						"', nb_ressource_reelle = '" + nbRessourceReelle + "', phase = '" + phase + "', avancement = '" + avancement + 
						"', description = '" + description + "', etat_projet = '" + etatProjet + "', avancement = '" + avancement + 
						"' where projectid = " + projectID ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		//LogDB.insertLogDB("Projet", "updateProjet: "+ hsql1, request);

	}
	
	
	
	public void updateStatProjetId(Integer projectID) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update projet set cal_message = (SELECT count(*) FROM alertes where id_projet = "+projectID+")" + 
					    ", cal_sprint = (SELECT count(*) FROM sprint where id_projet = "+projectID+")" + 
						", cal_module_app = (SELECT count(*) FROM module_app where id_projet = "+projectID+")" + 
						", cal_environnement = (SELECT count(*) FROM environnement where id_projet = "+projectID+")" + 
						", cal_verssion = (SELECT count(*) FROM verssion where id_projet = "+projectID+")" + 
						", cal_bug = (SELECT toatl FROM executoin where id_projet = " + projectID + " Order by date_execution desc limit 0,1 )" + 
						", cal_release = (SELECT count(*) FROM releases where id_projet = "+projectID+")" + 
						" where projectid = " + projectID ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
	

	@RequestMapping(value = "/api/Projet", method = RequestMethod.GET)
	public List<Projet> getProjet(  HttpServletRequest request) {
		return projetrepository.findAll();
	}

	@RequestMapping(value = "/api/GetProjetId")
	public List<Projet> getProjetId(Integer projectID,  HttpServletRequest request) {
		updateStatProjetId(projectID);
		//LogDB.insertLogDB("Projet", "saveProjet: "+ projectID, request);
		return projetrepository.findProjet(Long.valueOf(projectID));
	}
	
	
	/*	
	@RequestMapping(value = "/api/listeProjet")
	public List<Projet> getlisteProjet(  HttpServletRequest request) throws ParseException {

	 
		Session session = sessionFactory.openSession();
		String sql = "select * from  Projet";

		Query sqlQuery = session.createSQLQuery(sql);
		List<Object[]> Projets = sqlQuery.list();
		Projet ProjetR = new Projet();
		List<Projet> ProjetAll = new ArrayList<Projet>();
		for (Object[] Projet : Projets) {
			ProjetAll.add(ProjetR.affectProjet(Projet));
		}
		session.close();
		LogDB.insertLogDB("Projet", "listeProjet: "+ sql, request);
		return (ProjetAll);

	}*/
	
	
	
	
	@RequestMapping(value = "/api/supprimerMembre", method = RequestMethod.GET)
	public void supprimerMembre(String idProjet, 
			String membre,HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "delete from user_projet where id_projetpk = " + idProjet + " and  id_userpk = " + membre;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
	
	
	
	
	@RequestMapping(value = "/api/updateTJM", method = RequestMethod.GET)
	public void updateTJM(String idProjet, 
			String membre, String tjm, HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update user_projet set tjm = " + tjm + " where id_projetpk = " + idProjet + " and  id_userpk = " + membre;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);
	}
		
		
		
		@RequestMapping(value = "/api/affectMembre", method = RequestMethod.GET)
		public void affectMembre(String idProjet, 
				String membre, String role,HttpServletRequest request) {
			Session session = sessionFactory.openSession();
			session.getTransaction().begin();
			String hsql1 = "insert into user_projet (id_projetpk, id_userpk, role) values "
											+ "( " + idProjet + "," + membre + ","+ role + ")";
			System.out.println(hsql1);
			session.createSQLQuery(hsql1).executeUpdate();
			session.getTransaction().commit();
			session.close();
			
			
			//LogDB.insertLogDB("Projet", "SaveProjet: "+ hsql1, request);

		}
	
	
}
