package ch.bfh.bti7081.s2020.orange.ui.views.chat;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;
import ch.bfh.bti7081.s2020.orange.ui.views.chat.ChatView.Observer;
import java.util.List;

public interface ChatView extends ViewWithObserver<Observer> {

  public void addMessage(Message message);

  public void showMedicalSpecialists(MedicalSpecialist medicalSpecialist);

  public void showPatients(List<Patient> patients);

  public void listNewChatPartners(List<Patient> patients);

  interface Observer {

    public void onAddMessage(String messageContent);

    public void onStartNewConversation();
  }
}
