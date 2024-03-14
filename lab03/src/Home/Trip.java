package Home;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalTime;
import java.time.LocalDate;

public class Trip {
    private List<Attraction> attractions = new ArrayList<>();

    public Trip(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public void displayVisitableNotPayableSorted() {
        attractions.stream()
                .filter(a -> a instanceof Visitable && !(a instanceof Payable))
                .map(a -> (Visitable) a)
                .sorted((a1, a2) -> a1.getOpeningHour(LocalDate.now()).compareTo(a2.getOpeningHour(LocalDate.now())))
                .forEach(a -> System.out.println(((Attraction) a).getName() + " opens at " + a.getOpeningHour(LocalDate.now())));
    }
}


