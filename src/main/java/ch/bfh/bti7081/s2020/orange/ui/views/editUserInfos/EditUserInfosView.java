package ch.bfh.bti7081.s2020.orange.ui.views.editUserInfos;


import ch.bfh.bti7081.s2020.orange.backend.model.Person;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;

public interface EditUserInfosView extends ViewWithObserver<EditUserInfosView.Observer> {

  interface Observer {
    Person getPersonById(long id);

  }
}
