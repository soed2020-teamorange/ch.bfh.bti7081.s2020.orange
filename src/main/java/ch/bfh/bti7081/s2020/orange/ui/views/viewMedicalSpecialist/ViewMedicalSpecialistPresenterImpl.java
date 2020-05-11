package ch.bfh.bti7081.s2020.orange.ui.views.viewMedicalSpecialist;


import ch.bfh.bti7081.s2020.orange.backend.model.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.service.MedicalSpecialistService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewMedicalSpecialistPresenterImpl implements ViewMedicalSpecialistPresenter,
    ViewMedicalSpecialistView.Observer {

  private final ViewMedicalSpecialistView viewMedicalSpecialistView;
  private final MedicalSpecialistService medicalSpecialistService;

  @Override
  public void onBeforeEnter() {
    viewMedicalSpecialistView.setObserver(this);
  }

  @Override
  public View getView() {
    return viewMedicalSpecialistView;
  }

  @Override
  public List<MedicalSpecialist> getAllMedicalSpecialists() {
    return medicalSpecialistService.getAllMedicalSpecialist();
  }
}
