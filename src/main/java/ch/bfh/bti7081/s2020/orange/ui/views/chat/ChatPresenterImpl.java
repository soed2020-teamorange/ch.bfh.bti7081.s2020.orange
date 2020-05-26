package ch.bfh.bti7081.s2020.orange.ui.views.chat;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.service.ChatService;
import ch.bfh.bti7081.s2020.orange.backend.service.MessageService;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ChatPresenterImpl implements ChatPresenter, ChatView.Observer {

  private final ChatView view;
  private final ChatService chatService;
  private final MessageService messageService;
  private final PatientService patientService;
  private final CurrentUser user;
  private Long chatId;

  @PostConstruct
  public void init() {
    messageService.getMessagesForChatId(chatId).subscribe(this::addMessageToView);
  }

  private void addMessageToView(Message message) {
    view.addMessage(message);
  }

  @Override
  public View getView() {
    return view;
  }

  @Override
  public void onAddMessage(String content) {
    messageService.addMessage(this.chatId, content, user.getUser());
  }

  @Override
  public void onBeforeEnter(Long chatId) {
    this.chatId = chatId;

    view.setObserver(this);
    messageService.loadInitialMessagesForChat(chatId).forEach(view::addMessage);

    if (user.getUser() instanceof Patient) {
      Patient patient = (Patient) user.getUser();
      if (patient.getMedicalSpecialist() != null) {
        view.showMedicalSpecialists(patient.getMedicalSpecialist());
      }
    } else if (user.getUser() instanceof MedicalSpecialist) {
      MedicalSpecialist medicalSpecialist = (MedicalSpecialist) user.getUser();
      if (medicalSpecialist.getPatients() != null) {
        view.showPatients(chatService
            .getPatientsByMedicalSpecialist((MedicalSpecialist) user.getUser()));
      }
    }

    List<Patient> patientsByMedicalSpecialist = patientService
        .getPatientsByMedicalSpecialist((MedicalSpecialist) user.getUser());

    List<Patient> chatPatientsByMedicalSpecialist = chatService
        .getPatientsByMedicalSpecialist((MedicalSpecialist) user.getUser());

    Set<Long> openChatsByPatientId = chatPatientsByMedicalSpecialist.stream()
        .map(Patient::getId)
        .collect(Collectors.toSet());
    List<Patient> newChatPartners = patientsByMedicalSpecialist.stream()
        .filter(patient -> !openChatsByPatientId.contains(patient.getId()))
        .collect(Collectors.toList());

    view.listNewChatPartners(newChatPartners);
  }

  @Override
  public void onStartNewConversation() {
    List<Patient> patients = patientService.getPatientsWithoutMedicalSpecialist();
    view.listNewChatPartners(patients);
  }
}