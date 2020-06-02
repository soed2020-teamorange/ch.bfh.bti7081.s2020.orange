package ch.bfh.bti7081.s2020.orange.ui.views.user_infos.edit;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.ui.exceptions.UserAlreadyExistsException;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasNotifications;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;
import java.util.List;

public interface UserInfosEditView extends ViewWithObserver<UserInfosEditView.Observer>,
    HasNotifications {

  void setUser(User user);

  void setEMailIsUniqueError(boolean b);

  void setMedicalSpecialist(MedicalSpecialist medicalSpecialist);

  void setPatients(List<Patient> patients);

  interface Observer {

    void onSaveUser(User user);

    void emailIsUnique(String email) throws UserAlreadyExistsException;

  }
}
