package ch.bfh.bti7081.s2020.orange.ui.views.activity_diary.create_entry;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Activity;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.ActivityEntry;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.spring.annotation.UIScope;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@UIScope
@Component
@RequiredArgsConstructor
public class ActivityDiaryCreateEntryViewImpl extends VerticalLayout implements
    ActivityDiaryCreateEntryView,
    HasLogger {

  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {
    add(new H1(AppConst.TITLE_ACTIVITY_DIARY),
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
    dateDPI18n.setWeekdays(Arrays
        .asList("Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"));
    dateDPI18n.setWeekdaysShort(Arrays.asList("So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"));
    dateDPI18n.setMonthNames(
        Arrays.asList("Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August",
            "September", "Oktober", "November", "Dezember"));
    dateDP.setI18n(dateDPI18n);

    TimePicker timeStartTP = new TimePicker("Start-Uhrzeit");
    timeStartTP.setValue(LocalTime.now().minusHours(1));

    TimePicker timeEndTP = new TimePicker("Ende-Uhrzeit");
    timeEndTP.setValue(LocalTime.now());

    ComboBox<String> activityCB = new ComboBox<>("Aktivität");

    List<String> activites = new ArrayList<>();
    for (Activity m : Activity.values()) {
      activites.add(m.getLabel());
    }
    activityCB.setItems(activites);
    activityCB.setValue(activites.get(0));

    TextArea contentTA = new TextArea("Beschreibung");

    // Bind elements to business object
    Binder<ActivityEntry> binder = new Binder<>(ActivityEntry.class);
    binder.forField(dateDP)
        .asRequired("Datum muss gesetzt sein.")
        .withValidator(date -> date.isBefore(LocalDate.now().plusDays(1)),
            "Datum muss in der Vergangenheit sein.")
        .bind(ActivityEntry::getDate, ActivityEntry::setDate);

    binder.forField(timeStartTP)
        .asRequired("Zeit muss gesetzt sein.")
        .bind(ActivityEntry::getStartTime, ActivityEntry::setStartTime);

    Binder.Binding<ActivityEntry, LocalTime> endTimeBinding = binder.forField(timeEndTP)
        .asRequired("Zeit muss gesetzt sein.")
        .withValidator(e -> timeStartTP.getValue().isBefore(e), "Startzeit muss vor Endzeit sein.")
        .bind(ActivityEntry::getEndTime, ActivityEntry::setEndTime);

    binder.forField(contentTA)
        .asRequired("Bitte eine Beschreibung eingeben.")
        .bind(ActivityEntry::getContent, ActivityEntry::setContent);

    Button saveButton = new Button("Neuen Aktivität hinzufügen");

    // TODO: fix NullPointer
    // timeStartTP.addValueChangeListener(
    //        event -> endTimeBinding.validate()
    //);

    // disable saveButton if form has validation errors
    binder.addStatusChangeListener(status -> {
          saveButton.setEnabled(!status.hasValidationErrors());
        }
    );

    saveButton.addClickListener(click -> {
      try {
        ActivityEntry activityEntry = new ActivityEntry();
        activityEntry.setActivity(Activity.valueOfLabel(activityCB.getValue()));
        binder.writeBean(activityEntry);

        // save new patient
        observer.saveNewActivityEntry(activityEntry);

        // load new empty activity to the form
        binder.readBean(new ActivityEntry());
        timeStartTP.setValue(LocalTime.now().minusHours(1));
        timeEndTP.setValue(LocalTime.now());
        dateDP.setValue(LocalDate.now());

      } catch (ValidationException e) {
        getLogger().warn("There are some validation errors.");
      }
    });

    // Wrap components in layouts
    FormLayout formLayout = new FormLayout(dateDP, timeStartTP, timeEndTP, activityCB, contentTA,
        saveButton);

    Div wrapperLayout = new Div(formLayout);

    return wrapperLayout;

  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
