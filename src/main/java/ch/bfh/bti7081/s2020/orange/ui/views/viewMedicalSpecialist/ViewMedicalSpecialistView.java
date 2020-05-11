package ch.bfh.bti7081.s2020.orange.ui.views.viewMedicalSpecialist;

import ch.bfh.bti7081.s2020.orange.backend.model.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;
import java.util.List;

public interface ViewMedicalSpecialistView extends
    ViewWithObserver<ViewMedicalSpecialistView.Observer> {

  interface Observer {

    List<MedicalSpecialist> getAllMedicalSpecialists();
  }
}
