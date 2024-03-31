package Home;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();

        // destinatii fake
        List<Destination> destinations = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Destination("D" + i + ": " + faker.country().capital()))
                .collect(Collectors.toList());

        System.out.println("\nDestinastions:");
        destinations.forEach(driver -> System.out.println(driver.getName()));

        //driveri name fake
        List<Driver> drivers = Arrays.asList(
                new Driver(faker.name().fullName(), destinations.get(0), Arrays.asList(destinations.get(0), destinations.get(1))),
                new Driver(faker.name().fullName(), destinations.get(1), Arrays.asList(destinations.get(1), destinations.get(2)))
        );

        System.out.println("\nDrivers:");
        drivers.forEach(driver -> System.out.println(driver.getName()));

        // passengers cu destinații specifice
        List<Passenger> passengers = Arrays.asList(
                new Passenger(faker.name().fullName(), destinations.get(0)),
                new Passenger(faker.name().fullName(), destinations.get(1))

        );

        System.out.println("\nPassengers:");
        passengers.forEach(passenger -> System.out.println(passenger.getName()));

        //combinam drivers cu passengers
        List<Person> persons = new ArrayList<>();
        persons.addAll(drivers);
        persons.addAll(passengers);

        //Problema
        Problem problem = new Problem(persons, destinations);
        //System.out.println("\n"+problem);

        // Maparea
        System.out.println("\nMap-> detinatia si persoanele: ");
        problem.getPersonWithDestinationMap().forEach((destination, people) -> {
            System.out.println(destination + ": " + people.stream().map(Person::getName).collect(Collectors.joining(", ")));
        });

        // greedy
        System.out.println("\nGreedy alg:");
        problem.greedyAlgorithm();
    }
}

