package ch.bfh.bti7081.s2020.orange.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	
	List<Prescription> findAllByPatientId(Long patientId);
}
