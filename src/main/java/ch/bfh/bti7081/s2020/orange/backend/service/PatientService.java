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

    ActivityDiary activityDiary = new ActivityDiary();
    activityDiary.setPatient(savedPatient);
    activityDiaryRepository.save(activityDiary);

    Chat chat = Chat.builder().patient(savedPatient)
        .medicalSpecialist(savedPatient.getMedicalSpecialist())
        .build();
    chatRepository.save(chat);

    Message welcomeMessage = Message.builder().chat(chat).content(
        String.format(
            "Herzlich Willkommen %s! Sie können mir über den Chat jederzeit schreiben und ich werde Ihnen schnellst möglich antworten.",
            savedPatient.getFirstName())).creationDate(LocalDateTime.now())
        .sender(patient.getMedicalSpecialist()).state(
            MessageState.UNREAD).build();
    messageRepository.save(welcomeMessage);

    return savedPatient;
  }

  public Patient updatePatient(Patient p) {
    p.setRole(Role.PATIENT);

    return this.patientRepository.save(p);
  }

}
