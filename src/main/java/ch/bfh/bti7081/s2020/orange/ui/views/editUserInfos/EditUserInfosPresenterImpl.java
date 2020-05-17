package ch.bfh.bti7081.s2020.orange.ui.views.editUserInfos;


import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.backend.service.UserService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EditUserInfosPresenterImpl implements EditUserInfosPresenter,
    EditUserInfosView.Observer {

  private final EditUserInfosView editUserInfosView;
  private final UserService userService;
  private final CurrentUser currentUser;

  @Override
  public void onBeforeEnter() {
    editUserInfosView.setObserver(this);
    editUserInfosView.setUser(currentUser.getUser());
  }

  @Override
  public void onSaveUser(User user) {
    userService.saveUser(user);
    editUserInfosView.showNotification("Angaben erfolgreich bearbeitet.");
  }

  @Override
  public boolean emailIsUnique(String email) {
    return userService.emailIsUnique(email);
  }

  @Override
  public View getView() {
    return editUserInfosView;
  }
}
