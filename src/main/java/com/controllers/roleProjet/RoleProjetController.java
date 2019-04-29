package com.controllers.roleProjet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.IRoleProjetRepository;
import com.entities.RoleProjet;
import com.util.HibernateUtility;

@RestController
public class RoleProjetController {

	@Autowired
	private IRoleProjetRepository roleProjetrepository;

	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

	@RequestMapping(value = "/api/RoleProjet", method = RequestMethod.GET)
	public List<RoleProjet> getProjet(HttpServletRequest request) {
		return roleProjetrepository.findAll();
	}

}
