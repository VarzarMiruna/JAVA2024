package Comp;

import java.util.List;

public class Trip {
    private String city;
    private String period;
    private List<Attraction> attractions;

    public Trip(String city, String period, List<Attraction> attractions) {
        this.city = city;
        this.period = period;
        this.attractions = attractions;
    }

    public String getCity() {
        return city;
    }

    public String getPeriod() {
        return period;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }
}

