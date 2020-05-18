package ch.bfh.bti7081.s2020.orange.backend.data.entities;

public enum Mood {
    DEPRESSED("Deprimiert"),
    ELATED("Begeistert"),
    HAPPY("Glücklich"),
    NEUTRAL("Normal"),
    SAD("Traurig");

    private final String label;

    private Mood(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Mood valueOfLabel(String label) {
        for (Mood e : values()) {
            if (e.getLabel().equals(label)) {
                return e;
            }
        }
        return null;
    }
}

