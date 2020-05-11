package ch.bfh.bti7081.s2020.orange;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MedicalSpecialistRepository;
import ch.bfh.bti7081.s2020.orange.backend.repositories.PatientRepository;
import ch.bfh.bti7081.s2020.orange.backend.repositories.UserRepository;
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

  //  private final PatientService patientService;
  private final PatientRepository patientRepository;
  //  private final MedicalSpecialistService medicalSpecialistService;
  private final MedicalSpecialistRepository medicalSpecialistRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public CommandLineRunner demo() {
    return (args) -> {

      Patient patient = new Patient();
      patient.setFirstName("Patient");
      patient.setLastName("Patient");
      patient.setEmail("patient@pms-orange.ch");
      patient.setPasswordHash(passwordEncoder.encode("1234"));
      patient.setRole(Role.PATIENT);
      Long patientId = patientRepository.save(patient).getId();

      MedicalSpecialist specialist = new MedicalSpecialist();
      specialist.setFirstName("Specialist");
      specialist.setLastName("Specialist");
      specialist.setEmail("specialist@@pms-orange.ch");
      specialist.setPasswordHash(passwordEncoder.encode("1234"));
      specialist.setRole(Role.MEDICAL_SPECIALIST);
      Long specialistId = medicalSpecialistRepository.save(specialist).getId();

      patient.setMedicalSpecialist(specialist);
      patientRepository.save(patient);

      specialist.setPatients(Arrays.asList(patient));
      medicalSpecialistRepository.save(specialist);

//      User user = new User("test@test.ch", passwordEncoder.encode("1234"), "Test", "Tester",
//          Role.PATIENT);
//      this.userRepository.save(user);

      // create a few patients
//      this.patientService.createPatient("Hans", "Steiner");
//      this.patientService.createPatient("Martha", "Hildegaard");
//      this.patientService.createPatient("Anton", "Gerber");
//
//      // create a few medicalSpecialists
//      this.medicalSpecialistService.createMedicalSpecialist("Troy", "Holland");
//      this.medicalSpecialistService.createMedicalSpecialist("Anna", "Edwards");
//      this.medicalSpecialistService.createMedicalSpecialist("Lauren", "Campbell");
//
//      // link patients with medicalSpecialists
//      Patient p = this.patientService.getPatientByLastName("Steiner");
//      MedicalSpecialist ms = this.medicalSpecialistService
//          .getMedicalSpecialistByLastName("Holland");
//      p.setMedicalSpecialist(ms);
//
//      // fetch all patients
//      log.info("Patients found with findAll():");
//      log.info("-------------------------------");
//      for (Patient patient : patientRepository.findAll()) {
//        log.info(patient.toString());
//      }
//      log.info("");
    };
  }
}
