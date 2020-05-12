package ch.bfh.bti7081.s2020.orange;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.repositories.UserRepository;
import ch.bfh.bti7081.s2020.orange.backend.service.MedicalSpecialistService;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@RequiredArgsConstructor
public class Application extends SpringBootServletInitializer {

  private final PatientService patientService;
  private final MedicalSpecialistService medicalSpecialistService;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner demo() {
    return (args) -> {
      Patient patient = patientService
          .createPatient("patient@pms.ch", passwordEncoder.encode("1234"), "Patient", "Tester");

      MedicalSpecialist specialist = medicalSpecialistService
          .createMedicalSpecialist("specialist@pms.ch", passwordEncoder.encode("1234"),
              "Specialist", "Tester");

      patient.setMedicalSpecialist(specialist);
      patientService.savePatient(patient);

      specialist.setPatients(Arrays.asList(patient));
      medicalSpecialistService.saveMedicalSpecialist(specialist);
    };
  }
}
