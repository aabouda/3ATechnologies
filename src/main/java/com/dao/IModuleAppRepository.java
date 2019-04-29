package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.ModuleApp;

public interface IModuleAppRepository extends JpaRepository<ModuleApp, Long> {

	@Query("select t from ModuleApp  t  where  t.idModuleApp =  :xid ")
	public List<ModuleApp> findModuleApp(@Param("xid") Long id_ModuleApp);
	
	

	@Query("select t from ModuleApp  t  where  t.idProjet =  :xid ")
	public List<ModuleApp> findProjetId(@Param("xid") Long idProjet);

}
