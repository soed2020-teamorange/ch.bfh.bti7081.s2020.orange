package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodDiary;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MoodDiaryRepository;
import ch.bfh.bti7081.s2020.orange.backend.repositories.PatientRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

  private final PatientRepository patientRepository;
  private final MoodDiaryRepository moodDiaryRepository;

  public Patient createPatient(String email, String passwordHash, String firstName,
      String lastName, LocalDate birthDate) {
    Patient patient = new Patient(email, passwordHash, firstName, lastName, birthDate);
    
    return createPatient(patient);
  }

  public Patient createPatient(String email, String passwordHash, String firstName,
      String lastName, LocalDate birthDate, MedicalSpecialist medicalSpecialist) {
    Patient patient = new Patient(email, passwordHash, firstName, lastName, birthDate);
    patient.setMedicalSpecialist(medicalSpecialist);

    return createPatient(patient);
  }

  private Patient createPatient(Patient patient) {
    Patient savedPatient = this.patientRepository.save(patient);

    MoodDiary moodDiary = new MoodDiary();
    moodDiary.setPatient(savedPatient);
    moodDiaryRepository.save(moodDiary);

    return savedPatient;
  }

  public Patient updatePatient(Patient p) {
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
