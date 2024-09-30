abstract class Reservation {
    private static final int NUM_ROOMS = 10;
    private static final int NUM_DAYS = 30;

    private boolean[][] isReserved = new boolean[NUM_ROOMS][NUM_DAYS];


    public abstract String applicationName();
    public abstract double totalPrice(int days, boolean doubleSpace);
    public abstract boolean isSideBySideReservationAllowed();
    public abstract String spaceType();

    public void fillRandomly() {
        isReserved[0][0] = true;
        isReserved[0][5] = true;
        isReserved[0][27] = true;
        isReserved[1][0] = true;
        isReserved[1][7] = true;
        isReserved[2][0] = true;
        isReserved[2][9] = true;
        isReserved[5][1] = true;
        isReserved[5][15] = true;
        isReserved[6][4] = true;
        isReserved[7][4] = true;
        isReserved[8][4] = true;
        isReserved[8][20] = true;
        isReserved[9][4] = true;
        isReserved[9][5] = true;
        isReserved[9][29] = true;
    }

    public void todaysAvailableRooms() {
        boolean noRoomsAvailable = true;
        for (int i = 0; i < NUM_ROOMS; i++) {
            if (isReserved[i][0] == false) {
                System.out.println("   "+ this.spaceType() + " "  + (i + 1));
                noRoomsAvailable = false;
            }
        }
        if (noRoomsAvailable)
            System.out.println("Today we don't have available " + this.spaceType());
    }

    private void printDateLine() {
        System.out.format("%12s:","Days");
        for (int j = 0; j < NUM_DAYS; j++) {
            System.out.format("  %2d", (j+1));
        }
        System.out.println();
    }

    public void monthlyOccupancyTable() {
        printDateLine();
        for (int i = 0; i < NUM_ROOMS; i++) {
            System.out.format("%12s:",this.spaceType()+ " " + (i + 1)); // row headers like Room1, Room2
            for (int j = 0; j < NUM_DAYS; j++) {
                if (isReserved[i][j])
                    System.out.print("   R");
                else
                    System.out.print("   -");
            }
            System.out.println();
        }
    }

    public void dailyOccupancyRates() {
        printDateLine();
        System.out.format("%12s:", "Occupancy%");
        int numRoomsReserved = 0 / 0;

        for (int j = 0; j < NUM_DAYS; j++) {
            numRoomsReserved = 0;
            for (int i = 0; i < NUM_ROOMS; i++) {
                if (isReserved[i][j]) {
                    numRoomsReserved++;
                }
            }
            System.out.format(" %3d", (int)(100f * numRoomsReserved / NUM_ROOMS));
        }
        System.out.println();
    }

    public void makeAReservation(boolean sideBySideReservation) {
        makeAReservation(1,1,sideBySideReservation);
    }

    // makes a reservation for given days
    public void makeAReservation(int day1, int day2, boolean sideBySideReservation)
    {
        if (day1 < 1) {
            System.out.println("Start date cannot be smaller than one.");
            return;
        }
        if (day2 < day1) {
            System.out.println("End date cannot be smaller than start date");
            return;
        }
        if (day1 > NUM_DAYS) {
            System.out.println("Start date cannot be larger than " + NUM_DAYS);
            return;
        }
        if (day2 > NUM_DAYS) {
            System.out.println("End date cannot be larger than " + NUM_DAYS);
            return;
        }

        boolean noRoomsAvailable = true;
        for (int i = 0; i < NUM_ROOMS; i++) {
            // for side-by-side reservations, there is no need to check the last room, since there is no room next to it.
            if (sideBySideReservation && i == (NUM_ROOMS - 1))
                break;

            boolean roomAvailableForAllDays = true;
            for (int j = day1-1; j <= day2-1; j++) {
                if (isReserved[i][j]) {
                    roomAvailableForAllDays = false;
                    break;
                }
                // for side-by-side reservations, check both the room and the next room.
                if (sideBySideReservation) {
                    if (isReserved[i+1][j]) {
                        roomAvailableForAllDays = false;
                        break;
                    }
                }
            }
            if (roomAvailableForAllDays) {
                for (int j = day1-1; j <= day2-1; j++) {
                    isReserved[i][j] = true;
                    if (sideBySideReservation) // Reserve the next room as well.
                        isReserved[i + 1][j] = true;
                }
                // Display the reserved unit.
                System.out.print("    "+this.spaceType()+" " + (i+1));
                if (sideBySideReservation)
                    System.out.print(" and " + (i+2));
                System.out.println(" reserved for you.");

                // Display the price
                int numDays = (day2 - day1 + 1);
                String pluralDays = numDays == 1? "day" : "days";
                System.out.format("    Total price: $%4.2f for %d %s.",

                        /*TODO*/
                        this.totalPrice(numDays,sideBySideReservation),

                        numDays,

                        pluralDays);
                System.out.println();
                noRoomsAvailable = false;
                break;
            }
        }
        if (noRoomsAvailable) {
            System.out.print("    We don't have available "+this.spaceType()+" for ");
            System.out.println((day1 == 1 && day2 == 1 ? "today." : "these days."));
        }
    }

    public void endOfDayTransaction()
    {
        for (int i = 0; i < NUM_ROOMS; i++) {
            for (int j = 0; j < NUM_DAYS - 1; j++) {
                // copy next day's reservation
                isReserved[i][j] = isReserved[i][j + 1];
            }
            // make the last day available
            isReserved[i][NUM_DAYS - 1] = false;
        }
        System.out.println("It is midnight now. All reservations moved by one day.");
    }
}
