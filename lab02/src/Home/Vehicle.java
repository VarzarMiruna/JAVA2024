package Home;

import java.util.Objects;

public abstract class Vehicle {
    protected String name;
    private Depot depot;
    public Vehicle(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName() { this.name=name; }
    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle vehicle = (Vehicle) obj;
        return name.equals(vehicle.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


