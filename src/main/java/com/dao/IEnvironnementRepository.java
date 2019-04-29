package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Alertes;
import com.entities.Environnement;

public interface IEnvironnementRepository extends JpaRepository<Environnement, Long> {

	@Query("select t from Environnement  t  where  t.idEnvironnement =  :xid ")
	public List<Environnement> findEnvironnement(@Param("xid") Long id_Environnement);
	
	
	@Query("select t from Environnement  t  where  t.idProjet =  :xid ")
	public List<Environnement> findProjetId(@Param("xid") Long idProjet);
	

}
