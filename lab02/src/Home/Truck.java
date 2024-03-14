package Home;

public class Truck extends Vehicle {
    private int capacity;

    public Truck(String name, int capacity) {
        super(name);
        this.capacity = capacity;
    }

    public int getCapacity() {

        return capacity;
    }
    public void setCapacity(int capacity) {

        this.capacity=capacity;
    }
}
