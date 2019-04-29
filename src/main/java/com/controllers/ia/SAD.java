package com.controllers.ia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.controllers.LogDB;
import com.entities.ConnexionDB;
import com.util.HibernateUtility;
@RestController
public class SAD {

	@Autowired
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	
	
	
	@RequestMapping(value = "/api/simulation", method = RequestMethod.GET)
	public void simulation( Integer projectID, HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		
		
		SimpleDateFormat formater = null;
		formater = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		java.util.Date date = new Date();
		String date_Operation = formater.format(date);
		
		
		int failed_niv1 = ThreadLocalRandom.current().nextInt(0, 10 + 1);
		int failed_niv2 = ThreadLocalRandom.current().nextInt(0, 10 + 1);
		int failed_niv3 = ThreadLocalRandom.current().nextInt(0, 10 + 1);
		int failed_niv4 = ThreadLocalRandom.current().nextInt(0, 10 + 1);
		int blocked = ThreadLocalRandom.current().nextInt(0, 100 + 1);
		int not_run = ThreadLocalRandom.current().nextInt(0, 100 + 1);
		int passed = ThreadLocalRandom.current().nextInt(0, 100 + 1);
		int sonar1release = ThreadLocalRandom.current().nextInt(0, 100 + 1);
		int sonar2release = ThreadLocalRandom.current().nextInt(0, 100 + 1);
		int sonar3release = ThreadLocalRandom.current().nextInt(0, 100 + 1);
		int sonar4release = ThreadLocalRandom.current().nextInt(0, 100 + 1);
		
		int failed = failed_niv1 + failed_niv2 + failed_niv3 + failed_niv4;
		int toatl = failed + blocked + not_run + passed;
		
		String name_compagne = "Auto_Name_" + date_Operation;
		String description = "Auto_Desc_" + date_Operation;
		
		String hsql1 = "INSERT INTO executoin(id_projet, name_compagne, date_execution, date_import, description, "
						+ "failed, failed_niv1, failed_niv2, failed_niv3, failed_niv4, blocked,  not_run, passed, toatl, refdbimport) "
						+ "VALUES ('"+ projectID +"','"+name_compagne +"','"+ date_Operation +"','"+ date_Operation +"','"+ description +"',"
								+ "'"+failed +"','"+ failed_niv1+"','"+ failed_niv2+"','"+ failed_niv3+"','"+ failed_niv4+"','"+ blocked+"','"+ not_run+"','"+ passed+"','"+ toatl +"','0');" ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		//session.getTransaction().commit();
		
		int etat_release = 1;
		
		if (failed>15) {
			etat_release = 0;
		}
		
		hsql1 = "INSERT INTO releases (id_projet, name_release, date_release, description, etat_release, "
				+ "blocked, failed, failed_niv1, failed_niv2, failed_niv3, failed_niv4, not_run, passed, "
				+ "sonar1release, sonar2release, sonar3release, sonar4release) "
				+ "VALUES ('"+ projectID +"','"+name_compagne +"','"+ date_Operation +"','"+ description +"','"+ etat_release +"',"
							+ "'"+blocked +"','"+failed +"','"+ failed_niv1+"','"+ failed_niv2+"','"+ failed_niv3+"','"+ failed_niv4+"','"+ not_run+"','"+ passed+"',"
								+ "'"+ sonar1release +"','"+ sonar2release +"','"+ sonar3release +"','"+ sonar4release +"');" ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		
		session.close();
		LogDB.insertLogDB("ModuleApp", "updateModuleApp: "+ hsql1, request);
	}
	
	
	
