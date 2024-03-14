package Home;


import java.time.LocalTime;
import java.util.Objects;

import Home.ClientType;

public class Client {
    private String name;
    private LocalTime minTime;
    private LocalTime maxTime;

    private ClientType type;

    public Client(String name, ClientType type) {
        this.name = name;
        this.type = type;
    }

    public Client() { }
    public Client(String name) {
        this(name, null, null);
    }
    public Client(String name, LocalTime minTime, LocalTime maxTime) {
        this.name = name;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }
    public String getName() {
        return name;
    }

    public ClientType getType() {
        return type;
    }

    public LocalTime getMinTime(){
        return  minTime;
    }

    public LocalTime getMaxTime(){
        return  maxTime;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public void setMinTime(LocalTime minTime) {
        this.minTime = minTime;
    }
    public void setMaxTime(LocalTime maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Client client = (Client) obj;
        return name.equals(client.name);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

