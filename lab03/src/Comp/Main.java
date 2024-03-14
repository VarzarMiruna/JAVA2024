package Comp;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Statue statue = new Statue("Stefan cel Mare", "A symbol of good time");
        statue.setOpeningHours("9:00 - 17:00");

        Church church = new Church("Sfanta Parascheva", "An iconic religious site");
        church.setOpeningHours("7:00 - 19:00");

        Concert concert = new Concert("The Motans", "Good Music");
        concert.setOpeningHours("18:00 - 23:00");
        concert.setEntryFee(99.99);

        List<Attraction> attractions = new ArrayList<>();
        attractions.add(statue);
        attractions.add(church);
        attractions.add(concert);

        Trip trip = new Trip("Iasi", "August 1st - August 7th", attractions);

        // Example of accessing trip details
        System.out.println("Trip to " + trip.getCity() + " during " + trip.getPeriod());
        for (Attraction attraction : trip.getAttractions()) {
            System.out.println("Visiting: " + attraction.getName() + ", " + "Description: " + attraction.getDescription());
        }
    }
}
