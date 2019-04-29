package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.BugImpact;

public interface IBugImpactRepository extends JpaRepository<BugImpact, Long>{
	


}
