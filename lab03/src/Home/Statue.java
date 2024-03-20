package Home;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Statue  extends Attraction implements Visitable {
    private Map<LocalDate, TimeInterval> timetable = new HashMap<>();

    public Statue(String name) {
        super(name);
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return timetable;
    }

}
