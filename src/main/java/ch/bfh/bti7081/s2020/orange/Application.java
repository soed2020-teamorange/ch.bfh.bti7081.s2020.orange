package ch.bfh.bti7081.s2020.orange;

import ch.bfh.bti7081.s2020.orange.backend.model.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.model.Patient;
import ch.bfh.bti7081.s2020.orange.backend.repository.MedicalSpecialistRepository;
import ch.bfh.bti7081.s2020.orange.backend.repository.PatientRepository;
import ch.bfh.bti7081.s2020.orange.backend.service.MedicalSpecialistService;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@RequiredArgsConstructor
public class Application extends SpringBootServletInitializer {

  private final PatientService patientService;
  private final PatientRepository patientRepository;
  private final MedicalSpecialistService medicalSpecialistService;
  private final MedicalSpecialistRepository medicalSpecialistRepository;
  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner demo() {
    return (args) -> {
      // create a few patients
      this.patientService.createPatient("Hans", "Steiner");
      this.patientService.createPatient("Martha", "Hildegaard");
      this.patientService.createPatient("Anton", "Gerber");

      // create a few medicalSpecialists
      this.medicalSpecialistService.createMedicalSpecialist("Troy", "Holland");
      this.medicalSpecialistService.createMedicalSpecialist("Anna", "Edwards");
      this.medicalSpecialistService.createMedicalSpecialist("Lauren", "Campbell");

      // link patients with medicalSpecialists
      Patient p = this.patientService.getPatientByLastName("Steiner");
      MedicalSpecialist ms = this.medicalSpecialistService
              .getMedicalSpecialistByLastName("Holland");
      p.setMedicalSpecialist(ms);

      // fetch all patients
      log.info("Patients found with findAll():");
      log.info("-------------------------------");
      for (Patient patient : patientRepository.findAll()) {
        log.info(patient.toString());
      }
      log.info("");
    };
  }
}