package ch.bfh.bti7081.s2020.orange.backend.data;

public enum Activity {
  FOOD("Essen"),
  EXERCISE("Sport"),
  MEDICATION("Medikamenteneinnahme"),
  SOCIALIZE("Soziale Kontakte");

  private final String label;

  Activity(final String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }

  public static Activity valueOfLabel(final String label) {
    for (final Activity a : Activity.values()) {
      if (a.getLabel().equals(label)) {
        return a;
      }
    }
    return null;
  }
}

