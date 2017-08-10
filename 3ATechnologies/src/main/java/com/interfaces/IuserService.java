package com.interfaces;

import java.util.List;

import com.entities.User;



public interface IuserService {
	
	public List<User> getAllUsers();
	
	public List<User> getUsersToAdd(String pattern);

}
