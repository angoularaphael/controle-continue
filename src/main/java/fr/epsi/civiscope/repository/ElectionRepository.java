package fr.epsi.civiscope.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.civiscope.model.election.Election;
import fr.epsi.civiscope.model.election.TourElection;
import fr.epsi.civiscope.model.election.TypeElection;

@Repository
public interface ElectionRepository extends JpaRepository<Election, UUID> {

	boolean existsByCode(String code);

	boolean existsByCodeAndIdNot(String code, UUID id);

	boolean existsByDateBetweenAndTypeElectionAndTourElection(
			LocalDate start,
			LocalDate end,
			TypeElection typeElection,
			TourElection tourElection);

	boolean existsByDateBetweenAndTypeElectionAndTourElectionAndIdNot(
			LocalDate start,
			LocalDate end,
			TypeElection typeElection,
			TourElection tourElection,
			UUID id);

}
