package Comp;

public class Driver extends Person{
    Driver(String name, int age, String destination)
    {
        super(name, age, destination);
    }

    public String getTypeP() {  return "Driver"; }

    @Override
    public String toString() {
        return "Driver-> Name: " + getName() + "--"
                + "Age: " + getAge() + "--"
                + "Destination: " + getDestination()  + "\n";
    }
}
