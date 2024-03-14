package Home;

import java.util.Map;
import java.time.LocalTime;
import java.time.LocalDate;


public interface Visitable {
    Map<LocalDate, TimeInterval> getTimetable();

    default LocalTime getOpeningHour(LocalDate date) {
        TimeInterval timeInterval = getTimetable().get(date);
        return timeInterval != null ? timeInterval.getFirst() : null;
    }

}


