package ch.bfh.bti7081.s2020.orange;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MessageState;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.repositories.ChatRepository;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MessageRepository;
import ch.bfh.bti7081.s2020.orange.backend.service.MedicalSpecialistService;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import java.time.LocalDateTime;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@RequiredArgsConstructor
public class Application extends SpringBootServletInitializer {

  private final PatientService patientService;
  private final MedicalSpecialistService medicalSpecialistService;
  private final PasswordEncoder passwordEncoder;
  private static final Logger log = LoggerFactory.getLogger(Application.class);
  private final ChatRepository chatRepository;
  private final MessageRepository messageRepository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  // TODO move
  @Bean
  UnicastProcessor<Message> publisher() {
    return UnicastProcessor.create();
  }

  // TODO move
  @Bean
  Flux<Message> messages(UnicastProcessor<Message> publisher) {
    return publisher.replay(0).autoConnect();
  }

  @Bean
  public CommandLineRunner demo() {
    return (args) -> {
      Patient patient = patientService
          .createPatient("patient@pms.ch", passwordEncoder.encode("1234"), "Patient", "Tester");
      Patient patient2 = patientService
          .createPatient("patient2@pms.ch", passwordEncoder.encode("1234"), "Patient2", "Tester");
      Patient patient3 = patientService
          .createPatient("patient3@pms.ch", passwordEncoder.encode("1234"), "Patient3", "Tester");

      MedicalSpecialist specialist = medicalSpecialistService
          .createMedicalSpecialist("specialist@pms.ch", passwordEncoder.encode("1234"),
              "Specialist", "Tester");

      patient.setMedicalSpecialist(specialist);
      patientService.savePatient(patient);

      specialist.setPatients(Arrays.asList(patient, patient3));
      medicalSpecialistService.saveMedicalSpecialist(specialist);

      Chat chat = new Chat(Arrays.asList(), patient, specialist);
      this.chatRepository.save(chat);

      Message message = new Message("Testnachricht", LocalDateTime.now(), MessageState.UNREAD,
          patient, chat);

      this.messageRepository.save(message);

      System.out.println(this.messageRepository.findAllByChatId(chat.getId()).size());
      System.out.println("ChatId: " + chat.getId());
    };
  }
}
