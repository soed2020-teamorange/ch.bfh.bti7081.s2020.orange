package ch.bfh.bti7081.s2020.orange.ui.views.home;

import ch.bfh.bti7081.s2020.orange.backend.service.HomeService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HomePresenterImpl implements HomePresenter, HomeView.Observer {

  private final HomeView homeView;
  private final HomeService homeService;

  @Override
  public void onBeforeEnter() {
    homeView.setObserver(this);
    homeView.setResult(String.valueOf(homeService.getInitialValue()));
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
