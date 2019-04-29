package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Releases;

public interface IReleaseRepository extends JpaRepository<Releases, Long>{
	
	@Query("select t from Releases  t  where  t.idProjet =  :xid Order by dateRelease desc")
	public List<Releases> findProjetId(@Param("xid") Long id_projet);
	
	
	@Query("select t from Releases  t  where  t.idRelease =  :xid ")
	public List<Releases> findReleaseId(@Param("xid") Long id_release);
	
	

}
