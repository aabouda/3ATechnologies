package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Alertes;
import com.entities.Societe;

public interface ISocieteRepository extends JpaRepository<Societe, Long> {

	@Query("select t from Societe  t  where  t.id_societe =  :xid ")
	public List<Societe> findSocieteId(@Param("xid") Long id_societe);
	
}
