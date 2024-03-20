package Home;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Church church = new Church("\nSf. Maria");
        church.getTimetable().put(LocalDate.now(), new TimeInterval(LocalTime.of(7, 0), LocalTime.of(19, 0)));

        Church church1 = new Church("Sf. Pavel");
        church1.getTimetable().put(LocalDate.now(), new TimeInterval(LocalTime.of(9, 0), LocalTime.of(20, 0)));


        Concert concert = new Concert("THE MOTANS & DELIA", 50.0);
        concert.getTimetable().put(LocalDate.now(), new TimeInterval(LocalTime.of(18, 0), LocalTime.of(23, 0)));

        Statue statue = new Statue("Hidra");
        statue.getTimetable().put(LocalDate.now(), new TimeInterval(LocalTime.of(10, 0), LocalTime.of(22, 0)));

        List<Attraction> attractions = Arrays.asList(church, concert);
        Trip trip = new Trip(attractions);

        List<Attraction> attractions2 = Arrays.asList(church1, statue);
        Trip trip2 = new Trip(attractions2);

        System.out.println("\nVisitable and not payable attractions, sorted by opening hour:");
        trip.displayVisitableNotPayableSorted();
        trip2.displayVisitableNotPayableSorted();

        TravelPlan travelPlan = new TravelPlan();
        travelPlan.addAttraction(church, LocalDate.now());
        travelPlan.addAttraction(church1, LocalDate.now());
        travelPlan.addAttraction(statue, LocalDate.now());
        travelPlan.addAttraction(concert, LocalDate.now().plusDays(1));

        System.out.println("\nTravel Plan:");
        travelPlan.printPlan();
    }
}
