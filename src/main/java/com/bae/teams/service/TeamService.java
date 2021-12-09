package com.bae.teams.service;

import java.util.List;

import com.bae.teams.domain.Team;

public interface TeamService {
	
	Team createTeam(Team t);

	List<Team> getAllTeam();
	
	List<Team> getAllTeamByLeague(String league);

	Team getTeam(Integer id);

	Team replaceTeam(Integer id, Team newTeam);

	void removeTeam(Integer id);

}
