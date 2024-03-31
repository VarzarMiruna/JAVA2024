package Bonus;

public abstract class Person {
    protected String name;
    protected Destination destination;

    public Person(String name, Destination destination) {
        this.name = name;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public Destination getDestination() {
        return destination;
    }
}
