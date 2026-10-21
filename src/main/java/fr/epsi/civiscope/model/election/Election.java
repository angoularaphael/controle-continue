package fr.epsi.civiscope.model.election;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "election")
public class Election {

	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;

	private String code;

	private LocalDate date;

	@Enumerated(EnumType.STRING)
	private TypeElection typeElection;

	@Enumerated(EnumType.STRING)
	private TourElection tourElection;

	private String codePays;

	public Election() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public TypeElection getTypeElection() {
		return typeElection;
	}

	public void setTypeElection(TypeElection typeElection) {
		this.typeElection = typeElection;
	}

	public TourElection getTourElection() {
		return tourElection;
	}

	public void setTourElection(TourElection tourElection) {
		this.tourElection = tourElection;
	}

	public String getCodePays() {
		return codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

}
