package Home;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Church church = new Church("St. Peter's Basilica");
        church.getTimetable().put(LocalDate.now(), new TimeInterval(LocalTime.of(7, 0), LocalTime.of(19, 0)));

        Concert concert = new Concert("Summer Music Festival", 50.0);
        concert.getTimetable().put(LocalDate.now(), new TimeInterval(LocalTime.of(18, 0), LocalTime.of(23, 0)));

        List<Attraction> attractions = Arrays.asList(church, concert);
        Trip trip = new Trip(attractions);

        System.out.println("Visitable and not payable attractions, sorted by opening hour:");
        trip.displayVisitableNotPayableSorted();

        TravelPlan travelPlan = new TravelPlan();
        travelPlan.addAttraction(church, LocalDate.now());
        travelPlan.addAttraction(concert, LocalDate.now().plusDays(1));

        System.out.println("\nTravel Plan:");
        travelPlan.printPlan();
    }
}
