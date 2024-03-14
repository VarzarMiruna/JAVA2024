package Comp;

public class Depot {
    private String name;

    public Depot(String name) {
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Depot{" + "name='" + name + '\'' + '}';
    }
}

