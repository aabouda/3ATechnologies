package com.controllers.user;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UserRepository;
import com.entities.User;
import com.util.HibernateUtility;

@RestController
public class UserService {
	@Autowired
	DataSource dataSource;

	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	
	
	@RequestMapping(value = "/api/finduser")
	public String finduser(String username) throws SQLException, ClassNotFoundException {
		String userid="";
		Session session3 = sessionFactory.openSession();
		session3.getTransaction().begin();
		String sql = "SELECT count(*) as qt from users where username='"+username+"'";
		System.out.println(sql);
		Query  sqlQuery = session3.createSQLQuery(sql);
		 userid= sqlQuery.list().get(0).toString();
		 System.out.println(userid);
		 session3.close();
		 return (userid);

	}
	
	
	
	
	
	@RequestMapping(value = "/api/ajouteruser")
	public void ajouterusert(String username, String lastname, String email, String teleph, String poste,
			String nom, String password , Integer enabled,String[] role,  HttpServletRequest request)
			throws SQLException, ClassNotFoundException {
		
		String userid="";
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		String hsql1 = "insert into users (userid,email,enabled,lastname,password,poste,tele,username,nom,idmodule,agence_id, id_agence)"
				+ " values(USERID_SEQ.Nextval,'" + email + "',"+enabled+",'" + lastname + "','" + password + "','" + poste
				+ "'," + teleph + ",'" + username + "','" + nom + "','',10,10)";
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		// ***********************************************************************

		Session session3 = sessionFactory.openSession();
		session3.getTransaction().begin();
		
		String sql = "SELECT userid from users where username='"+username+"'";
		System.out.println(sql);
		Query  sqlQuery = session3.createSQLQuery(sql);
		 userid= sqlQuery.list().get(0).toString();
		 System.out.println(userid);
		for (int i = 0; i < role.length; i++) {
			System.out.println("Str[" + i + "]:" + role[i]);
			String sql2 = "SELECT id_role from roles where role_name='"+role[i]+"'";
			System.out.println(sql2);
			Query  sqlQuery2 = session3.createSQLQuery(sql2);
			String roleid= sqlQuery2.list().get(0).toString();
			String Sql = "insert into user_roles (USER_ROLE_ID,USERID,ROLEID)"
					+ " values(USER_ROLES_SEQ.Nextval,"+userid+"," + roleid + ")";
			System.out.println(Sql);
			session.createSQLQuery(Sql).executeUpdate();
			}

		session.close();

	}
	
	
	@RequestMapping(value = "/api/modifieruser")
	public void modifierusert(String username, String lastname, String email, String teleph, String poste,
			String nom, String password , Integer enabled,String[] role, Long userId,  HttpServletRequest request)
			throws SQLException, ClassNotFoundException {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		
		String hsql1 = "update users set   email= '" + email + "',enabled = " + enabled+ ",lastname = '" + lastname+ "',password = '" + password+ "',poste = '" + poste+ "' "
				+ ",tele = " + teleph+ " ,username = '" + username+ "' where userid = " + userId;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();

		Session session3 = sessionFactory.openSession();
		session3.getTransaction().begin();
		
		String sql = "delete from user_roles where USERID='"+userId+"'";
		System.out.println(sql);
		session3.createSQLQuery(sql).executeUpdate();
		
		
		for (int i = 0; i < role.length; i++) {
			System.out.println("Str[" + i + "]:" + role[i]);
			String sql2 = "SELECT id_role from roles where role_name='"+role[i]+"'";
			System.out.println(sql2);
			Query  sqlQuery2 = session3.createSQLQuery(sql2);
			String roleid= sqlQuery2.list().get(0).toString();
			String Sql = "insert into user_roles (USER_ROLE_ID,USERID,ROLEID)"
					+ " values(USER_ROLES_SEQ.Nextval,"+userId+"," + roleid + ")";
			System.out.println(Sql);
			session.createSQLQuery(Sql).executeUpdate();
			}

		session.close();
		session3.close();

	}
	
	
	
	
	@Autowired
	private UserRepository userrepository;

	@RequestMapping(value = "/api/listeUsers", method = RequestMethod.GET)
	public List<User> getlisteUsers() {
		return userrepository.findAll();

	}

	
	@RequestMapping(value = "/api/actuel")
	@PreAuthorize("isAuthenticated()")
	public User getCurrentUser() {
		String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
		String currentRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		System.out.println("current name : "+currentName);
		System.out.println("current role : "+currentRole);
		User user = userrepository.findByUserName(currentName);
		return user;
	}
	
	@RequestMapping(value = "actuelrole2")
	@PreAuthorize("hasRole('ROLE_DASHBORD')")
	public User getCurrentUserRole2() {
		String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("current name : "+currentName);
		User user = userrepository.findByUserName(currentName);
		return user;
	}
	
	@RequestMapping(value = "actuelrole1")
	@PreAuthorize("hasRole('ROLE_DASHBORD')")
	public User getCurrentUserRole1() {
		String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("current name : "+currentName);
		User user = userrepository.findByUserName(currentName);
		return user;
	}
	
	
	@RequestMapping(value = "/api/GetUserId")
	public List<User> getUserId(Long userId) {
		return userrepository.findUserId(userId);
	}
	
	@RequestMapping(value = "/api/listeUsersProjetIn")
	public List listeUsersProjetIn(Long projetId) {
		Session session = sessionFactory.openSession();
		return session
				.createSQLQuery("SELECT username,userid, role, role_projet, tjm "
						+ "FROM  users  "
						+ "left join user_projet on id_userpk = userid "
						+ "left join role_projet on role = id_role_projet "
						+ "where userid in (select id_userpk from user_projet where id_projetpk = "+projetId+") and id_projetpk = "+projetId+" ;")
				.list();
	}
	
	@RequestMapping(value = "/api/listeUsersProjetOut")
	public List listeUsersProjetOut(Long projetId) {
		Session session = sessionFactory.openSession();
		return session
				.createSQLQuery("SELECT username,userid, role, tjm "
						+ "FROM  users  "
						+ "left join user_projet on id_userpk = userid "
						+ "where userid not in (select id_userpk from user_projet where id_projetpk = "+projetId+") ;")
				.list();
	}
		
	
	@RequestMapping(value = "/api/listeUsersProjetInForce")
	public List listeUsersProjetInForce(Long projetId, String id_sprint) {
		Session session = sessionFactory.openSession();
		String sql = "select username, userid, sum(heures_estime_us) from (" + 
				"			SELECT username,userid, role, role_projet" + 
				"			FROM  users " + 
				"			left join user_projet on id_userpk = userid " + 
				"			left join role_projet on role = id_role_projet " + 
				"			where userid in (select id_userpk from user_projet where id_projetpk = "+projetId+")  and id_projetpk = "+projetId+") P " + 
				"			left join  user_story on userid = id_User  and id_sprint = '" + id_sprint +  "' " +
				"			group by userid ;";
		System.out.println(sql);
		return session
				.createSQLQuery(sql)
				.list();
	}

	
	
	
}
