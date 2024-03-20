package Comp;
/**
 @author Varzar Alina-Miruna **/
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;


public class Main {
    public static void main(String[] args) {

        Driver driver1 = new Driver("Miruna1", 20, "Dest1");
        Driver driver2 = new Driver("Miruna2", 30, "Dest2");
        Driver driver3 = new Driver("Miruna3", 22, "dest3");


        Passenger passenger1 = new Passenger("Alina", 28, "Dest1");
        Passenger passenger2 = new Passenger("Horia", 32, "Dest2");
        Passenger passenger3 = new Passenger("Ema", 27, "Dest3");


        LinkedList<Driver> drivers = new LinkedList<>();
        drivers.add(driver1);
        drivers.add(driver2);
        drivers.add(driver3);

        Collections.sort(drivers, Comparator.comparingInt(Driver::getAge));
        
        System.out.println("Drivers:");
        for (Driver driver : drivers) {
            System.out.println(driver);
        }

        TreeSet<Passenger> passengers = new TreeSet<>();
        passengers.add(passenger1);
        passengers.add(passenger2);
        passengers.add(passenger3);

        System.out.println("Passengers:");
        for (Passenger passenger : passengers) {
            System.out.println(passenger);
        }
    }
}
