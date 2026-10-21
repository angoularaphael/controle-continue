package fr.epsi.civiscope.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fr.epsi.civiscope.model.election.Election;
import fr.epsi.civiscope.repository.ElectionRepository;

@Service
public class ElectionService {

	private final ElectionRepository electionRepository;

	public ElectionService(ElectionRepository electionRepository) {
		this.electionRepository = electionRepository;
	}

	public List<Election> findAll(Integer limit) {
		Sort tri = Sort.by(Sort.Direction.DESC, "date");
		if (limit == null) {
			return this.electionRepository.findAll(tri);
		}
		return this.electionRepository.findAll(PageRequest.of(0, limit, tri)).getContent();
	}

	public Optional<Election> findById(UUID id) {
		return this.electionRepository.findById(id);
	}

	public boolean isValid(Election election) {
		if (election == null) {
			return false;
		}
		if (election.getCode() == null || election.getCode().isEmpty()) {
			return false;
		}
		if (election.getDate() == null) {
			return false;
		}
		if (election.getTypeElection() == null) {
			return false;
		}
		return true;
	}

	public boolean codeDejaUtilise(String code) {
		return this.electionRepository.existsByCode(code);
	}

	public boolean codeDejaUtiliseParAutre(String code, UUID id) {
		return this.electionRepository.existsByCodeAndIdNot(code, id);
	}

	public boolean memeAnneeTypeEtTour(Election election) {
		int annee = election.getDate().getYear();
		LocalDate debut = LocalDate.of(annee, 1, 1);
		LocalDate fin = LocalDate.of(annee, 12, 31);
		return this.electionRepository.existsByDateBetweenAndTypeElectionAndTourElection(
				debut,
				fin,
				election.getTypeElection(),
				election.getTourElection());
	}

	public boolean memeAnneeTypeEtTourSansId(Election election, UUID id) {
		int annee = election.getDate().getYear();
		LocalDate debut = LocalDate.of(annee, 1, 1);
		LocalDate fin = LocalDate.of(annee, 12, 31);
		return this.electionRepository.existsByDateBetweenAndTypeElectionAndTourElectionAndIdNot(
				debut,
				fin,
				election.getTypeElection(),
				election.getTourElection(),
				id);
	}

	public Election addElection(Election election) {
		return this.electionRepository.save(election);
	}

	public Optional<Election> updateElection(UUID id, Election election) {
		Optional<Election> optional = this.electionRepository.findById(id);
		if (!optional.isPresent()) {
			return Optional.empty();
		}
		Election enBase = optional.get();
		enBase.setCode(election.getCode());
		enBase.setDate(election.getDate());
		enBase.setTypeElection(election.getTypeElection());
		enBase.setTourElection(election.getTourElection());
		enBase.setCodePays(election.getCodePays());
		return Optional.of(this.electionRepository.save(enBase));
	}

	public Optional<Election> deleteElection(UUID id) {
		Optional<Election> optional = this.electionRepository.findById(id);
		if (!optional.isPresent()) {
			return Optional.empty();
		}
		Election aSupprimer = optional.get();
		this.electionRepository.delete(aSupprimer);
		return Optional.of(aSupprimer);
	}

}
