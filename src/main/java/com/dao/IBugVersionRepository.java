package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.BugVersion;

public interface IBugVersionRepository extends JpaRepository<BugVersion, Long>{
	


}
