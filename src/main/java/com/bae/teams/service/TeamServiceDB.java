package com.bae.teams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bae.teams.domain.Team;
import com.bae.teams.repo.TeamRepo;

@Service
public class TeamServiceDB implements TeamService {
	
	private TeamRepo repo;
	
	@Autowired
	public TeamServiceDB(TeamRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Team createTeam(Team t) {
		Team created = this.repo.save(t);  //INSERT INTO team - SQL 
		return created;
	}

	@Override
	public List<Team> getAllTeam() {
		return this.repo.findAll(); // SELECT * FROM team 
	}

	@Override
	public Team getTeam(Integer id) {
		Optional<Team> idFound = this.repo.findById(id);
		return idFound.get();
	}

	@Override
	public Team replaceTeam(Integer id, Team newTeam) {
		Team existing = this.repo.findById(id).get();
		
		existing.setLeague(newTeam.getLeague());
		existing.setName(newTeam.getName());
		existing.setStadiumCapacity(newTeam.getStadiumCapacity());
		existing.setYearFounded(newTeam.getYearFounded());
		
		Team updated = this.repo.save(existing);
		return updated;
	}

	@Override
	public void removeTeam(Integer id) {
		this.repo.deleteById(id);
		
	}

	@Override
	public List<Team> getAllTeamByLeague(String league) {
		List<Team> found = this.repo.findByLeague(league);
		return found;
	}

}
