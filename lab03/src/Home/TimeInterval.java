package Home;

import java.time.LocalTime;

class TimeInterval extends Pair<LocalTime, LocalTime> {
    public TimeInterval(LocalTime openingTime, LocalTime closingTime) {
        super(openingTime, closingTime);
    }
}
