package Home;

public class Passenger extends Person implements Comparable<Passenger> {
    public Passenger(String name, Destination destination) {
        super(name, destination);
    }
        @Override
    public int compareTo(Passenger other) {
        return this.getName().compareTo(other.getName());
    }

    @Override
    public String toString() {
        return "\nPasagerul{" +
                "name='" + name + '\'' + '}'+ "\n";
    }
}
