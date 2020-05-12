package ch.bfh.bti7081.s2020.orange.ui.views.editUserInfos;


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

  @Override
  public void onBeforeEnter() {
    editUserInfosView.setObserver(this);
  }

  @Override
  public View getView() {
    return editUserInfosView;
  }

  @Override
  public void onSaveUser(User user) {
    userService.saveUser(user);
    editUserInfosView.showNotification("Angaben erfolgreich bearbeitet.");
  }
}
