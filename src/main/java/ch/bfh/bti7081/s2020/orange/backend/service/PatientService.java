package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.repositories.PatientRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

  private final PatientRepository patientRepository;
  private final PasswordEncoder passwordEncoder;

  public Patient createPatient(String email, String password, String firstName,
      String lastName) {
    Patient patient = new Patient(email, passwordEncoder.encode(password), firstName, lastName);

    this.patientRepository.save(patient);

    return patient;
  }

  public Patient getPatient(long id) {

    return this.patientRepository.findById(id).get();
  }

  public List<Patient> getAllPatients() {

    return this.patientRepository.findAll();
  }

  public Patient getPatientByLastName(String lastName) {
    return (Patient) this.patientRepository.findByLastName(lastName);
  }

  public void deletePatient(long id) {
    this.patientRepository.deleteById(id);
  }

}
