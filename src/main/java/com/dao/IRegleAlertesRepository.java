package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.RegleAlerte;

public interface IRegleAlertesRepository extends JpaRepository<RegleAlerte, Long> {

	@Query("select t from RegleAlerte  t  where  t.idprojet =  :xid and  valeur > minVal and valeur < maxVal")
	public List<RegleAlerte> findProjetId(@Param("xid") Long id_projet);

	@Query("select t from RegleAlerte  t  where  t.idprojet =  :xid")
	public List<RegleAlerte> findAllAlerteProjetId(@Param("xid") Long id_projet);

}
