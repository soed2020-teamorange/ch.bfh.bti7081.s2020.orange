package ch.bfh.bti7081.s2020.orange.backend.repository;

import ch.bfh.bti7081.s2020.orange.backend.model.MedicalSpecialist;
import javax.transaction.Transactional;

@Transactional
public interface MedicalSpecialistRepository extends PersonBaseRepository<MedicalSpecialist> {

}
