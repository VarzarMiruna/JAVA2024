package Comp;


public class Statue extends Attraction implements Visitable {
    private String openingHours;

    public Statue(String name, String description) {
        super(name, description);
    }

    @Override
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    @Override
    public String getOpeningHours() {
        return openingHours;
    }
}




