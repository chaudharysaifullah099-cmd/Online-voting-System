package onlineVotingSystem.dao;
import java.sql.*;
import onlineVotingSystem.Database.DatabaseConnection;

public class AdminDao {
    public static String getPasswordHash(String username) throws Exception {
        Connection con = DatabaseConnection.getConnection();

        PreparedStatement ps = con.prepareStatement(
                "SELECT password FROM Admin WHERE username = ?"
        );
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("password");   // <-- changed here
        }

        return null;
    }
}
