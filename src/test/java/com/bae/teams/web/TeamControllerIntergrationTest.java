package com.bae.teams.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.bae.teams.domain.Team;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
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
		
		Team testCreatedTeam = new Team(1, "Blackburn", 1875, 38000, "Championship");
		String testCreatedTeamAsJson = this.mapper.writeValueAsString(testCreatedTeam);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedTeamAsJson);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

}
