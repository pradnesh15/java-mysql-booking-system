package BusTicketBooking.main;

import BusTicketBooking.dao.SeatDAO;
import java.util.Scanner;
public class BusTicketBooking {
    
    private static int total_seats = 20;
    static boolean seats[] = new boolean[total_seats];

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the bus booking system");

        

        while(true) {
            System.out.println("\n\nMenu Options");
            System.out.println("1. Search Bus");
            System.out.println("2. View my bookings (Feature yet to come)");
            System.out.println("3. Cancel the ticket");
            System.out.println("4. Exit");

            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice) {
                case 1: 
                        SeatDAO.displayAvailableBuses();
                        System.out.print("Choose the Bus ID: ");
                        int busId = scanner.nextInt();
                        

                        SeatDAO.displayAvailableSeats(busId);
                        

                        System.out.print("Choose the seat you want to book: ");
                        int seat = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.print("Enter the name of the passenger: ");
                        String name = scanner.nextLine();
                        
                        System.out.print("Enter the phone number of the passenger: ");
                        String phone = scanner.nextLine();
                        SeatDAO.bookTicket(seat, busId, name, phone);
                        break;

                case 2: 
                        // SeatDAO.displayAvailableSeats();
                        break;

                case 3: 
                        System.out.println("Currently we have 9 buses active: ");
                        SeatDAO.displayAvailableBuses();
                        System.out.print("\nEnter the bus ID:");
                        int cancelbusId = scanner.nextInt();
                        if(cancelbusId >= 1 && cancelbusId <=9) {
                            System.out.print("Enter the seat number: ");
                            int cancelSeat = scanner.nextInt();
                            SeatDAO.cancel(cancelbusId, cancelSeat);
                        } 
                        break;
                case 4: 
                        System.out.println("Thank You for using Booking System\n \n");
                        scanner.close();
                        System.exit(0);
                        
                default: System.out.println("\nEnter a valid option from the menu");

            }

        }

        
    }

    

    

}
