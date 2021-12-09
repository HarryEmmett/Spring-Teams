package com.bae.teams.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bae.teams.domain.Team;
import com.bae.teams.service.TeamService;

@RestController
public class TeamController {
	
	private TeamService service;
	
	@Autowired
	public TeamController(TeamService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/helloWorld")
	public String hello() {
		return "Hello, World!";
	}
	
	@PostMapping("/create")
	public ResponseEntity<Team> createTeam(@RequestBody Team t) {
		Team created = this.service.createTeam(t);
		ResponseEntity<Team> response = new ResponseEntity<Team>(created, HttpStatus.CREATED);
		return response;
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Team>> getAllTeam() {
		return ResponseEntity.ok(this.service.getAllTeam());
	}
	
	@GetMapping("/get/{id}")
	public Team getTeam(@PathVariable Integer id) {
		System.out.println("ID " + id);
		System.out.println(service);
		Team getId = service.getTeam(id);
		return getId;
	}
	
	@PutMapping("/replace/{id}")
	public ResponseEntity<Team> replaceTeam(@PathVariable Integer id, @RequestBody Team newTeam) {
		System.out.println("ID: " + id);
		System.out.println("DINO: " + newTeam);
		Team body = this.service.replaceTeam(id, newTeam);

		ResponseEntity<Team> response = new ResponseEntity<Team>(body, HttpStatus.ACCEPTED);
		return response;
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Team> removeTeam(@PathVariable Integer id) {
		this.service.removeTeam(id);
		ResponseEntity<Team> response = new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
		return response;
	}
	
	@GetMapping("/getByLeague/{league}")
	public ResponseEntity<List<Team>> getTeamByLeague(@PathVariable String league) {
		List<Team> found = this.service.getAllTeamByLeague(league);
		return ResponseEntity.ok(found);
	}
	
	
}


