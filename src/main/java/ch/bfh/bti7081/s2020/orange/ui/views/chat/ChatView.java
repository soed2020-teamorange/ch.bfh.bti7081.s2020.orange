package ch.bfh.bti7081.s2020.orange.ui.views.chat;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;
import ch.bfh.bti7081.s2020.orange.ui.views.chat.ChatView.Observer;

public interface ChatView extends ViewWithObserver<Observer> {

  public void addMessage(Message message);

  interface Observer {

    public void onAddMessage(String messageContent);
  }
}
