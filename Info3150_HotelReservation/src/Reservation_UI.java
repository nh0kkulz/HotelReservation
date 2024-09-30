import java.util.Scanner;

public class Reservation_UI {

	public static void main(String[] args) {

		Reservation reservation;

		reservation = new BoutiqueHotelReservation();
//		reservation = new MarinaReservation();
//		reservation = new CampingAreaReservation()

		
		reservation.fillRandomly();
		
		Scanner input = new Scanner(System.in);
		
		while (true) {
		    System.out.println("");
		    System.out.println(reservation.applicationName());
		    System.out.println("");
		    System.out.println("   1- Display available places today");
		    System.out.println("   2- Monthly reservation table");
		    System.out.println("   3- 30-day occupancy rates");
		    System.out.println("   4- Quick reservation for today");
		    System.out.println("   5- Reservation for a date range");

			if(reservation.isSideBySideReservationAllowed()) {
				System.out.println("   6- Side-by-side quick reservation for today");
				System.out.println("   7- Side-by-side reservation for a date range");
			}

		    System.out.println("   9- End of day transaction");
		
		    String usersChoice = input.nextLine();
		    if (usersChoice == "")
		    	continue;
            System.out.println();
		    
		    switch (usersChoice.charAt(0)) {
		        case '1': {
		                System.out.println("Today's available places");
		                reservation.todaysAvailableRooms();
		                break;
		            }
		        case '2': {
		                System.out.println("Monthly reservation table");
		                reservation.monthlyOccupancyTable();
		                break;
		            }
		        case '3': {
		                System.out.println("30-day occupancy rates");
		                reservation.dailyOccupancyRates();
		                break;
		            }
		        case '4': {
		                System.out.println("Quick reservation for today");
						reservation.makeAReservation(false);
		                break;
		            }
		        case '5': {
		                System.out.println("Reservation for a date range");
	                    System.out.print("Starting day (nn): ");
	                    int day1 = Integer.parseInt(input.nextLine());
	
	                    System.out.print("Ending day (nn): ");
	                    int day2 = Integer.parseInt(input.nextLine());

		                reservation.makeAReservation(day1, day2, false);
		                break;
		            }
				case '6': {
						System.out.println("Side-by-side quick reservation for today");
						reservation.makeAReservation(true);
				}
				case '7': {
						System.out.println("Side-by-side reservation for a date range");
						System.out.print("Starting day (nn): ");
						int day1 = Integer.parseInt(input.nextLine());

						System.out.print("Ending day (nn): ");
						int day2 = Integer.parseInt(input.nextLine());

						reservation.makeAReservation(day1, day2, true);
						break;
				}
		        case '9': {
	                    System.out.println("End of day transaction");
	                    reservation.endOfDayTransaction();
	                    break;
	                }
		    }
		}
	}
}