	@RequestMapping(value = "/api/avancementTempsAllProjet", method = RequestMethod.GET)
	public void avancementTempsAllProjet( HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update projet set avancement = DATEDIFF(CURDATE(), date_debut_reelle) / DATEDIFF(date_fin_prevu, date_debut_reelle) ;" ;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		LogDB.insertLogDB("ModuleApp", "updateModuleApp: "+ hsql1, request);
	}
	
	
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/api/avancementRessourceAllProjet", method = RequestMethod.GET)
	public void avancementRessourceAllProjet( HttpServletRequest request) throws SQLException {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		ConnexionDB connexionDB = new ConnexionDB();
		connexionDB.ConnexionDBInit();
		
		String loginDB = connexionDB.getLoginDB();
		String pwDB = connexionDB.getPwDB();
		String path = connexionDB.getPath();
		
		Connection con = DriverManager.getConnection(path, loginDB, pwDB);
		String query = "SELECT projectid FROM projet;";
		
		 PreparedStatement pst = con.prepareStatement(query);
         ResultSet rs = pst.executeQuery();

     while (rs.next()) {
        
        String hsql1 = "update projet set nb_ressource_reelle = "
        		+ "(SELECT count(*) FROM user_projet where id_projetpk = "+rs.getInt(1)+")  where projectid = "+rs.getInt(1)+" ;" ;
 		System.out.println(hsql1);
 		session.createSQLQuery(hsql1).executeUpdate();
     }
     session.getTransaction().commit();
     session.close();
     
	}
	
	
	
	
	

    
           
	
	
