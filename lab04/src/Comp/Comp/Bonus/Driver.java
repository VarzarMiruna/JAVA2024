package Bonus;

import java.util.ArrayList;
import java.util.List;

public class Driver extends Person {
    private List<Destination> passThroughDestinations;

    public Driver(String name, Destination destination, List<Destination> passThroughDestinations) {
        super(name, destination);
        this.passThroughDestinations = passThroughDestinations;
    }

    public List<Destination> getPassThroughDestinations() {
        return passThroughDestinations;
    }

    @Override
    public String toString() {
        return "\nSoferul{" +
                "name='" + name + '\'' + '}'+"\n";
    }
}


