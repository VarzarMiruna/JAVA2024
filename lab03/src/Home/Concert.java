package Home;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Concert extends Attraction implements Visitable, Payable {
    private Map<LocalDate, TimeInterval> timetable = new HashMap<>();
    private double ticketPrice;

    public Concert(String name, double ticketPrice) {
        super(name);
        this.ticketPrice = ticketPrice;
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return timetable;
    }

    @Override
    public double getTicketPrice() {
        return ticketPrice;
    }
}