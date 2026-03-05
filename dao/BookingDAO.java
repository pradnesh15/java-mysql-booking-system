package BusTicketBooking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import BusTicketBooking.db.DBConnection;

public class BookingDAO {
    public static void bookTicket(int seat, int bus_id, int user_id, String name, String phone) {
        try (
            Connection con = DBConnection.getConnection();
        ) {
            String selectQ = "SELECT * FROM bookings WHERE seatNum=? AND bus_id =?";
            PreparedStatement ps1 = con.prepareStatement(selectQ);
            ps1.setInt(1, seat);
            ps1.setInt(2, bus_id);
            ResultSet rs1 = ps1.executeQuery();

            if(rs1.next()) System.out.println("The seat is already booked");
            else {
                String insertQuery = "INSERT INTO bookings(user_id, bus_id, seatNum, passenger_name, passenger_phone) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement ps2 = con.prepareStatement(insertQuery);
                ps2.setInt(1, user_id);
                ps2.setInt(2, bus_id);
                ps2.setInt(3, seat);
                ps2.setString(4, name);
                ps2.setString(5, phone);
            
                ps2.executeUpdate();
                ps2.close();
                System.out.println("\nYour seat is booked successfully");
                
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

        
    public static void cancel(int user_id, int busId, int seatNum) {
        

        try(Connection con = DBConnection.getConnection()) {
            String query = "DELETE FROM bookings WHERE user_id =? AND seatNum=? AND bus_id=?";
            PreparedStatement ps1 = con.prepareStatement(query);
            ps1.setInt(1, user_id);
            ps1.setInt(2, seatNum);
            ps1.setInt(3, busId);


            int r = ps1.executeUpdate();
            if(r > 0) System.out.println("You have cancelled the ticket with the seat number "+ seatNum + " for the bus "+ busId);
            else System.out.println("No booking found");
            ps1.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
