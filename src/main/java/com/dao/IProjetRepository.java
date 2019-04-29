package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Projet;

public interface IProjetRepository extends JpaRepository<Projet, Long> {

	@Query("select t from Projet  t  where  t.projectID =  :xid ")
	public List<Projet> findProjet(@Param("xid") Long id_Projet);

}
