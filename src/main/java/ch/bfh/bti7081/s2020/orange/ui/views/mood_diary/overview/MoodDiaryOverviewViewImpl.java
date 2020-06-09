package ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.overview;

import ch.bfh.bti7081.s2020.orange.backend.data.Mood;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodEntry;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.AxisType;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.spring.annotation.UIScope;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@UIScope
@Component
public class MoodDiaryOverviewViewImpl extends VerticalLayout implements MoodDiaryOverviewView {

  VerticalLayout content = new VerticalLayout();

  @PostConstruct
  public void init() {
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
        e -> button.getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_MOOD_DIARY_CREATE_ENTRY)));

    return button;
  }

  private void addContent(final List<MoodEntry> entries) {
    final Tab chartTab = new Tab("Diagramm");
    final Chart chartPage = this.createChart(entries);

    final Tab tableTab = new Tab("Tabelle");
    final Grid<MoodEntry> tablePage = this.createTable(entries);
    tablePage.setVisible(false);

    final Map<Tab, com.vaadin.flow.component.Component> tabsToPages = new HashMap<>();
    tabsToPages.put(chartTab, chartPage);
    tabsToPages.put(tableTab, tablePage);
    final Tabs tabs = new Tabs(chartTab, tableTab);
    tabs.setWidth("100%");
    final Div pages = new Div(chartPage, tablePage);
    pages.setWidth("100%");
    final Set<com.vaadin.flow.component.Component> pagesShown = Stream.of(chartPage)
        .collect(Collectors.toSet());

    tabs.addSelectedChangeListener(event -> {
      pagesShown.forEach(page -> page.setVisible(false));
      pagesShown.clear();
      final com.vaadin.flow.component.Component selectedPage = tabsToPages
          .get(tabs.getSelectedTab());
      selectedPage.setVisible(true);
      pagesShown.add(selectedPage);
    });

    this.content.removeAll();
    this.content.add(tabs);
    this.content.add(pages);
  }

  private H1 createTitle() {
    return new H1(AppConst.TITLE_MOOD_DIARY);
  }

  private Chart createChart(final List<MoodEntry> entries) {
    final Chart chart = new Chart(ChartType.LINE);

    Configuration configuration = chart.getConfiguration();

    final DataSeries moodSeries = new DataSeries("Stimmung");
    final DataSeries sleepHoursSeries = new DataSeries("Stunden Schlaf");
    final DataSeries waterDrunkSeries = new DataSeries("Liter Wasser");
    moodSeries.setyAxis(1);

    for (final MoodEntry entry : entries) {
      final long timestamp = entry.getDate().atTime(entry.getTime()).toEpochSecond(ZoneOffset.UTC);
      moodSeries.add(new DataSeriesItem(timestamp * 1000, this.mapMood(entry.getMood())));
      sleepHoursSeries.add(new DataSeriesItem(timestamp * 1000, entry.getSleepHours()));
      waterDrunkSeries.add(new DataSeriesItem(timestamp * 1000, entry.getWaterDrunk()));
    }

    configuration.setTitle("Stimmungsanalyse");

    final XAxis xAxis = configuration.getxAxis();
    xAxis.setType(AxisType.DATETIME);

    final YAxis numberYAxis = configuration.getyAxis();
    numberYAxis.setOpposite(true);
    numberYAxis.setTitle("Schlaf (Stunden) / Wasser (Liter)");

    final YAxis moodYAxis = new YAxis();
    moodYAxis.setTitle("Stimmung (Text)");
    moodYAxis.setTickPositions(new Number[]{1, 2, 3, 4, 5});
    moodYAxis.getLabels().setFormatter("function(){"
        + "    switch(this.value){"
        + "        case 1:"
        + "            return 'Deprimiert';"
        + "        case 2: "
        + "            return 'Trauring'; "
        + "        case 3:"
        + "            return 'Normal';"
        + "        case 4:"
        + "            return 'Glücklich';"
        + "        case 5: "
        + "            return 'Begeistert';"
        + "    }"
        + "    return null;"
        + "}");
    configuration.addyAxis(moodYAxis);

    configuration.addSeries(moodSeries);
    configuration.addSeries(sleepHoursSeries);
    configuration.addSeries(waterDrunkSeries);

    configuration.getTooltip().setShared(true);
    configuration.getTooltip().setPointFormatter("function(){"
        + "  var val = '';"
        + "  var suffix = '';"
        + "  var prefix = '';"
        + "  switch(this.series.name){"
        + "    case 'Stimmung': {"
        + "        switch(this.y){"
        + "        case 1: {"
        + "            val = 'Deprimiert';"
        + "          break;"
        + "        }"
        + "        case 2: {"
        + "            val = 'Traurig';"
        + "          break;"
        + "        }"
        + "        case 3: {"
        + "            val = 'Normal';"
        + "          break;"
        + "        }"
        + "        case 4: {"
        + "            val = 'Glücklich';"
        + "          break;"
        + "        }"
        + "        case 5: {"
        + "            val = 'Begeistert';"
        + "          break;"
        + "        }"
        + "      }"
        + "      prefix='Stimmung:';"
        + "      break;"
        + "    }"
        + "    case 'Stunden Schlaf': {"
        + "        val = this.y;"
        + "      suffix = 'Stunden';"
        + "      prefix = 'Schlaf:';"
        + "      break;"
        + "    "
        + "    }"
        + "    case 'Liter Wasser': {"
        + "        val = this.y;"
        + "      suffix = 'Liter';"
        + "      prefix = 'Wasser:';"
        + "      break;"
        + "    }"
        + "  }"
        + "  return '<span>'+ prefix + '</span><b> '+val + '</b><span> ' + suffix + '</span><br/>';"
        + "}");

    return chart;
  }

  private Number mapMood(final Mood mood) {
    switch (mood) {
      case DEPRESSED:
        return 1;
      case SAD:
        return 2;
      case NEUTRAL:
        return 3;
      case HAPPY:
        return 4;
      case ELATED:
        return 5;
    }

    return null;
  }

  private Grid<MoodEntry> createTable(final List<MoodEntry> entries) {
    final Grid<MoodEntry> entryGrid = new Grid<>(MoodEntry.class);

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    entryGrid.removeAllColumns();
    entryGrid.addColumn(entry -> entry.getDate().format(formatter)).setHeader("Datum");
    entryGrid.addColumn(MoodEntry::getTime).setHeader("Uhrzeit");
    entryGrid.addColumn(MoodEntry::getTitle).setHeader("Titel");
    entryGrid.addColumn(MoodEntry::getContent).setHeader("Inhalt");
    entryGrid.addColumn(entry -> entry.getMood().getLabel()).setHeader("Stimmung");
    entryGrid.addColumn(entry -> entry.getSleepHours() + " Stunden").setHeader("Schlaf");
    entryGrid.addColumn(entry -> entry.getWaterDrunk() + " Liter").setHeader("Liter Wasser");

    entryGrid.setItems(entries);

    return entryGrid;
  }

  @Override
  public <C> C getComponent(final Class<C> type) {
    return type.cast(this);
  }


  @Override
  public void setEntries(final List<MoodEntry> entries) {
    this.addContent(entries);
  }
}
