package ch.bfh.bti7081.s2020.orange.ui.views.editUserInfos;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasNotifications;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;

public interface EditUserInfosView extends ViewWithObserver<EditUserInfosView.Observer>,
    HasNotifications {

  void setUser(User user);

  interface Observer {

    void onSaveUser(User user);

    boolean emailIsUnique(String email);

  }
}
