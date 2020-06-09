package ch.bfh.bti7081.s2020.orange.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Medicament;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {

	public List<Medicament> findByNameContainingIgnoreCase(String name);
}
