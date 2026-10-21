package fr.epsi.civiscope.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
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

import fr.epsi.civiscope.model.election.Election;
import fr.epsi.civiscope.service.ElectionService;

@RequestMapping("/elections")
@RestController
public class ElectionController {

	private final ElectionService electionService;

	public ElectionController(ElectionService electionService) {
		this.electionService = electionService;
	}

	@GetMapping
	public ResponseEntity<List<Election>> findAll(@RequestParam(required = false) Integer limit) {
		if (limit != null && limit <= 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(this.electionService.findAll(limit));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Election> findById(@PathVariable UUID id) {
		Optional<Election> optional = this.electionService.findById(id);
		if (!optional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(optional.get());
	}

	@PostMapping
	public ResponseEntity<Election> addElection(@RequestBody Election election) {
		if (!this.electionService.isValid(election)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if (this.electionService.codeDejaUtilise(election.getCode())) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		if (this.electionService.memeAnneeTypeEtTour(election)) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		Election saved = this.electionService.addElection(election);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Election> updateElection(@PathVariable UUID id, @RequestBody Election election) {
		if (!this.electionService.isValid(election)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Election> enBase = this.electionService.findById(id);
		if (!enBase.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if (this.electionService.codeDejaUtiliseParAutre(election.getCode(), id)) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		if (this.electionService.memeAnneeTypeEtTourSansId(election, id)) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		Optional<Election> updated = this.electionService.updateElection(id, election);
		return ResponseEntity.ok(updated.get());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Election> deleteElection(@PathVariable UUID id) {
		Optional<Election> deleted = this.electionService.deleteElection(id);
		if (deleted.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
