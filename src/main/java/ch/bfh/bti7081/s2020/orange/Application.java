package ch.bfh.bti7081.s2020.orange;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import ch.bfh.bti7081.s2020.orange.backend.data.Mood;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Medicament;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodDiary;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodEntry;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.repositories.ChatRepository;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MedicamentRepository;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MessageRepository;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MoodDiaryRepository;
import ch.bfh.bti7081.s2020.orange.backend.service.MedicalSpecialistService;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@RequiredArgsConstructor
public class Application extends SpringBootServletInitializer implements HasLogger {

  private final PatientService patientService;
  private final MedicalSpecialistService medicalSpecialistService;
  private final PasswordEncoder passwordEncoder;
  private final ChatRepository chatRepository;
  private final MessageRepository messageRepository;
  private final MoodDiaryRepository moodDiaryRepository;
  private final MedicamentRepository medicamentRepository;

  public static void main(final String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  UnicastProcessor<Message> publisher() {
    return UnicastProcessor.create();
  }

  @Bean
  Flux<Message> messages(final UnicastProcessor<Message> publisher) {
    return publisher.replay(0).autoConnect();
  }

  @Bean
  public CommandLineRunner demo() {
    return (args) -> {
      final MedicalSpecialist specialist = this.medicalSpecialistService
          .createMedicalSpecialist("specialist@pms.ch",
              this.passwordEncoder.encode("1234"),
              "Specialist", "Tester", LocalDate.now().minusDays(120));
      specialist.setPhone("+41791234567");
      this.medicalSpecialistService.saveMedicalSpecialist(specialist);

      final MedicalSpecialist specialist2 = this.medicalSpecialistService
          .createMedicalSpecialist("specialist2@pms.ch",
              this.passwordEncoder.encode("1234"),
              "Specialist2", "Tester2", LocalDate.now().minusDays(150));

      final Patient patient = this.patientService
          .createPatient("patient@pms.ch", this.passwordEncoder.encode("1234"), "Patient",
              "Tester", LocalDate.now().minusDays(1500), specialist);

      final MoodDiary moodDiary = this.moodDiaryRepository.getByPatientId(patient.getId());
      moodDiary.addEntry(
          new MoodEntry("test1", "desc1", Mood.DEPRESSED, LocalDate.now().minusDays(3),
              LocalTime.now(), 6, 1));
      moodDiary.addEntry(
          new MoodEntry("test2", "desc2", Mood.ELATED, LocalDate.now().minusDays(2),
              LocalTime.now(), 8, 4));
      moodDiary.addEntry(
          new MoodEntry("test3", "desc3", Mood.NEUTRAL, LocalDate.now().minusDays(1),
              LocalTime.now(), 7, 3));
      this.moodDiaryRepository.save(moodDiary);
      
      Medicament med1 = new Medicament("Antidepressiva 1", "Roche", 100000);
      Medicament med2 = new Medicament("Schmerzmittel X", "Bayer", 20000);
      medicamentRepository.save(med1);
      medicamentRepository.save(med2);

      final Patient patient2 = this.patientService
          .createPatient("patient2@pms.ch", this.passwordEncoder.encode("1234"), "Patient2",
              "Tester", LocalDate.now().minusDays(2000), specialist);
      final Patient patient3 = this.patientService
          .createPatient("patient3@pms.ch", this.passwordEncoder.encode("1234"), "Patient3",
              "Tester", LocalDate.now().minusDays(3550));
    };
  }
}
