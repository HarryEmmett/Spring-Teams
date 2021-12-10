package com.bae.teams.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // tells h2 that this is going to be a data base
public class Team {
	
	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Integer id;
	
	@Column(nullable = false) //not null has to be entered
	private String name;
	
//	@Column(unique = true) //each value has to be unique
	private Integer yearFounded;
	
	private Integer stadiumCapacity;
	
	private String league;
	
	public Team(Integer id, String name, Integer yearFounded, Integer stadiumCapacity, String league) {
		super();
		this.id = id;
		this.name = name;
		this.yearFounded = yearFounded;
		this.stadiumCapacity = stadiumCapacity;
		this.league = league;
	}
	
	public Team() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYearFounded() {
		return yearFounded;
	}

	public void setYearFounded(Integer yearFounded) {
		this.yearFounded = yearFounded;
	}

	public Integer getStadiumCapacity() {
		return stadiumCapacity;
	}

	public void setStadiumCapacity(Integer stadiumCapacity) {
		this.stadiumCapacity = stadiumCapacity;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", yearFounded=" + yearFounded + ", stadiumCapacity=" + stadiumCapacity
				+ ", league=" + league + "]";
	}

}
