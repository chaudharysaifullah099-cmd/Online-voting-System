package onlineVotingSystem.dao;
import onlineVotingSystem.Database.DatabaseConnection;
import java.util.*;
import java.sql.*;

public class AuditDao {
    public static void log(String event, String detail) {
        try {
            Connection con=DatabaseConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(
                    "INSERT INTO AuditLog (event, detail) VALUES (?, ?)"
            );
            ps.setString(1, event);
            ps.setString(2, detail);
            ps.executeUpdate();
        }
        catch (Exception e) {
           e.printStackTrace();
            System.out.println("Event not added");
        }
    }
}
