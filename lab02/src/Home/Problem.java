package Home;

import java.util.ArrayList;
import java.util.List;

public class Problem {
    private Depot[] depots;
    private Client[] clients;

    public Problem(Depot[] depots, Client[] clients) {
        this.depots = depots;
        this.clients = clients;
    }

    public Vehicle[] getVehicles() {
        List<Vehicle> allVehicles = new ArrayList<>();
        for (Depot depot : depots) {
            for (Vehicle vehicle : depot.getVehicles()) {
                allVehicles.add(vehicle);
            }
        }
        return allVehicles.toArray(new Vehicle[0]);
    }

    public List<Tour> allocateClients() {
        Vehicle[] vehicles = getVehicles();
        List<Tour> tours = new ArrayList<>();

        for (int i = 0; i < clients.length; i++) {
            if (i < vehicles.length) {
                tours.add(new Tour(vehicles[i], new Client[] {clients[i]}));
            }
        }

        return tours;
    }
}
