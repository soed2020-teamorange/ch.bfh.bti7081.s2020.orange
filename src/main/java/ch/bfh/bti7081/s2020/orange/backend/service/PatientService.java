package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.repositories.PatientRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

  private final PatientRepository patientRepository;

  public Patient createPatient(String email, String passwordHash, String firstName,
      String lastName) {
    Patient patient = new Patient(email, passwordHash, firstName, lastName);

    return this.patientRepository.save(patient);
  }

  public Patient savePatient(Patient p) {
    p.setRole(Role.PATIENT);

    return this.patientRepository.save(p);
  }

  public Patient getPatient(long id) {

    return this.patientRepository.findById(id).get();
  }

  public List<Patient> getPatientsByMedicalSpecialist(MedicalSpecialist ms) {
    return this.patientRepository.findByMedicalSpecialist(ms);
  }

  public List<Patient> getAllPatients() {

    return this.patientRepository.findAll();
  }

  public void deletePatient(long id) {
    this.patientRepository.deleteById(id);
  }

}
