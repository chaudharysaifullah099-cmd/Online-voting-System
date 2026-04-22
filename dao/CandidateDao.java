package onlineVotingSystem.dao;
import onlineVotingSystem.model.Candidate;
import onlineVotingSystem.Database.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class CandidateDao {
    public static void addCandidate(int electionId, String name, String party) throws Exception {
        Connection con=DatabaseConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(
                "INSERT INTO Candidate (election_id, name, party) VALUES (?, ?, ?)"
        );

        ps.setInt(1, electionId);
        ps.setString(2, name);
        ps.setString(3, party);
        ps.executeUpdate();
    }

    public static List<Candidate> getByElection(int electionId) throws Exception {
        List<Candidate> list=new ArrayList<>();
        Connection con=DatabaseConnection.getConnection();

        PreparedStatement ps=con.prepareStatement(
                "SELECT * FROM Candidate WHERE election_id = ?"
        );
        ps.setInt(1, electionId);

        ResultSet rs=ps.executeQuery();
        while (rs.next()) {
            list.add(new Candidate(
                    rs.getInt("id"),
                    rs.getInt("election_id"),
                    rs.getString("name"),
                    rs.getString("party"),
                    rs.getInt("votes")
            ));
        }
        return list;
    }
}
