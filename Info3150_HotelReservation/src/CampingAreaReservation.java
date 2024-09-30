public class CampingAreaReservation extends Reservation{

    @Override
    public String applicationName() {
        return "Campground Reservation System";
    }

    @Override
    public double totalPrice(int days, boolean doubleSpace) {
        return doubleSpace? 100 * days : 160 * days;
    }

    @Override
    public boolean isSideBySideReservationAllowed() {
        return true;
    }

    @Override
    public String spaceType() {
        return "Tent area";
    }

}
