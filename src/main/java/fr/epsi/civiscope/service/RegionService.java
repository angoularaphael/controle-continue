package fr.epsi.civiscope.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.epsi.civiscope.model.geo.Region;
import fr.epsi.civiscope.repository.RegionRepository;

@Service
public class RegionService {

	private final RegionRepository regionRepository;

	public RegionService(RegionRepository regionRepository) {
		this.regionRepository = regionRepository;
	}

	public List<Region> findAll() {
		return this.regionRepository.findAll();
	}

	public Region findByCode(String code) {
		Optional<Region> optional = this.regionRepository.findByCode(code);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Region addRegion(Region region) {
		return this.regionRepository.save(region);
	}

	public Region updateRegion(Region region) {
		Optional<Region> optionalRegion = this.regionRepository.findByCode(region.getCode());
		if (optionalRegion.isPresent()) {
			Region regionToUpdate = optionalRegion.get();
			regionToUpdate.setNom(region.getNom());
			return this.regionRepository.save(regionToUpdate);
		}
		return null;
	}

	public Region deleteRegion(Region region) {
		Optional<Region> optionalRegion = this.regionRepository.findByCode(region.getCode());
		if (optionalRegion.isPresent()) {
			Region regionToDelete = optionalRegion.get();
			this.regionRepository.delete(regionToDelete);
			return regionToDelete;
		}
		return null;
	}

}
