package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Parametres;

public interface IParametresRepository extends JpaRepository<Parametres, Long> {

	
	
	@Query("select t from Parametres  t  where  t.idProjet =  :xid ")
	public List<Parametres> findProjetId(@Param("xid") Long idProjet);
	

}
