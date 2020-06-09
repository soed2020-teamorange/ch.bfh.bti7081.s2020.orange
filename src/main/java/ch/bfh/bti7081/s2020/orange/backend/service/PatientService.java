package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.data.MessageState;
import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.ActivityDiary;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodDiary;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.repositories.ActivityDiaryRepository;
import ch.bfh.bti7081.s2020.orange.backend.repositories.ChatRepository;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MessageRepository;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MoodDiaryRepository;
import ch.bfh.bti7081.s2020.orange.backend.repositories.PatientRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

  private final PatientRepository patientRepository;
  private final MoodDiaryRepository moodDiaryRepository;
  private final ActivityDiaryRepository activityDiaryRepository;
  private final ChatRepository chatRepository;
  private final MessageRepository messageRepository;

  public Patient createPatient(final String email, final String passwordHash,
      final String firstName,
      final String lastName, final LocalDate birthDate) {
    final Patient patient = new Patient(email, passwordHash, firstName, lastName, birthDate);

    return this.createPatient(patient);
  }

  public Patient createPatient(final String email, final String passwordHash,
      final String firstName,
      final String lastName, final LocalDate birthDate, final MedicalSpecialist medicalSpecialist) {
    final Patient patient = new Patient(email, passwordHash, firstName, lastName, birthDate);
    patient.setMedicalSpecialist(medicalSpecialist);

    return this.createPatient(patient);
  }

  private Patient createPatient(final Patient patient) {
    final Patient savedPatient = patientRepository.save(patient);

    final MoodDiary moodDiary = new MoodDiary();
    moodDiary.setPatient(savedPatient);
    this.moodDiaryRepository.save(moodDiary);

    final ActivityDiary activityDiary = new ActivityDiary();
    activityDiary.setPatient(savedPatient);
    this.activityDiaryRepository.save(activityDiary);

    final Chat chat = Chat.builder().patient(savedPatient)
        .medicalSpecialist(savedPatient.getMedicalSpecialist())
        .build();
    this.chatRepository.save(chat);

    final Message welcomeMessage = Message.builder().chat(chat).content(
        String.format(
            "Herzlich Willkommen %s! Sie können mir über den Chat jederzeit schreiben und ich werde Ihnen schnellst möglich antworten.",
            savedPatient.getFirstName())).creationDate(LocalDateTime.now())
        .sender(patient.getMedicalSpecialist()).state(
            MessageState.UNREAD).build();
    this.messageRepository.save(welcomeMessage);

    return savedPatient;
  }

  public Patient updatePatient(final Patient p) {
    p.setRole(Role.PATIENT);

    return patientRepository.save(p);
  }

}
