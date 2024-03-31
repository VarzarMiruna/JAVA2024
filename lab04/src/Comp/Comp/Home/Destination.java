package Home;

public class Destination {
    private String name;

    public Destination(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "\nDestinatia{" +
                "name='" + name + '\'' +
                '}'+"\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
