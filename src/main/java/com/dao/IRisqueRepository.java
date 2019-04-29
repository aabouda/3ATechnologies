package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Risque;

public interface IRisqueRepository extends JpaRepository<Risque, Long> {

	@Query("select t from Risque  t  where  t.idProjet =  :xid ")
	public List<Risque> findProjetId(@Param("xid") Long id_projet);

	@Query("select t from Risque  t  where  t.idRisque =  :xid ")
	public List<Risque> findRisqueId(@Param("xid") Long idRisque);

}
