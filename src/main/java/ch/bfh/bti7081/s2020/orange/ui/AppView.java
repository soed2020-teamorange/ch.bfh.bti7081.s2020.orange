package ch.bfh.bti7081.s2020.orange.ui;

import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.VIEWPORT;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Push
@Viewport(VIEWPORT)
@PWA(name = "Team Orange - Projekt MHC-PMS", shortName = "MHC-PMS Orange", startPath = "login")
@RequiredArgsConstructor
@CssImport("./styles/shared-styles.css")
public class AppView extends Div implements RouterLayout {

  private final CurrentUser currentUser;

  @PostConstruct
  public void init() {
    setWidth("100%");
    setHeight("100%");

    if (currentUser.getUser().getRole().equals(Role.PATIENT)) {
      final Button emergencyButton = new Button(new Icon(VaadinIcon.PHONE));
      emergencyButton.addClassName("emergency-button");

      final ContextMenu emergencyContextMenu = new ContextMenu(emergencyButton);
      emergencyContextMenu.addItem(new Anchor("tel:144", "Notruf: 144"));
      emergencyContextMenu.addItem(new Anchor("tel:143", "Dargebotene Hand: 143"));

      Patient patient = (Patient) currentUser.getUser();
      MedicalSpecialist medicalSpecialist = patient.getMedicalSpecialist();
      if (medicalSpecialist.getPhone() != null) {
        emergencyContextMenu.addItem(new Anchor("tel:" + medicalSpecialist.getPhone(),
            medicalSpecialist.getFirstName() + " " + medicalSpecialist
                .getLastName() + ": " + medicalSpecialist.getPhone()));
      }
      emergencyContextMenu.setOpenOnClick(true);
      add(emergencyButton);
    }
  }
}
