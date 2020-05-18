package ch.bfh.bti7081.s2020.orange.ui.views.moodDiary;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.*;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UIScope
@Component
@RequiredArgsConstructor
public class MoodDiaryViewImpl extends VerticalLayout implements MoodDiaryView,
    HasLogger {

  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {
    add(new H1(AppConst.TITLE_MOODDIARY),
        buildForm());
  }

  private Div buildForm() {
    // Create form components
    DatePicker dateDP = new DatePicker("Datum");
    dateDP.setValue(LocalDate.now());
    DatePicker.DatePickerI18n dateDPI18n = new DatePicker.DatePickerI18n();
    dateDPI18n.setWeek("Woche");
    dateDPI18n.setCalendar("Kalender");
    dateDPI18n.setClear("Löschen");
    dateDPI18n.setToday("Heute");
    dateDPI18n.setCancel("Abbrechen");
    dateDPI18n.setWeekdays(Arrays.asList("Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"));
    dateDPI18n.setWeekdaysShort(Arrays.asList("So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"));
    dateDPI18n.setMonthNames(Arrays.asList("Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August",
            "September", "Oktober", "November", "Dezember"));
    dateDP.setI18n(dateDPI18n);

    TimePicker timeTP = new TimePicker();
    timeTP.setValue(LocalTime.now());

    ComboBox<String> moodCB = new ComboBox<>("Stimmung");

    List<String> moods = new ArrayList<>();
    for (Mood m : Mood.values()) {
      moods.add(m.getLabel());
    }
    moodCB.setItems(moods);

    TextField waterDrunkTF = new TextField("Anzahl Liter Wasser");
    TextField sleepHoursTF = new TextField("Anzahl Stunden Schlaf");

    // Bind elements to business object
    Binder<MoodEntry> binder = new Binder<>(MoodEntry.class);
    binder.forField(dateDP)
            .asRequired("Datum muss gesetzt sein.")
            .withValidator(date -> date.isBefore(LocalDate.now().plusDays(1)), "Datum muss in der Vergangenheit sein.")
            .bind(MoodEntry::getDate, MoodEntry::setDate);

    binder.forField(timeTP)
            .asRequired("Zeit muss gesetzt sein.")
            .bind(MoodEntry::getTime, MoodEntry::setTime);

//    binder.forField(moodCB)
//            .asRequired("Stimmung muss ausgewählt werden.");

    Button saveButton = new Button("Neuen Stimmungseintrag hinzufügen");

    // disable saveButton if form has validation errors
    binder.addStatusChangeListener(status -> {
              saveButton.setEnabled(!status.hasValidationErrors());
            }
    );

    saveButton.addClickListener(click -> {
      try {
        MoodEntry moodEntry = new MoodEntry();
        moodEntry.setMood(Mood.valueOfLabel(moodCB.getValue()));
        binder.writeBean(moodEntry);

        // save new patient
        observer.saveNewMoodEntry(moodEntry);

        // load new empty mood to the form
        binder.readBean(new MoodEntry());

      } catch (ValidationException e) {
        getLogger().warn("There are some validation errors.");
      }
    });


    // Wrap components in layouts
    FormLayout formLayout = new FormLayout(dateDP, timeTP, moodCB, waterDrunkTF, sleepHoursTF, saveButton);

    Div wrapperLayout = new Div(formLayout);

    return wrapperLayout;

  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
