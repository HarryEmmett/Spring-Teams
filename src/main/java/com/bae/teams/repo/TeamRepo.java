package com.bae.teams.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bae.teams.domain.Team;

public interface TeamRepo extends JpaRepository<Team, Integer> { 
	
	//Spring uses this extension to handle SQL CRUD queries in the background

	List<Team> findByLeague(String league);
	
	List<Team> findByName(String name);
	
	List<Team> findByStadiumCapacity(Integer stadiumCapacity);
	
	List<Team> findByYearFounded(Integer yearFounded);
}
