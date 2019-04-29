package com.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entities.Role;

@Repository
public interface UserRolesRepository extends JpaRepository<Role, Long> {
	
	/*  and a.userroleid=b.userroleid */
@Query("select c.roleName from UserRole a, User b, Role c where b.userName=?1 and a.roleid=c.idRole and b.userId = a.userid")
    public List<String> findRoleByUserName(String username);

	
}