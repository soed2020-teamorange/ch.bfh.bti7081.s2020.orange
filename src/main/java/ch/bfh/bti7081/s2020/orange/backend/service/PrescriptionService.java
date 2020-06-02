package ch.bfh.bti7081.s2020.orange.backend.service;

import org.springframework.stereotype.Service;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Prescription;
import ch.bfh.bti7081.s2020.orange.backend.repositories.PrescriptionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
	
	private final PrescriptionRepository prescriptionRepository;
	
	public Prescription getById(Long prescriptionId) {
		return prescriptionRepository.findById(prescriptionId).get();
	}
	
	public Prescription savePrescription(Prescription p) {
		return prescriptionRepository.save(p);
	}
	
	public void deletePrescription(Prescription p) {
		prescriptionRepository.delete(p);
	}
	
	public void deletePrescription(Long id) {
		prescriptionRepository.deleteById(id);
	}
}