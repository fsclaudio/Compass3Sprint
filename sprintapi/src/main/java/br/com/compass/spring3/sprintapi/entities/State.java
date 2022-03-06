package br.com.compass.spring3.sprintapi.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_state")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String region;
	private int population;
	private String capital;
	private Double area;
	
	private LocalDate foundationDate;
	private int timeSinceFoundation;
	
	public State() {
	}
	
	public State(Long id, String name, String region, int population, String capital, 
			Double area, LocalDate foundationDate,int timesSinceFoundation) {
		this.id = id;
		this.name = name;
		this.region = region;
		this.population = population;
		this.capital = capital;
		this.area = area;
		this.foundationDate= foundationDate;
		this.timeSinceFoundation= timesSinceFoundation;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public LocalDate getFoundationDate() {
		return foundationDate;
	}

	public void setFoundationDate(LocalDate foundationDate) {
		this.foundationDate = foundationDate;
	}

	public int getTimeSinceFoundation() {
		return timeSinceFoundation;
	}

	public void setTimeSinceFoundation(int timeSinceFoundation) {
		this.timeSinceFoundation = timeSinceFoundation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	} 
	
}
