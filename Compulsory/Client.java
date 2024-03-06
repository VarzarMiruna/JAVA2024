package Compulsory;

import Comp.ClientType;

public class Client {
    private String name;
    private String visitingTimeInterval; // This could be more complex, depending on how you wish to represent time intervals
    private ClientType type;

    public Client(String name, String visitingTimeInterval, ClientType type) {
        this.name = name;
        this.visitingTimeInterval = visitingTimeInterval;
        this.type = type;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisitingTimeInterval() {
        return visitingTimeInterval;
    }

    public void setVisitingTimeInterval(String visitingTimeInterval) {
        this.visitingTimeInterval = visitingTimeInterval;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }
}
