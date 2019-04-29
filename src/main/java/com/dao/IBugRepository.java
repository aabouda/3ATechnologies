package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entities.Bug;

public interface IBugRepository extends JpaRepository<Bug, Long>{
	


}
