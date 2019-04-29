package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Verssion;

public interface IVerssionRepository extends JpaRepository<Verssion, Long> {

	@Query("select t from Verssion  t  where  t.idVerssion =  :xid ")
	public List<Verssion> findVerssion(@Param("xid") Long id_Verssion);
	
	

	@Query("select t from Verssion  t  where  t.idProjet =  :xid ")
	public List<Verssion> findProjetId(@Param("xid") Long idProjet);

}
