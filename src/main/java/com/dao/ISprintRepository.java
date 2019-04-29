package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Sprint;

public interface ISprintRepository extends JpaRepository<Sprint, Long> {

	@Query("select t from Sprint  t  where  t.idProjet =  :xid Order by dateDebutEstimeSp Desc")
	public List<Sprint> findProjetId(@Param("xid") Long id_projet);

	@Query("select t from Sprint  t  where  t.idSp =  :xid ")
	public List<Sprint> findSprintId(@Param("xid") Long id_Sp);

}
