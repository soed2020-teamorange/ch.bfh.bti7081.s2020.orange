package ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.create_entry;

import ch.bfh.bti7081.s2020.orange.backend.data.Mood;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodEntry;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToDoubleConverter;
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
public class MoodDiaryCreateEntryViewImpl extends VerticalLayout implements
    MoodDiaryCreateEntryView,
    HasLogger {

  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {
    this.add(new H1(AppConst.TITLE_MOOD_DIARY),
        this.buildForm());
  }

  private Div buildForm() {
    // Create form components
    final DatePicker dateDP = new DatePicker("Datum");
    dateDP.setValue(LocalDate.now());
    final DatePicker.DatePickerI18n dateDPI18n = new DatePicker.DatePickerI18n();
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

    final TimePicker timeTP = new TimePicker("Uhrzeit");
    timeTP.setValue(LocalTime.now());

    final ComboBox<String> moodCB = new ComboBox<>("Stimmung");

    final List<String> moods = new ArrayList<>();
    for (final Mood m : Mood.values()) {
      moods.add(m.getLabel());
    }
    moodCB.setItems(moods);
    moodCB.setValue(moods.get(1));

    final TextField titleTF = new TextField("Titel");
    final TextArea contentTA = new TextArea("Beschreibung");
    final TextField waterDrunkTF = new TextField("Anzahl Liter Wasser", "0");
    final TextField sleepHoursTF = new TextField("Anzahl Stunden Schlaf", "0");

    // Bind elements to business object
    final Binder<MoodEntry> binder = new Binder<>(MoodEntry.class);
    binder.forField(dateDP)
        .asRequired("Datum muss gesetzt sein.")
        .withValidator(date -> date.isBefore(LocalDate.now().plusDays(1)),
            "Datum muss in der Vergangenheit sein.")
        .bind(MoodEntry::getDate, MoodEntry::setDate);

    binder.forField(timeTP)
        .asRequired("Zeit muss gesetzt sein.")
        .bind(MoodEntry::getTime, MoodEntry::setTime);

    binder.forField(titleTF)
        .asRequired("Bitte einen Titel eingeben.")
        .withValidator(l -> l.length() <= 100, "Bitte maximal 100 Zeichen verwenden.")
        .bind(MoodEntry::getTitle, MoodEntry::setTitle);

    binder.forField(contentTA)
        .asRequired("Bitte eine Beschreibung eingeben.")
        .withValidator(l -> l.length() <= 800, "Bitte maximal 800 Zeichen verwenden.")
        .bind(MoodEntry::getContent, MoodEntry::setContent);

    binder.forField(waterDrunkTF)
        .asRequired("Bitte eine gültige Zahl eingeben.")
        .withConverter(new StringToDoubleConverter("Bitte eine gültige Zahl eingeben."))
        .bind(MoodEntry::getWaterDrunk, MoodEntry::setWaterDrunk);

    binder.forField(sleepHoursTF)
        .asRequired("Bitte eine gültige Zahl eingeben.")
        .withConverter(new StringToDoubleConverter("Bitte eine gültige Zahl eingeben."))
        .bind(MoodEntry::getSleepHours, MoodEntry::setSleepHours);

    final Button saveButton = new Button("Neuen Stimmungseintrag hinzufügen");

    // disable saveButton if form has validation errors
    binder.addStatusChangeListener(status -> {
          saveButton.setEnabled(!status.hasValidationErrors());
        }
    );

    saveButton.addClickListener(click -> {
      try {
        final MoodEntry moodEntry = new MoodEntry();
        moodEntry.setMood(Mood.valueOfLabel(moodCB.getValue()));
        binder.writeBean(moodEntry);

        // save new patient
        this.observer.saveNewMoodEntry(moodEntry);

        // load new empty mood to the form
        binder.readBean(new MoodEntry());
        timeTP.setValue(LocalTime.now());
        dateDP.setValue(LocalDate.now());

      } catch (final ValidationException e) {
        this.getLogger().warn("There are some validation errors.");
      }
    });

    // Wrap components in layouts
    final FormLayout formLayout = new FormLayout(dateDP, timeTP, moodCB, titleTF, contentTA,
        waterDrunkTF,
        sleepHoursTF,
        saveButton);

    final Div wrapperLayout = new Div(formLayout);

    return wrapperLayout;

  }

  @Override
  public <C> C getComponent(final Class<C> type) {
    return type.cast(this);
  }
}
