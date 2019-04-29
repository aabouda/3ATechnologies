package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Assignation;

public interface IAssignationRepository extends JpaRepository<Assignation, Long> {

	

	@Query("select t from Assignation  t  where  t.idUS =  :xid ")
	public List<Assignation> findAlerteId(@Param("xid") Long id_US);

}
