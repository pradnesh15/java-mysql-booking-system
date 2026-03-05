package BusTicketBooking.main;

import BusTicketBooking.dao.BookingDAO;
import BusTicketBooking.dao.SeatDAO;
import BusTicketBooking.dao.UserDAO;


import java.util.Scanner;
public class BusTicketBooking {
    
    private static int total_seats = 20;
    static boolean seats[] = new boolean[total_seats];

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        int userId = 0;
        while(true) {
            
            if(userId == 0) {
                System.out.println("Welcome to the bus booking system");
                System.out.println("Login: ");

                System.out.print("Enter user Name: ");
                final String userName = scanner.nextLine();
                System.out.print("Enter password: ");
                String pass = scanner.nextLine();   
                userId = UserDAO.Login(userName, pass);
            }

            if(userId == -1) {
                System.out.println("Username or password is wrong");
                System.out.println("Try Again\n");
                userId = 0;
            }
            else {


                System.out.println("\n\nMenu Options");
                System.out.println("1. Book Ticket");
                System.out.println("2. View my bookings");
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
                            BookingDAO.bookTicket(seat, busId, userId, name, phone);
                            break;

                    case 2: 
                            SeatDAO.displayAvailableBookings(userId);
                            break;

                    case 3: while(true){
                                System.out.println("Currently we have 9 buses active: ");
                                SeatDAO.displayAvailableBuses();
                                System.out.println();
                                System.out.print("\nEnter the bus ID:");
                                int cancelbusId = scanner.nextInt();
                                if(cancelbusId >= 101 && cancelbusId <=109) {
                                    System.out.print("Enter the seat number: ");
                                    int cancelSeat = scanner.nextInt();
                                    BookingDAO.cancel(userId, cancelbusId, cancelSeat);
                                    break;
                                } 
                                else System.out.println("Enter a valid bus ID");;
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
}
