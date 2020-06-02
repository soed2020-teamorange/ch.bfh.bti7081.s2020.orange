package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescription extends AbstractEntity {

	private LocalDate validFrom;
	
	private LocalDate validUntil;
	
	private LocalDate startingOn;
	
	@OneToOne
	private Medicament medicament;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Dose> dosages;
}
