package br.com.compass.spring3.sprintapi.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.compass.spring3.sprintapi.entities.State;

public class StateDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	@Size(min = 4, max = 150, message = "O onome deve conter de 5 a 150 caracteres")
	@NotBlank(message = "Campo Obrigatório")
	private String name;
	@Size(min = 4, max = 50, message = "O onome deve conter de 5 a 50 caracteres")
	@NotBlank(message = "Campo Obrigatório para região")
	private String region;
	private int population;
	@Size(min = 5, max = 150, message = "O onome deve conter de 5 a 150 caracteres")
	@NotBlank(message = "Campo Obrigatório")
	private String capital;
	@Positive(message = "Area deve ser um valor positivo")
	private Double area;

	private LocalDate foundationDate;
	private int timeSinceFoundation;

	public StateDto() {
	}

	public StateDto(Long id, String name, String region, int population, String capital, Double area,
			LocalDate foundationDate, int timesSinceFoundation) {
		this.id = id;
		this.name = name;
		this.region = region;
		this.population = population;
		this.capital = capital;
		this.area = area;
		this.foundationDate = foundationDate;
		this.timeSinceFoundation = timesSinceFoundation;
	}

	public StateDto(State entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.region = entity.getRegion();
		this.population = entity.getPopulation();
		this.capital = entity.getCapital();
		this.area = entity.getArea();
		this.foundationDate = entity.getFoundationDate();
		this.timeSinceFoundation = entity.getTimeSinceFoundation();
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
}
