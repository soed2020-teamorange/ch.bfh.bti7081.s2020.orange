package ch.bfh.bti7081.s2020.orange.ui.views.viewPatient;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;
import java.util.List;

public interface ViewPatientView extends ViewWithObserver<ViewPatientView.Observer> {

  interface Observer {

    List<Patient> getAllPatients();
  }
}
