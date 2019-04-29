package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Alertes;

public interface IAlertesRepository extends JpaRepository<Alertes, Long> {

	@Query("select t from Alertes  t  where  t.idProjet =  :xid ")
	public List<Alertes> findProjetId(@Param("xid") Long id_projet);

	@Query("select t from Alertes  t  where  t.idAlerte =  :xid ")
	public List<Alertes> findAlerteId(@Param("xid") Long id_aletre);

}
