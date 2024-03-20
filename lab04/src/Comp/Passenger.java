package Comp;

public class Passenger extends Person implements Comparable<Passenger> {

    Passenger (String name, int age, String destination){
        super(name, age, destination);
    }
    public String getTypeP(){
        return "Passenger";
    }

    @Override
    public int compareTo(Passenger Pers){
        return this.getName().compareTo(Pers.getName());
    }
    @Override
    public String toString() {
        return "Passenger-> name: " + getName() + "--"
                + "Age: " + getAge() + "--"
                + "Destination: " + getDestination() + "\n";
    }
}
