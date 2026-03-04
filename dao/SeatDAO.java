package BusTicketBooking.dao;
import BusTicketBooking.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SeatDAO {
    public static void bookTicket(int seat, int bus_id, String name, String phone) {
        try (
            Connection con = DBConnection.getConnection();
        ) {
            String selectQ = "SELECT is_booked FROM seats WHERE seatNum=?";
            PreparedStatement ps1 = con.prepareStatement(selectQ);
            ps1.setInt(1, seat);
            ResultSet rs1 = ps1.executeQuery();

            if(rs1.next()) {
                if(rs1.getBoolean("is_booked")) {
                    System.out.println("The seat is already booked");
                    return;
                }

                String updateQuery = "UPDATE seats SET passenger_name=?, passenger_phone=?, is_booked=true WHERE seatNum = ? AND bus_id = ?";
                PreparedStatement ps2 = con.prepareStatement(updateQuery);
                ps2.setString(1, name);
                ps2.setString(2, phone);
                ps2.setInt(3, seat);
                ps2.setInt(4, bus_id);
            
                ps2.executeUpdate();
                ps2.close();
                System.out.println("\nYour seat is booked successfully");
            }

            else System.out.println("Sorry No seat is available the bus is full");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void displayAvailableSeats(int bus_id) {
        try (
            Connection con = DBConnection.getConnection();
        ){
            String selectQuery = "SELECT seatNum FROM seats WHERE is_booked = false AND bus_id=?";
            PreparedStatement ps = con.prepareStatement(selectQuery);
            ps.setInt(1, bus_id);
            ResultSet rs = ps.executeQuery();

            System.out.println("Available seats are for the bus ID "+ bus_id +": ");
            while (rs.next()) {
                System.out.print(rs.getInt("seatNum")+" ");
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    public static void cancel(int bus_id, int seatNum) {
        

        try(Connection con = DBConnection.getConnection()) {
            String query = "UPDATE seats SET passenger_name =?, passenger_phone=?, is_booked=false WHERE seatNum = ? AND bus_id=?";
            PreparedStatement ps1 = con.prepareStatement(query);
            ps1.setString(1, null);
            ps1.setString(2, null);
            ps1.setInt(3, seatNum);
            ps1.setInt(4, bus_id);

            ps1.executeUpdate();
            ps1.close();
            System.out.println("You have cancelled the ticket with the seat number "+ seatNum);
            
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
                System.out.print("\nBus ID " + rs.getInt("bus_id")+" | " + rs.getString("source")+" -> " + rs.getString("destination"));
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
