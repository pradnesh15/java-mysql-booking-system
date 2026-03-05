package BusTicketBooking.dao;
import BusTicketBooking.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SeatDAO {

    public static void displayAvailableSeats(int bus_id) {
        try (
            Connection con = DBConnection.getConnection();
        ){
            // String selectQuery = "SELECT seatNum FROM seats WHERE bus_id=? AND seatNum NOT IN (SELECT seatNum FROM bookings)";
            boolean [] seats = new boolean[21];
            String selectQuery = "SELECT seatNum FROM bookings WHERE bus_id=?";
            PreparedStatement ps = con.prepareStatement(selectQuery);
            ps.setInt(1, bus_id);
            ResultSet rs = ps.executeQuery();
            
            System.out.println("Available seats are for the bus ID "+ bus_id);
            while (rs.next()) {
                int st = rs.getInt("seatNum");
                seats[st] = true;
                // System.out.print(rs.getInt("seatNum")+" ");
            }
            for(int i=1;i<21;i++) {
                if(seats[i]) continue; 
                else System.out.print(i +" ");
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayAvailableBuses() {
        try (
            Connection con = DBConnection.getConnection();
        ){
            String selectQuery = "SELECT * FROM buses";
            PreparedStatement ps = con.prepareStatement(selectQuery);
            ResultSet rs = ps.executeQuery();

            System.out.println("Available Buses are: ");
            
            while (rs.next()) {
                System.out.print("\nBus ID " + rs.getInt("bus_id")+" | " + rs.getString("source")+" -> " + rs.getString("destination") + " | " + rs.getString("departure_time"));
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public static void displayAvailableBookings(int user_id) {
        try (
            Connection con = DBConnection.getConnection();
        ){

            String selectQuery2 = """
                SELECT * 
                FROM buses bs
                JOIN bookings bo ON bs.bus_id = bo.bus_id  
                WHERE bo.user_id=?""";
            PreparedStatement ps2 = con.prepareStatement(selectQuery2);
            ps2.setInt(1, user_id);
            ResultSet rs2 = ps2.executeQuery();

            System.out.println("Available Bookings are: ");
            boolean flag = false;
            while (rs2.next()) {
                flag = true;
                System.out.println("\nBus ID " + rs2.getInt("bus_id")+" | Seat Number "+rs2.getInt("seatNum") +" | Departure at " + rs2.getString("departure_time")+" | "+ rs2.getString("source")+" -> " + rs2.getString("destination") + " | " + rs2.getString("booking_timestamp"));
            }
            if(!flag) System.out.println("0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


