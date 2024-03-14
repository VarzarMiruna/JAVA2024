package Home;

public class Drone extends Vehicle {
    private int maxFlightDuration;

    public Drone(String name, int maxFlightDuration) {
        super(name);
        this.maxFlightDuration = maxFlightDuration;
    }

    public int getMaxFlightDuration() {

        return maxFlightDuration;
    }
    public void setMaxFlightDuration(int maxFlightDuration) {

        this.maxFlightDuration=maxFlightDuration;
    }
}
