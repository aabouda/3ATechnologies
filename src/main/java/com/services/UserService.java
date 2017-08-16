package com.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.User;
import com.interfaces.IuserService;

@Service
@Transactional
public class UserService implements IuserService {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<User> getAllUsers() {
		TypedQuery<User> q = entityManager.createQuery("SELECT u FROM User u", User.class);
		return q.getResultList();
	}

	@Override
	public List<User> getUsersToAdd(String pattern) {
		TypedQuery<Object[]> q = entityManager.createQuery("SELECT u.userID,u.firstName,u.lastName FROM User u where u.firstName LIKE :ln or u.lastName LIKE :ln ", Object[].class);
		q.setParameter("ln", '%' + pattern + '%');
		List<Object[]> results = q.getResultList();
		List<User> Luser = new ArrayList<User>();
		for (Object[] result : results) {
			Luser.add(new User(Long.valueOf(String.valueOf(result[0])),String.valueOf(result[1]),String.valueOf(result[2])));
		}
		return Luser;
	}

	@Override
	public User getUserById(long userID) {
		return entityManager.find(User.class, userID);
	}

}
