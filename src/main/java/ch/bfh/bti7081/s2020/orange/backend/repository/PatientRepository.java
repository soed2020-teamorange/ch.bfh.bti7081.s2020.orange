package ch.bfh.bti7081.s2020.orange.backend.repository;

import ch.bfh.bti7081.s2020.orange.backend.model.Patient;
import javax.transaction.Transactional;

@Transactional
public interface PatientRepository extends PersonBaseRepository<Patient> {

}
