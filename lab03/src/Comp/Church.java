package Comp;

public class Church extends Attraction implements Visitable {
    private String openingHours;

    public Church(String name, String description) {
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