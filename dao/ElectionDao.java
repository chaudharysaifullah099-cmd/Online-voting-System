package onlineVotingSystem.dao;
import onlineVotingSystem.Database.DatabaseConnection;
import onlineVotingSystem.model.Election;
import java.sql.*;
import java.util.*;

public class ElectionDao {
    public static int createElection(String title) throws Exception {
        Connection con=DatabaseConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(
                "INSERT INTO Election (title) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS
        );
        ps.setString(1, title);
        ps.executeUpdate();

        ResultSet rs=ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }

    public static List<Election> getAll() throws Exception {
        List<Election> list = new ArrayList<>();
        Connection con = DatabaseConnection.getConnection();

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Election");

        while (rs.next()) {
            list.add(new Election(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("status")
            ));
        }
        return list;
    }
}
