package Home;

import java.util.*;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Home.Person;
import Home.Driver;
import Home.Destination;
import Home.Passenger;


public class Problem {
    private List<Person> persons;
    private List<Destination> destinations;

    @Override
    public String toString() {
        return "Problem{" +
                "\npersoane=" + persons +
                ", \ndestinatii=" + destinations +
                '}';
    }

    public Problem(List<Person> persons, List<Destination> destinations) {
        this.persons = persons;
        this.destinations = destinations;
    }

    //get si set
    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }


    // Compute a map of destinations and persons who want to go there
    public Map<Destination, List<Person>> getPersonWithDestinationMap() {
        Map<Destination, List<Person>> destMap = new HashMap<>();

        for (Destination dest : destinations) {
            List<Person> personsGoingToDest = persons.stream()
                    .filter(person -> person instanceof Driver && ((Driver) person).getPassThroughDestinations().contains(dest) ||
                            person instanceof Passenger && person.getDestination().equals(dest))
                    .collect(Collectors.toList());
            destMap.put(dest, personsGoingToDest);
        }

        return destMap;
    }


    //alg greedy, un sofer ia un singur pasager
    public void greedyAlgorithm() {
        List<Driver> drivers = persons.stream()
                .filter(Driver.class::isInstance)
                .map(Driver.class::cast)
                .collect(Collectors.toList());

        List<Passenger> passengers = persons.stream()
                .filter(Passenger.class::isInstance)
                .map(Passenger.class::cast)
                .collect(Collectors.toList());

        Map<Driver, Passenger> matches = new HashMap<>();

        for (Driver driver : drivers) {
            for (Passenger passenger : passengers) {
                // verfifam daca pasagerul mvrea la destinatia anume
                if (driver.getPassThroughDestinations().contains(passenger.getDestination()) && !matches.containsKey(driver)) {
                    matches.put(driver, passenger);
                    break; // daca soferul are un pasager, trec la urm sofer care alege
                }
            }
        }

        matches.forEach((driver, passenger) -> System.out.println("Soferul: " + driver.getName() + " --ia-- "+ "Pasagerul: " + passenger.getName() + " --la-- " + "Destinatia: " + passenger.getDestination().getName()));
    }
}



