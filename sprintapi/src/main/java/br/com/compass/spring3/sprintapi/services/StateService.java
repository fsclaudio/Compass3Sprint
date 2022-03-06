package br.com.compass.spring3.sprintapi.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.compass.spring3.sprintapi.dto.StateDto;
import br.com.compass.spring3.sprintapi.entities.State;
import br.com.compass.spring3.sprintapi.repositories.StateRepository;
import br.com.compass.spring3.sprintapi.services.exceptions.DatabaseException;
import br.com.compass.spring3.sprintapi.services.exceptions.ResourceNotFoundException;

@Service
public class StateService {

	@Autowired
	private StateRepository repository;

	public List<State> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Page<StateDto> findAllPaged(PageRequest pageRequest) {
		Page<State> list = repository.findAll(pageRequest);

		return list.map(x -> new StateDto(x));
	}

	@Transactional(readOnly = true)
	public StateDto findById(Long id) {
		Optional<State> obj = repository.findById(id);
		State entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));

		return new StateDto(entity);
	}

	@Transactional(readOnly = true)
	public List<StateDto> stateByregion(String region) {

		return repository.findByRegionEquals(region);
	}

	@Transactional(readOnly = true)
	public List<StateDto> stateByPopulation(int population) {

		return repository.findByPopulationGreaterThanOrderByPopulation(population);
	}

	@Transactional(readOnly = true)
	public List<StateDto> stateByArea(double area) {

		return repository.findByAreaLargeWhat(area);
	}

	@Transactional
	public StateDto insert(StateDto dto) {
		State entity = new State();

		if (found(dto) == false) {
			throw new ResourceNotFoundException("a idade calculada: " + anos(dto.getFoundationDate())
					+ " é diferente da Lançada: " + dto.getTimeSinceFoundation());
		}
		if (coparation(dto) == false) {
			throw new ResourceNotFoundException(
					"a região diferente do cadastro (Norte, Nordeste, Centro Oeste, Sudeste e Sul) Você passou: "
							+ dto.getRegion());
		}
		if (!futereDate(dto)) {
			throw new ResourceNotFoundException("Data de Fundação não pode ser futura");
		}
		entity.setName(dto.getName());
		entity.setRegion(dto.getRegion());
		entity.setCapital(dto.getCapital());
		entity.setPopulation(dto.getPopulation());
		entity.setArea(dto.getArea());
		entity.setFoundationDate(dto.getFoundationDate());
		entity.setTimeSinceFoundation(dto.getTimeSinceFoundation());
		entity = repository.save(entity);

		return new StateDto(entity);

	}

	@Transactional
	public StateDto update(Long id, StateDto dto) {
		try {

			if (found(dto) == false) {
				throw new ResourceNotFoundException("a idade calculada: " + anos(dto.getFoundationDate())
						+ " é diferente da Lançada: " + dto.getTimeSinceFoundation());
			}
			if (coparation(dto) == false) {
				throw new ResourceNotFoundException(
						"a região diferente do cadastro (Norte, Nordeste, Centro Oeste, Sudeste e Sul) Você passou: "
								+ dto.getRegion());
			}
			if (!futereDate(dto)) {
				throw new ResourceNotFoundException("Data de Fundação não pode ser futura");
			}
			State entity = repository.getById(id);
			entity.setName(dto.getName());
			entity.setRegion(dto.getRegion());
			entity.setPopulation(dto.getPopulation());
			entity.setCapital(dto.getCapital());
			entity.setArea(dto.getArea());
			entity.setFoundationDate(dto.getFoundationDate());
			entity.setTimeSinceFoundation(dto.getTimeSinceFoundation());
			entity = repository.save(entity);
			return new StateDto(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	public static int anos(final LocalDate data) {
		final LocalDate dataAtual = LocalDate.now();
		final Period periodo = Period.between(data, dataAtual);
		return periodo.getYears();
	}

	public boolean found(StateDto dto) {
		int idade = anos(dto.getFoundationDate());
		int anosDigitado = dto.getTimeSinceFoundation();
		if (idade != anosDigitado) {
			return false;
		} else
			return true;
	}

	public boolean futereDate(StateDto dto) {
		final LocalDate dataAtual = LocalDate.now();
		final LocalDate dataDigitado = dto.getFoundationDate();
		if (dataAtual.isBefore(dataDigitado)) {
			return false;
		} else
			return true;
	}

	private boolean coparation(StateDto dto) {
		if (dto.getRegion().compareToIgnoreCase("NORTE") == 0 || dto.getRegion().compareToIgnoreCase("NORDESTE") == 0
				|| dto.getRegion().compareToIgnoreCase("CENTRO OESTE") == 0
				|| dto.getRegion().compareToIgnoreCase("SUDESTE") == 0
				|| dto.getRegion().compareToIgnoreCase("SUL") == 0) {
			return true;
		} else
			return false;
	}
}
