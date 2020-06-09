package ch.bfh.bti7081.s2020.orange.ui.views.activity_diary.overview;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.ActivityEntry;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import de.nils_bauer.PureTimeline;
import de.nils_bauer.PureTimelineItem;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@UIScope
@Component
public class ActivityDiaryOverviewViewImpl extends VerticalLayout implements
    ActivityDiaryOverviewView {

  VerticalLayout content = new VerticalLayout();

  @PostConstruct
  public void init() {
    this.content.setClassName("timeline-container");

    final Div noData = new Div();
    noData.setText("Keine Daten vorhanden");
    this.content.add(noData);

    this.add(this.createTitle());
    this.add(this.createButton());
    this.add(this.content);
  }

  private Button createButton() {
    final Button button = new Button("Neuen Eintrag erstellen");

    button.addClickListener(
        e -> button.getUI()
            .ifPresent(ui -> ui.navigate(AppConst.PAGE_ACTIVITY_DIARY_CREATE_ENTRY)));

    return button;
  }

  private void addContent(final List<ActivityEntry> entries) {

    final PureTimeline timeline = new PureTimeline();

    for (final ActivityEntry entry : entries) {
      final PureTimelineItem item = new PureTimelineItem(this.getBoxText(entry),
          this.getBoxContent(entry));

      timeline.add(item);
    }

    this.content.removeAll();
    this.content.add(timeline);
  }

  private com.vaadin.flow.component.Component[] getBoxContent(final ActivityEntry entry) {
    return new com.vaadin.flow.component.Component[]{
        new H1(entry.getTitle()),
        new H2(entry.getActivity().getLabel()),
        new Paragraph(entry.getContent())
    };
  }

  private String getBoxText(final ActivityEntry entry) {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    return String.format("%s %tR - %tR", entry.getDate().format(formatter), entry.getStartTime(),
        entry.getEndTime());
  }

  private H1 createTitle() {
    return new H1(AppConst.TITLE_ACTIVITY_DIARY);
  }

  @Override
  public void setEntries(final List<ActivityEntry> entries) {
    this.addContent(entries);
  }

  @Override
  public <C> C getComponent(final Class<C> type) {
    return type.cast(this);
  }

}
