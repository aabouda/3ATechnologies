package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.Langues;

public interface ILanguesRepository extends JpaRepository<Langues, Long> {

	@Query("select t from Langues  t  where  t.module =  :xid ")
	public List<Langues> findLanguesId(@Param("xid") Long module);

	

}
