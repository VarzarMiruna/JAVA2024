package Comp;


public abstract class Attraction implements Comparable<Attraction> {
    protected String name;
    protected String description;

    public Attraction(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }

    // Implement compareTo to allow natural ordering by name
    @Override
    public int compareTo(Attraction other) {
        return this.name.compareTo(other.name);
    }
}
