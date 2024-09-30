public class MarinaReservation extends Reservation{
    @Override
    public String applicationName() {
        return "Marina Reservation System";
    }

    @Override
    public double totalPrice(int days, boolean doubleSpace) {
        return doubleSpace? days * 1000 * 2 : days;
    }

    @Override
    public boolean isSideBySideReservationAllowed() {
        return true;
    }

    @Override
    public String spaceType(){
        return "Dock";
    }
}
