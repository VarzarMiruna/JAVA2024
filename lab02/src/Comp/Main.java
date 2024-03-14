package Comp;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("Miruna", ClientType.PREMIUM);
        Vehicle vehicle = new Vehicle("Truck");
        Depot depot = new Depot("Depot 1");

        System.out.println(client);
        System.out.println(vehicle);
        System.out.println(depot);
    }
}

