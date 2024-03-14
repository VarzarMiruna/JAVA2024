package Comp;


public class Concert extends Attraction implements Visitable, Payable {
    private String openingHours;
    private double entryFee;

    public Concert(String name, String description) {
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

    @Override
    public void setEntryFee(double fee) {
        this.entryFee = fee;
    }

    @Override
    public double getEntryFee() {
        return entryFee;
    }
}
