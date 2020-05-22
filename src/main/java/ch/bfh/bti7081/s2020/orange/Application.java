package ch.bfh.bti7081.s2020.orange;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.data.MessageState;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.repositories.ChatRepository;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MessageRepository;
import ch.bfh.bti7081.s2020.orange.backend.service.MedicalSpecialistService;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import lombok.RequiredArgsConstructor;
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
public class Application extends SpringBootServletInitializer implements HasLogger {

  private final PatientService patientService;
  private final MedicalSpecialistService medicalSpecialistService;
  private final PasswordEncoder passwordEncoder;
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
      MedicalSpecialist specialist = medicalSpecialistService
          .createMedicalSpecialist("specialist@pms.ch",
              passwordEncoder.encode("1234"),
              "Specialist", "Tester", LocalDate.now().minusDays(120));

      MedicalSpecialist specialist2 = medicalSpecialistService
              .createMedicalSpecialist("specialist2@pms.ch",
                      passwordEncoder.encode("1234"),
                      "Specialist2", "Tester2", LocalDate.now().minusDays(150));

      Patient patient = patientService
          .createPatient("patient@pms.ch", passwordEncoder.encode("1234"), "Patient",
              "Tester", LocalDate.now().minusDays(1500), specialist);

      Patient patient2 = patientService
          .createPatient("patient2@pms.ch", passwordEncoder.encode("1234"), "Patient2",
              "Tester", LocalDate.now().minusDays(2000), specialist);
      Patient patient3 = patientService
          .createPatient("patient3@pms.ch", passwordEncoder.encode("1234"), "Patient3",
              "Tester", LocalDate.now().minusDays(3550));

      Chat chat = new Chat(Arrays.asList(), patient, specialist);
      this.chatRepository.save(chat);

      Message message = new Message(
          "Herzlich Willkommen im Chat! Sie können hier jederzeit eine Nachricht hinterlassen.",
          LocalDateTime.now(), MessageState.UNREAD,
          specialist, chat);

      this.messageRepository.save(message);

      System.out.println(this.messageRepository.findAllByChatId(chat.getId()).size());
      System.out.println("ChatId: " + chat.getId());
    };
  }
}
