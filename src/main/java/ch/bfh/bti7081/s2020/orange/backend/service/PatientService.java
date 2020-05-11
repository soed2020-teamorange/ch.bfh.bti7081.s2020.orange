package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.model.Patient;
import ch.bfh.bti7081.s2020.orange.backend.repository.PatientRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

  private final PatientRepository patientRepository;

  public Patient createPatient(String firstName, String lastName) {
    Patient patient = new Patient(firstName, lastName);

    this.patientRepository.save(patient);

    return patient;
  }

  public Patient createPatient(Patient patient) {

    return this.patientRepository.save(patient);
  }

  public Patient getPatient(long id) {

    return this.patientRepository.findById(id).get();
  }

  public List<Patient> getAllPatients() {

    return (List<Patient>) this.patientRepository.findAll();
  }

  public Patient getPatientByLastName(String lastName) {
    return this.patientRepository.findByLastName(lastName);
  }

  public void deletePatient(long id) {
    this.patientRepository.deleteById(id);
  }

}
