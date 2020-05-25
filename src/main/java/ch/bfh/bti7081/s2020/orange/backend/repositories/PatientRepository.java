package ch.bfh.bti7081.s2020.orange.backend.repositories;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import java.util.List;

public interface PatientRepository extends UserBaseRepository<Patient> {

  List<Patient> findByMedicalSpecialist(MedicalSpecialist ms);

  List<Patient> findByMedicalSpecialistNull();
}
