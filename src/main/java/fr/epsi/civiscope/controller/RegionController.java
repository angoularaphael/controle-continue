package fr.epsi.civiscope.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.epsi.civiscope.model.geo.Region;
import fr.epsi.civiscope.service.RegionService;

@RequestMapping("/regions")
@RestController
public class RegionController {

	private final RegionService regionService;

	public RegionController(RegionService regionService) {
		this.regionService = regionService;
	}

	@GetMapping
	public List<Region> findAll() {
		return this.regionService.findAll();
	}

	@GetMapping("/{code}")
	public Region findByCode(@PathVariable String code) {
		return this.regionService.findByCode(code);
	}

	@PostMapping
	public ResponseEntity<Region> addRegion(@RequestBody Region region) {
		Region saved = this.regionService.addRegion(region);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping
	public ResponseEntity<Region> updateRegion(@RequestBody Region region) {
		Region updated = this.regionService.updateRegion(region);
		if (Objects.nonNull(updated)) {
			return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping
	public ResponseEntity<Region> deleteRegion(@RequestBody Region region) {
		Region deleted = this.regionService.deleteRegion(region);
		if (Objects.nonNull(deleted)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
