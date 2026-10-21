package fr.epsi.civiscope.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.epsi.civiscope.model.geo.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, UUID> {

	Optional<Region> findByCode(String code);

}
