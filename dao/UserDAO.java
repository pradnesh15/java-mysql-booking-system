package BusTicketBooking.dao;

import BusTicketBooking.db.DBConnection;
import java.sql.*;

public class UserDAO {
    public static int Login(String userName, String pass) {
        try (Connection con = DBConnection.getConnection()) {
            String query1 = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps1 = con.prepareStatement(query1);
            ps1.setString(1,userName);
            ps1.setString(2,pass);
            ResultSet rs1 = ps1.executeQuery();
            if(rs1.next()) {
                System.out.println("Login Successful");
                System.out.println("Welcome " + rs1.getString("username") + " your user ID is "+ rs1.getInt("user_id"));
                return rs1.getInt("user_id");    
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
}
