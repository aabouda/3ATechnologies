package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.DeclarationRisque;

public interface IDeclarationRisqueRepository extends JpaRepository<DeclarationRisque, Long> {

	@Query("select t from DeclarationRisque  t  where  t.idRisque =  :xid ")
	public List<DeclarationRisque> findRisqueId(@Param("xid") Long id_sprint);

	@Query("select t from DeclarationRisque  t  where  t.idDeclarationRisque =  :xid ")
	public List<DeclarationRisque> findDeclarationRisqueId(@Param("xid") Long idDeclarationRisque);

}
