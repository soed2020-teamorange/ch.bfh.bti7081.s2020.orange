package ch.bfh.bti7081.s2020.orange;

import static org.junit.Assert.assertEquals;

import ch.bfh.bti7081.s2020.orange.backend.data.Mood;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodEntry;
import ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.overview.MoodDiaryOverviewViewImpl;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.DataSeries;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

public class MoodDiaryOverviewUnitTests {

  @Test
  public void shouldCreateChartOnSetEntries() {
    final MoodDiaryOverviewViewImpl view = new MoodDiaryOverviewViewImpl();
    final MoodEntry entry = new MoodEntry(Mood.NEUTRAL, LocalDate.now(), LocalTime.now(), 8, 2);

    view.init();
    view.setEntries(List.of(entry));

    final Chart chart = this.findComponent(view, Chart.class);

    final DataSeries moodSeries = (DataSeries) chart.getConfiguration().getSeries().stream()
        .filter(serie -> serie.getName().equals("Stimmung")).findFirst().get();

    final DataSeries sleepHoursSeries = (DataSeries) chart.getConfiguration().getSeries().stream()
        .filter(serie -> serie.getName().equals("Stunden Schlaf")).findFirst().get();

    final DataSeries waterDrunkSeries = (DataSeries) chart.getConfiguration().getSeries().stream()
        .filter(serie -> serie.getName().equals("Liter Wasser")).findFirst().get();

    final long expectedTimestamp =
        entry.getDate().atTime(entry.getTime()).toEpochSecond(ZoneOffset.UTC) * 1000;

    assertEquals(moodSeries.getData().get(0).getY(), entry.getMood().ordinal());
    assertEquals(sleepHoursSeries.getData().get(0).getY(), entry.getSleepHours());
    assertEquals(waterDrunkSeries.getData().get(0).getY(), entry.getWaterDrunk());

    assertEquals(moodSeries.getData().get(0).getX(), expectedTimestamp);
    assertEquals(sleepHoursSeries.getData().get(0).getX(), expectedTimestamp);
    assertEquals(waterDrunkSeries.getData().get(0).getX(), expectedTimestamp);
  }

  private <T> T findComponent(final Component input, final Class<T> clazz) {
    if (clazz.isInstance(input)) {
      return clazz.cast(input);
    }

    for (final Component comp : input.getChildren().collect(Collectors.toList())) {
      final T found = this.findComponent(comp, clazz);

      if (null != found) {
        return found;
      }
    }

    return null;
  }
}
