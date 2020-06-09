package ch.bfh.bti7081.s2020.orange.ui.utils;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;

/**
 * Interface for views showing notifications to users
 */
public interface HasNotifications extends HasElement {

  default void showNotification(final String message) {
    this.showNotification(message, false);
  }

  default void showNotification(final String message, final boolean persistent) {
    if (persistent) {
      final Button close = new Button("Close");
      close.getElement().setAttribute("theme", "tertiary small error");
      final Notification notification = new Notification(new Text(message), close);
      notification.setPosition(Notification.Position.BOTTOM_START);
      notification.setDuration(0);
      close.addClickListener(event -> notification.close());
      notification.open();
    } else {
      Notification
          .show(message, AppConst.NOTIFICATION_DURATION, Notification.Position.BOTTOM_STRETCH);
    }
  }
}
