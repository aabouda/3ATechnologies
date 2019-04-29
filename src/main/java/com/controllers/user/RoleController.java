package com.controllers.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UserRolesRepository;
import com.entities.Role;
import com.util.HibernateUtility;

@RestController
public class RoleController {

	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	@Autowired
	private UserRolesRepository rolerepository;
	
	
	@RequestMapping(value = "/api/listeUser", method = RequestMethod.GET)
	public List<Role> getlisteUser(  HttpServletRequest request) {
		return  rolerepository.findAll();

	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/api/user/roles", method = RequestMethod.GET)
	public List<String> getCurrentUserRoles() {
		return  rolerepository.findRoleByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

	}
	
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/api/GetRoleUserId")
	public List GetRoleUserId(Integer userRole) {
		Session session = sessionFactory.openSession();
		String sql = "select c.role_name from User_Roles a, Roles c where  a.roleid=c.id_role and  a.userid=" + userRole ;
		System.out.println(sql);
		return session.createSQLQuery(sql).list();
	}
	
	
	
}
