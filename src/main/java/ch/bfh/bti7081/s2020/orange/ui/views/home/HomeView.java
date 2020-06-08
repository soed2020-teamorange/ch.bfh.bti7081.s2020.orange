package ch.bfh.bti7081.s2020.orange.ui.views.home;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;
import ch.bfh.bti7081.s2020.orange.ui.views.home.HomeView.Observer;

public interface HomeView extends ViewWithObserver<Observer> {

  void setResult(String result);
  void setUser(CurrentUser user);

  interface Observer {

    void onCalculate(long base, long power);
  }
}
