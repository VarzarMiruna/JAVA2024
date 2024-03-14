package Home;

public class Main {
    public static void main(String[] args) {

        // Create vehicles
        Truck truck1 = new Truck("Truck1", 5000); // name capacity
        Drone drone1 = new Drone("Drone1", 2); // name and max flight duration
        System.out.println(truck1);
        System.out.println(drone1);
        // Create depots and assign vehicles
        Depot depot1 = new Depot("Depot1");
        depot1.setVehicles(new Vehicle[]{truck1}); // Assuming setVehicles accepts an array of Vehicles
        System.out.println(depot1);
        Depot depot2 = new Depot("Depot2");
        depot2.setVehicles(new Vehicle[]{drone1});
        System.out.println(depot2);
        // Create clients
        Client client1 = new Client("Client1");
        Client client2 = new Client("Client2");
        System.out.println(client1);
        System.out.println(client2);
        // Initialize the problem with depots and clients
        Problem pb = new Problem(new Depot[]{depot1, depot2}, new Client[]{client1, client2});

        // Perform allocation
        java.util.List<Tour> tours = pb.allocateClients();


    }
}
