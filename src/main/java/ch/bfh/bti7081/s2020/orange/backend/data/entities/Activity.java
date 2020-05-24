package ch.bfh.bti7081.s2020.orange.backend.data.entities;

public enum Activity {
    FOOD("Essen"),
    EXERCISE("Sport"),
    MEDICATION("Medikamenteneinnahme"),
    SOCIALIZE("Soziale Kontakte");

    private final String label;

    private Activity(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Activity valueOfLabel(String label) {
        for (Activity a : values()) {
            if (a.getLabel().equals(label)) {
                return a;
            }
        }
        return null;
    }
}

