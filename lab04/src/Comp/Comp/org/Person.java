package Comp;
import java.util.Objects;

public abstract class Person {
    private String name;
    private int age;
    private String destination;

    public Person(String name, int age, String destination) {
        this.name = name;
        this.age = age;
        this.destination = destination;
    }
    public abstract String getTypeP();
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(destination, person.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, destination);
    }


    @Override
    public String toString() {
        return "Person-> Name: " + getName() + ", "
                + "Age: " + getAge() + ", "
                + "Destination: " + getDestination() + "\n";
    }
}
