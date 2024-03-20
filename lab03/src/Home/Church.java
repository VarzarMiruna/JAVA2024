package Home;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

// Assuming all classes like Church, Concert, etc., extend Attraction and implement Visitable and/or Payable as appropriate.

public class Church extends Attraction implements Visitable {
    private Map<LocalDate, TimeInterval> timetable = new HashMap<>();

    public Church(String name) {
        super(name);
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return timetable;
    }
}

