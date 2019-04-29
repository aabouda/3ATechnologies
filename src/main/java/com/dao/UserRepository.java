package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.User;
 
 
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String username);
 
 
	@Query("select t from User  t  where  t.userId =:iiduse ")
	public List<User> findUserId(@Param("iiduse") Long userId);

	
	
	

	 
	   
}