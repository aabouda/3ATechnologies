package com.controllers.sprint;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.IAssignationRepository;

import com.entities.Assignation;
import com.util.HibernateUtility;
@RestController
public class AssignationController {

	@Autowired
	private IAssignationRepository Assignationrepository;
	
	private static SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	
	
	
	@RequestMapping(value = "/api/assignationUserStory", method = RequestMethod.GET)
	public List<Assignation> getAlerteId(Integer USId,  HttpServletRequest request) {
		
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		String hsql1 = "update assignation set lu = 1 where id_alerte =  " + USId;
		System.out.println(hsql1);
		session.createSQLQuery(hsql1).executeUpdate();
		session.getTransaction().commit();
		session.close();
		
		return Assignationrepository.findAlerteId(Long.valueOf(USId));
	}
	
}
