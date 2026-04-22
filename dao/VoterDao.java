package onlineVotingSystem.dao;

import onlineVotingSystem.model.Voter;
import onlineVotingSystem.Database.DatabaseConnection;
import java.sql.*;

public class VoterDao {

    public static boolean register(Voter v) throws Exception {
        Connection con=DatabaseConnection.getConnection();

        // Check if CNIC already exists
        PreparedStatement check = con.prepareStatement(
                "SELECT id FROM Voter WHERE cnic = ?"
        );
        check.setString(1, v.getCnic());
        ResultSet rs = check.executeQuery();

        if (rs.next()) {
            return false; // CNIC already registered
        }

        // Insert new voter (plain password)
        PreparedStatement ps=con.prepareStatement(
                "INSERT INTO Voter (name, cnic, password, hasVoted) VALUES (?, ?, ?, FALSE)"
        );
        ps.setString(1, v.getName());
        ps.setString(2, v.getCnic());
        ps.setString(3, v.getPassword());

        ps.executeUpdate();
        return true;
    }

    public static boolean verifyLogin(String cnic, String password) throws Exception {
        Connection con=DatabaseConnection.getConnection();

        PreparedStatement ps=con.prepareStatement(
                "SELECT password FROM Voter WHERE cnic = ?"
        );
        ps.setString(1, cnic);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String realPassword=rs.getString("password");
            return realPassword.equals(password); // plain text compare
        }

        return false;
    }

    public static boolean hasVoted(String cnic) throws Exception {
        Connection con = DatabaseConnection.getConnection();

        PreparedStatement ps=con.prepareStatement(
                "SELECT hasVoted FROM Voter WHERE cnic = ?"
        );
        ps.setString(1, cnic);

        ResultSet rs=ps.executeQuery();
        return rs.next() && rs.getBoolean("hasVoted");
    }

    public static int getVoterId(String cnic) throws Exception {
        Connection con = DatabaseConnection.getConnection();

        PreparedStatement ps=con.prepareStatement(
                "SELECT id FROM Voter WHERE cnic = ?"
        );
        ps.setString(1, cnic);

        ResultSet rs=ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }

        return -1;
    }

    public static void markVoted(int id) throws Exception {
        Connection con = DatabaseConnection.getConnection();

        PreparedStatement ps=con.prepareStatement(
                "UPDATE Voter SET hasVoted = TRUE WHERE id = ?"
        );
        ps.setInt(1, id);

        ps.executeUpdate();
    }
}
