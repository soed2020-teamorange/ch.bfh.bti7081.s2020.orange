package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.model.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.repository.MedicalSpecialistRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicalSpecialistService {

  private final MedicalSpecialistRepository medicalSpecialistRepository;

  public MedicalSpecialist createMedicalSpecialist(String firstName, String lastName) {
    MedicalSpecialist medicalSpecialist = new MedicalSpecialist();
    medicalSpecialist.setFirstName(firstName);
    medicalSpecialist.setLastName(lastName);

    long medicalSpecialistId = this.medicalSpecialistRepository.save(medicalSpecialist).getId();

    return this.medicalSpecialistRepository.findById(medicalSpecialistId).get();
  }

  public MedicalSpecialist createMedicalSpecialist(MedicalSpecialist medicalSpecialist) {
    return this.medicalSpecialistRepository.save(medicalSpecialist);
  }

  public MedicalSpecialist getMedicalSpecialist(long id) {
    return this.medicalSpecialistRepository.findById(id).get();
  }

  public List<MedicalSpecialist> getAllMedicalSpecialist() {

    return (List<MedicalSpecialist>) this.medicalSpecialistRepository.findAll();
  }

  public MedicalSpecialist getMedicalSpecialistByLastName(String lastName) {
    return this.medicalSpecialistRepository.findByLastName(lastName);
  }

  public void deleteMedicalSpecialist(long id) {
    this.medicalSpecialistRepository.deleteById(id);
  }

}
