package br.com.compass.spring3.sprintapi.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.compass.spring3.sprintapi.dto.StateDto;
import br.com.compass.spring3.sprintapi.services.StateService;

@RestController
@RequestMapping(value = "/api/states")
public class StateController {

	@Autowired
	private StateService service;

	@GetMapping
	public ResponseEntity<Page<StateDto>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "region") String orderBy

	) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage,
				org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);

		Page<StateDto> list = service.findAllPaged(pageRequest);

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/region/{region}")
	public ResponseEntity<List<StateDto>> stateSearchByRegion(@PathVariable String region) {

		List<StateDto> list = service.stateByregion(region);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/population/{population}")
	public ResponseEntity<List<StateDto>> stateSearchBypopulation(@PathVariable int population) {

		List<StateDto> list = service.stateByPopulation(population);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/area/{area}")
	public ResponseEntity<List<StateDto>> stateSearchByArea(@PathVariable double area) {

		List<StateDto> list = service.stateByArea(area);
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<StateDto> findById(@PathVariable Long id) {
		StateDto dto = service.findById(id);

		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<StateDto> Insert(@RequestBody @Valid StateDto dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<StateDto> update(@PathVariable Long id, @RequestBody @Valid StateDto dto) {
		dto = service.update(id, dto);

		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<StateDto> delete(@PathVariable Long id) {
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
