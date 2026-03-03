import java.util.Scanner;
import java.sql.*;

public class BusTicketBooking {
    

    static String url = "jdbc:mysql://localhost:3306/busbooking";
    static final String userName = "root";
    static final String password = "root";
    private static int total_seats = 20;
    static boolean seats[] = new boolean[total_seats];

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the bus booking system");

        while(true) {
            System.out.println("\n\nMenu Options");
            System.out.println("1. Book Ticket");
            System.out.println("2. Display Available Tickets");
            System.out.println("3. Exit");

            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();

            switch(choice) {
                case 1: bookTicket();
                        break;
                case 2: displayAvailableSeats();
                        break;
                case 3: 
                        System.out.println("Thank You for using Booking System\n \n");
                        scanner.close();
                        System.exit(0);
                default: System.out.println("\nEnter a valid option from the menu");

            }

        }

        
    }

    private static void bookTicket() {
        try (
            Connection con = DriverManager.getConnection(url, userName, password);
        ) {
            String selectQuery = "SELECT seatNum FROM seats WHERE is_booked = false LIMIT 1";
            PreparedStatement ps = con.prepareStatement(selectQuery);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                int seat = rs.getInt("seatNum");

                String updateQuery = "UPDATE seats SET is_booked = true WHERE seatNum = ?";
                PreparedStatement ps2 = con.prepareStatement(updateQuery);
                ps2.setInt(1, seat);
                ps2.executeUpdate();
                System.out.println("Your ticket is booked successfully");
                System.out.println("Your ticket number is "+ seat);
            }
            else System.out.println("Sorry No seat is available the bus is full");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayAvailableSeats() {
        try (
            Connection con = DriverManager.getConnection(url, userName, password);
        ){
            String selectQuery = "SELECT seatNum FROM seats WHERE is_booked = false";
            PreparedStatement ps = con.prepareStatement(selectQuery);
            ResultSet rs = ps.executeQuery();

            System.out.println("Available Seats are: ");
            while (rs.next()) {
                System.out.print(rs.getInt("seatNum")+" ");
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
