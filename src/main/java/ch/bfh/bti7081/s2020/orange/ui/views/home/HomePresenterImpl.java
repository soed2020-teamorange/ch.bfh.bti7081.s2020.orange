package ch.bfh.bti7081.s2020.orange.ui.views.home;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.service.HomeService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HomePresenterImpl implements HomePresenter, HomeView.Observer {

  private final HomeView homeView;
  private final HomeService homeService;
  private final CurrentUser currentUser;

  @Override
  public void onBeforeEnter() {
    homeView.setObserver(this);
    homeView.setResult(currentUser.getUser().getEmail() + " " + ((Patient) currentUser.getUser())
        .getMedicalSpecialist().getId());
  }

  @Override
  public void onCalculate(long base, long power) {
    homeView.setResult(String.valueOf(homeService.calculate(base, power)));
  }

  @Override
  public View getView() {
    return homeView;
  }
}
