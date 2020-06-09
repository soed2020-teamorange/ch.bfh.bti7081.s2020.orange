package ch.bfh.bti7081.s2020.orange.backend.data;

public enum Mood {
  DEPRESSED("Deprimiert"),
  ELATED("Begeistert"),
  HAPPY("Glücklich"),
  NEUTRAL("Normal"),
  SAD("Traurig");

  private final String label;

  Mood(final String label) {
    this.label = label;
  }

  public String getLabel() {
    return this.label;
  }

  public static Mood valueOfLabel(final String label) {
    for (final Mood e : Mood.values()) {
      if (e.getLabel().equals(label)) {
        return e;
      }
    }
    return null;
  }
}

