package com.bae.teams.service;

import java.util.ArrayList;
import java.util.List;

import com.bae.teams.domain.Team;

public class TeamServiceList implements TeamService{
	
	private List<Team> teams = new ArrayList<>();
	
	@Override
	public Team createTeam(Team t) {
		this.teams.add(t);
		Team created = this.teams.get(this.teams.size() - 1);
		return created;
	}
	
	@Override
	public List<Team> getAllTeam() {
		return this.teams;
	}
	
	@Override
	public Team getTeam(Integer id) {
		System.out.println("ID " + id);
		System.out.println(teams);
		Team getId = teams.get(id);
		return getId;
	}
	
	@Override
	public Team replaceTeam(Integer id, Team newTeam) {
		System.out.println("ID: " + id);
		System.out.println("DINO: " + newTeam);
		Team created = teams.set(id, newTeam);
		return created;
	}

	@Override
	public void removeTeam(Integer id) {
		teams.remove(id.intValue());
	}

	@Override
	public List<Team> getAllTeamByLeague(String league) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
