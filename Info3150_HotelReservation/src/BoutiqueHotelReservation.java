public class BoutiqueHotelReservation extends Reservation {
    @Override
    public String applicationName(){
        return "Boutique Hotel Reservation System";
    }

    @Override
    public double totalPrice(int days, boolean doubleSpace){
        if(days > 5) return days * 90;
        else return days * 100;
    }

    @Override
    public boolean isSideBySideReservationAllowed() {
        return false;
    }

    @Override
    public String spaceType(){
        return "Room";
    }


}
