package com.bae.teams.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.teams.domain.Team;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:team-schema.sql",
		"classpath:team-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TeamControllerIntergrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		Team testTeam = new Team(null, "Blackburn", 1875, 38000, "Championship");
		String testTeamAsJSON = this.mapper.writeValueAsString(testTeam);
		RequestBuilder req = post("/create").content(testTeamAsJSON).contentType(MediaType.APPLICATION_JSON);
		
		Team testCreatedTeam = new Team(2, "Blackburn", 1875, 38000, "Championship");
		String testCreatedTeamAsJson = this.mapper.writeValueAsString(testCreatedTeam);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedTeamAsJson);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testCreate2() throws Exception {
		Team testTeam = new Team(null, "Blackburn Rovers", 1875, 38000, "Championship");  // this to create test
		String testTeamAsJSON = this.mapper.writeValueAsString(testTeam);
		RequestBuilder req = post("/create").content(testTeamAsJSON).contentType(MediaType.APPLICATION_JSON);
		
		Team testCreatedTeam = new Team(2, "Blackburn Rovers", 1875, 38000, "Championship"); // this is the object that should be created
		String testCreatedTeamAsJson = this.mapper.writeValueAsString(testCreatedTeam);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedTeamAsJson);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetAll() throws Exception {
		RequestBuilder req = get("/getAll"); // just getting all not passing anything in so just looking for the 1 result in the sql schema
		
		List<Team> testAllTeams = List.of(new Team(1, "Blackburn Rovers", 1875, 38000, "Championship")); //list of teams that should come back (can do the array list way with .add team)
		String testAllTeamsAsJSON = this.mapper.writeValueAsString(testAllTeams);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testAllTeamsAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testGetById() throws Exception {
		RequestBuilder req = get("/get/1"); //just getting value with id 1 in table
		
		Team testGetById = new Team(1, "Blackburn Rovers", 1875, 38000, "Championship"); //what should be returned
		String testGetByIdJSON = this.mapper.writeValueAsString(testGetById);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testGetByIdJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testReplaceTeam() throws Exception {	
		Team testUpdate = new Team(null, "Man United", 1878, 75000, "Premier League");
		String testUpdateAsJSON = this.mapper.writeValueAsString(testUpdate);
		RequestBuilder req = put("/replace/1").content(testUpdateAsJSON).contentType(MediaType.APPLICATION_JSON);
		
		Team testUpdatedTeam = new Team(1, "Man United", 1878, 75000, "Premier League"); 
		String testUpdatedAsJSON = this.mapper.writeValueAsString(testUpdatedTeam);
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testUpdatedAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testRemoveTeam() throws Exception {
		RequestBuilder req = delete("/remove/1");
		ResultMatcher checkStatus = status().isNoContent();
    
		this.mvc.perform(req).andExpect(checkStatus);
	}
	
	@Test
	void testGetTeamByLeague() throws Exception {
		RequestBuilder req = get("/getByLeague/Championship");
		
		List<Team> testGetByLeague = List.of(new Team(1, "Blackburn Rovers", 1875, 38000, "Championship"));
		String testGetByLeagueJSON = this.mapper.writeValueAsString(testGetByLeague);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testGetByLeagueJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
		
	}
	
}
