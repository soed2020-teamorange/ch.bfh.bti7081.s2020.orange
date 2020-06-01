package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MedicalSpecialistRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalSpecialistService {

  private final MedicalSpecialistRepository medicalSpecialistRepository;

  public MedicalSpecialist createMedicalSpecialist(String email, String passwordHash,
      String firstName, String lastName, LocalDate birthDate) {
    MedicalSpecialist medicalSpecialist = new MedicalSpecialist(email, passwordHash, firstName,
        lastName, birthDate);

    return this.medicalSpecialistRepository.save(medicalSpecialist);
  }

  public MedicalSpecialist saveMedicalSpecialist(MedicalSpecialist medicalSpecialist) {
    medicalSpecialist.setRole(Role.MEDICAL_SPECIALIST);

    return this.medicalSpecialistRepository.save(medicalSpecialist);
  }

  public List<MedicalSpecialist> getAllMedicalSpecialist() {

    return this.medicalSpecialistRepository.findAll();
  }

}
