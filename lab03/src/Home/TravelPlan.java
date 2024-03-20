package Home;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TravelPlan {
    private Map<Attraction, LocalDate> plan = new LinkedHashMap<>();

    public void addAttraction(Attraction attraction, LocalDate date) {
        plan.put(attraction, date);
    }

    public void printPlan() {
        for (Entry<Attraction, LocalDate> entry : plan.entrySet()) {
            System.out.println(entry.getKey().getName() + " will be visited on " + entry.getValue());
        }
    }
}


