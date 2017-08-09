package com.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.interfaces.IuserService;

@Service
@Transactional
public class UserService implements IuserService {
	@PersistenceContext
	private EntityManager entityManager;

}
