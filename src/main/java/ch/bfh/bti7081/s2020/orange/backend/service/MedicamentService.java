package ch.bfh.bti7081.s2020.orange.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Medicament;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MedicamentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicamentService {
	
	public final MedicamentRepository medicamentRepository;
	
	public Medicament getById(Long medId) {
		return medicamentRepository.findById(medId).get();
	}
	
	public List<Medicament> getAllMedicaments() {
		return medicamentRepository.findAll();
	}
	
	public List<Medicament> findByNameLike(String medicamentName) {
		return medicamentRepository.findByNameContainingIgnoreCase(medicamentName);
	}
}
