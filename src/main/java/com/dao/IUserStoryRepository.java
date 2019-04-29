package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entities.UserStory;

public interface IUserStoryRepository extends JpaRepository<UserStory, Long> {

	@Query("select t from UserStory  t  where  t.idSprint =  :xid ")
	public List<UserStory> findSprintId(@Param("xid") Long id_sprint);

	@Query("select t from UserStory  t  where  t.idUserStory =  :xid ")
	public List<UserStory> findUserStoryId(@Param("xid") Long id_user_story);

}
