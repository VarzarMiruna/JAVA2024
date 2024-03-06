package Compulsory;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String id;
    private Depot depot;
    private List<Client> clients; // List to keep track of clients assigned to this vehicle

    public Vehicle(String id, Depot depot) {
        this.id = id;
        this.depot = depot;
        this.clients = new ArrayList<>();
    }

    // Methods to add and remove clients
    public void addClient(Client client) {
        clients.add(client);
    }

    public boolean removeClient(Client client) {
        return clients.remove(client);
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    public List<Client> getClients() {
        return clients;
    }
}
