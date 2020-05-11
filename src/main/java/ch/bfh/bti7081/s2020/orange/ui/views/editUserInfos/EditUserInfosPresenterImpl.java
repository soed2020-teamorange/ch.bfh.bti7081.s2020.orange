package ch.bfh.bti7081.s2020.orange.ui.views.editUserInfos;


import ch.bfh.bti7081.s2020.orange.backend.model.Patient;
import ch.bfh.bti7081.s2020.orange.backend.model.Person;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import ch.bfh.bti7081.s2020.orange.backend.service.PersonService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EditUserInfosPresenterImpl implements EditUserInfosPresenter, EditUserInfosView.Observer {

    private final EditUserInfosView editUserInfosView;
    private final PersonService personService;

    @Override
    public void onBeforeEnter() {
        editUserInfosView.setObserver(this);
    }

    @Override
    public View getView() {
        return editUserInfosView;
    }

    @Override
    public Person getPersonById(long id) {
        return personService.getPersonById(id);
    }
}
