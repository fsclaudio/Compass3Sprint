package br.com.compass.spring3.sprintapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.compass.spring3.sprintapi.dto.StateDto;
import br.com.compass.spring3.sprintapi.entities.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

	@Query("SELECT DISTINCT obj FROM State obj  " + " WHERE  obj.area > :area  ORDER BY obj.area ")
	List<StateDto> findByAreaLargeWhat(double area);

	List<StateDto> findByRegionEquals(String region);

	List<StateDto> findByPopulationGreaterThanOrderByPopulation(int pop);
}
