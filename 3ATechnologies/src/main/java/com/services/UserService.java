package com.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Projet;
import com.entities.User;
import com.interfaces.IuserService;

@Service
@Transactional
public class UserService implements IuserService {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> getAllProject() {
		TypedQuery<User> q = entityManager.createQuery("SELECT u FROM User u", User.class);
		return q.getResultList();
	}

}
