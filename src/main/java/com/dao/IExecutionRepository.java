package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Executoin;

public interface IExecutionRepository extends JpaRepository<Executoin, Long>{
	
	@Query("select t from Executoin  t  where  t.idProjet =  :xid Order by dateExecution DESC")
	public List<Executoin> findProjetId(@Param("xid") Long id_projet);
	
	
	@Query("select t from Executoin  t  where  t.idExecution =  :xid ")
	public List<Executoin> findExecutionId(@Param("xid") Long id_execution);
	
	

}