	@RequestMapping(value = "/api/avancementTemps", method = RequestMethod.GET)
	public List avancementTemps(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT  DATEDIFF(CURDATE(), date_debut_reelle) / DATEDIFF(date_fin_prevu, date_debut_reelle) " + 
				"FROM projet " + 
				"where projectid = " + projectID + "   ; " ;
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	
	

	@RequestMapping(value = "/api/avancementQuantites", method = RequestMethod.GET)
	public float avancementQuantites(Integer projectID,  HttpServletRequest request) {
		
		Session session4 = sessionFactory.openSession();
		session4.getTransaction().begin();
		String hsql1 = "SELECT sum(nb_cas_test) FROM module_app where id_projet = " + projectID + "  ; " ;
				
		System.out.println(hsql1);
		Query sqlQuery3 = session4.createSQLQuery(hsql1);
		String coutInitial = sqlQuery3.list().get(0).toString();
		session4.close();
		Session session5 = sessionFactory.openSession();
		session5.getTransaction().begin();
		hsql1 = "SELECT nb_cas_de_test_reel FROM projet where projectid = " + projectID + "; " ;
		System.out.println(hsql1);
		Query sqlQuery4 = session5.createSQLQuery(hsql1);
		String coutActualise = sqlQuery4.list().get(0).toString();
		session5.close();
		return (Float.parseFloat(coutInitial) / Float.parseFloat(coutActualise));
	}

	
	
	
	
	
	
	
	
	@RequestMapping(value = "/api/avancementTaches", method = RequestMethod.GET)
	public List avancementTaches(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT sum(avancement_us)/ (count(*))  FROM user_story, sprint where id_sprint = id_sp and id_projet = " + projectID + ";";
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	
	@RequestMapping(value = "/api/tauxRetard", method = RequestMethod.GET)
	public List tauxRetard(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT sum(t.nbjours * u.tjm) FROM timesheet t " + 
				"inner join  user_projet u on t.iduser = u.id_userpk and t.idprojet = u.id_projetpk " + 
				"where t.idprojet = " + projectID + "  ; ";
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	@RequestMapping(value = "/api/coutReelProjet", method = RequestMethod.GET)
	public List coutReelProjet(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT   sum(((DATEDIFF(date_fin_estime_us, date_debut_estime_sp) * avancement_sp)/100) * tjm) " + 
				"FROM user_story, sprint, user_projet " + 
				"where id_sprint = id_sp and id_projet = " + projectID + " and avancement_us < 100 and  id_User  = id_userpk and id_projetpk = id_projet; " ;
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	@RequestMapping(value = "/api/depassementCoutProjet", method = RequestMethod.GET)
	public List depassementCoutProjet(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT   sum(((DATEDIFF(date_fin_estime_us, date_debut_estime_sp) * avancement_sp)/100) * tjm) " + 
				"FROM user_story, sprint, user_projet " + 
				"where id_sprint = id_sp and id_projet = " + projectID + " and avancement_us < 100 and  id_User  = id_userpk and id_projetpk = id_projet; ";
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	@RequestMapping(value = "/api/depassementCoutPhase", method = RequestMethod.GET)
	public List depassementCoutPhase(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT sum(((DATEDIFF(date_fin_estime_us, date_debut_estime_sp) * avancement_sp)/100)* tjm), id_sp, resume_sp " + 
				"FROM user_story, sprint, user_projet " + 
				"where id_sprint = id_sp and id_projet = " + projectID + " and avancement_us < 100 and  id_User  = id_userpk and id_projetpk = id_projet " + 
				"group by id_sp; ";
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	@RequestMapping(value = "/api/depassementCoutRessources", method = RequestMethod.GET)
	public List depassementCoutRessources(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT sum(((DATEDIFF(date_fin_estime_us, date_debut_estime_sp) * avancement_sp)/100)* tjm), id_User, nom " + 
				"FROM user_story, sprint, user_projet, users " + 
				"where id_sprint = id_sp and id_projet = " + projectID + " and avancement_us < 100 and  id_User  = id_userpk and id_projetpk = id_projet and id_User = userid " + 
				"group by id_User; " ;
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	@RequestMapping(value = "/api/depassementDelaiPhase", method = RequestMethod.GET)
	public List depassementDelaiPhase(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT sum(DATEDIFF(date_fin_estime_us, date_debut_estime_sp) ), id_sp, resume_sp " + 
				"FROM user_story, sprint, user_projet " + 
				"where id_sprint = id_sp and id_projet = " + projectID + " and avancement_us < 100 and  id_User  = id_userpk and id_projetpk = id_projet " + 
				"group by id_sp; ";
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	@RequestMapping(value = "/api/depassementDelaiRessource", method = RequestMethod.GET)
	public List depassementDelaiRessource(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT sum(DATEDIFF(date_fin_estime_us, date_debut_estime_sp) ), id_User, nom " + 
				"FROM user_story, sprint, user_projet, users " + 
				"where id_sprint = id_sp and id_projet = " + projectID + " and avancement_us < 100 and  id_User  = id_userpk and id_projetpk = id_projet and id_User = userid " + 
				"group by id_User; " ;
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	@RequestMapping(value = "/api/depassementDelaiProjet", method = RequestMethod.GET)
	public List depassementDelaiProjet(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT sum(DATEDIFF(date_fin_estime_us, date_debut_estime_sp) ) " + 
				"FROM user_story, sprint, user_projet " + 
				"where id_sprint = id_sp and id_projet = " + projectID + " and avancement_us < 100 and  id_User  = id_userpk and id_projetpk = id_projet ; ";
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	@RequestMapping(value = "/api/ressourceEmploi", method = RequestMethod.GET)
	public List ressourceEmploi(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT sum(DATEDIFF(date_debut_reels_us, date_fin_reels ) ) / sum(DATEDIFF(date_fin_estime_us, date_debut_estime_sp) ) *100 , id_User, nom " + 
				"FROM user_story, sprint, user_projet, users " + 
				"where id_sprint = id_sp and id_projet = " + projectID + "  and  id_User  = id_userpk and id_projetpk = id_projet and id_User = userid " + 
				"group by id_User; ";
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	@RequestMapping(value = "/api/ressourceCapacite", method = RequestMethod.GET)
	public List ressourceCapacite(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT sum(DATEDIFF(date_debut_reels_us, date_fin_reels ) ) / sum(DATEDIFF(date_fin_estime_us, date_debut_estime_sp) ) *100 , id_User, nom " + 
				"FROM user_story, sprint, user_projet, users " + 
				"where id_sprint = id_sp and id_projet = " + projectID + "  and  id_User  = id_userpk and id_projetpk = id_projet and id_User = userid " ;
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	@RequestMapping(value = "/api/ecartCout", method = RequestMethod.GET)
	public float ecartCout(Integer projectID,  HttpServletRequest request) {
		
		Session session4 = sessionFactory.openSession();
		session4.getTransaction().begin();
		String hsql1 = "SELECT sum(((DATEDIFF(date_fin_estime_us, date_debut_estime_sp) * avancement_sp)/100)* tjm) " +
								"FROM user_story, sprint, user_projet " + 
								"where id_sprint = id_sp and id_projet = " + projectID + " and id_User  = id_userpk and id_projetpk = id_projet; "; 
		System.out.println(hsql1);
		Query sqlQuery3 = session4.createSQLQuery(hsql1);
		String coutInitial = sqlQuery3.list().get(0).toString();
		session4.close();
		Session session5 = sessionFactory.openSession();
		session5.getTransaction().begin();
		hsql1 = "SELECT sum(((DATEDIFF(date_debut_reels_us, date_fin_reels) * avancement_sp)/100)* tjm) " +  
								"FROM user_story, sprint, user_projet " +
								"where id_sprint = id_sp and id_projet = " + projectID + " and id_User  = id_userpk and id_projetpk = id_projet;" ;
		System.out.println(hsql1);
		Query sqlQuery4 = session5.createSQLQuery(hsql1);
		String coutActualise = sqlQuery4.list().get(0).toString();
		session5.close();
		return (Float.parseFloat(coutInitial) / Float.parseFloat(coutActualise));
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/api/ecartDure", method = RequestMethod.GET)
	public float ecartDure(Integer projectID,  HttpServletRequest request) {
		
		Session session4 = sessionFactory.openSession();
		session4.getTransaction().begin();
		String hsql1 = "SELECT sum(((DATEDIFF(date_fin_estime_us, date_debut_estime_sp) * avancement_sp)/100)* tjm) " +
								"FROM user_story, sprint, user_projet " + 
								"where id_sprint = id_sp and id_projet = " + projectID + " and id_User  = id_userpk and id_projetpk = id_projet; "; 
		System.out.println(hsql1);
		Query sqlQuery3 = session4.createSQLQuery(hsql1);
		String coutInitial = sqlQuery3.list().get(0).toString();
		session4.close();
		Session session5 = sessionFactory.openSession();
		session5.getTransaction().begin();
		hsql1 = "SELECT sum(((DATEDIFF(date_debut_reels_us, date_fin_reels) * avancement_sp)/100)) " +  
				 "FROM user_story, sprint, user_projet " +  
				 "where id_sprint = id_sp and id_projet = " + projectID + " and id_User  = id_userpk and id_projetpk = id_projet;" ;
		System.out.println(hsql1);
		Query sqlQuery4 = session5.createSQLQuery(hsql1);
		String coutActualise = sqlQuery4.list().get(0).toString();
		session5.close();
		return (Float.parseFloat(coutInitial) / Float.parseFloat(coutActualise));
	}
	
	
	
	
	@RequestMapping(value = "/api/pointsSuspens", method = RequestMethod.GET)
	public List pointsSuspens(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT count(*) " + 
				"FROM user_story, sprint " + 
				"where id_sprint = id_sp and id_projet = " + projectID + "  and statut_us = 3; ";
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	@RequestMapping(value = "/api/densiteRisque", method = RequestMethod.GET)
	public List densiteRisque(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT sum(criticite)/count(*) FROM risque;";
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	@RequestMapping(value = "/api/nbRisqueRaalise", method = RequestMethod.GET)
	public List nbRisqueRaalise(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT count(*) FROM declaration_risque where statut = 3;";
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	
	@RequestMapping(value = "/api/risqueRealise", method = RequestMethod.GET)
	public List risqueRealise(Integer projectID,  HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT 100-sum(avancement_us)/ (count(*))  FROM user_story, sprint where id_sprint = id_sp and id_projet = " + projectID + ";";
		System.out.println(sql);
		return session.createSQLQuery(sql).list();

	}
	
	
	
	
	
	
	@RequestMapping(value = "/api/coutVsBudget", method = RequestMethod.GET)
	public static float coutVsBudget(Integer projectID,  HttpServletRequest request) {
		
		Session session4 = sessionFactory.openSession();
		session4.getTransaction().begin();
		String hsql1 = "SELECT sum(t.nbjours * u.tjm) FROM timesheet t " + 
				"inner join  user_projet u on t.iduser = u.id_userpk and t.idprojet = u.id_projetpk " + 
				"where t.idprojet = " + projectID + "  ; " ;
				
		System.out.println(hsql1);
		Query sqlQuery3 = session4.createSQLQuery(hsql1);
		String coutInitial = sqlQuery3.list().get(0).toString();
		session4.close();
		Session session5 = sessionFactory.openSession();
		session5.getTransaction().begin();
		hsql1 = "SELECT budget_prevu FROM projet where projectid = " + projectID + "; " ;
		System.out.println(hsql1);
		Query sqlQuery4 = session5.createSQLQuery(hsql1);
		String coutActualise = sqlQuery4.list().get(0).toString();
		session5.close();
		return (Float.parseFloat(coutInitial) / Float.parseFloat(coutActualise));
	}

	
	
}
