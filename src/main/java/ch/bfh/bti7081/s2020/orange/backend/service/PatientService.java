package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.model.Patient;
import ch.bfh.bti7081.s2020.orange.backend.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

  private final PatientRepository patientRepository;

  public Patient createPatient(String firstName, String lastName) {
    Patient patient = new Patient();
    patient.setFirstName(firstName);
    patient.setLastName(lastName);

    long patientId = this.patientRepository.save(patient).getId();

    return this.patientRepository.findById(patientId).get();
  }

  public Patient createPatient(Patient patient) {
    return this.patientRepository.save(patient);
  }

  public Patient getPatient(long id) {
    return this.patientRepository.findById(id).get();
  }

  public void deletePatient(long id) {
    this.patientRepository.deleteById(id);
  }
}
