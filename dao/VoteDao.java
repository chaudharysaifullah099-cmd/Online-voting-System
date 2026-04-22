package onlineVotingSystem.dao;
import onlineVotingSystem.Database.DatabaseConnection;
import java.util.*;
import java.sql.*;

public class VoteDao {
    public static void castVote(int voterId, int electionId, int candidateId) throws Exception {
        Connection con=DatabaseConnection.getConnection();

        con.setAutoCommit(false);

        try {
            PreparedStatement ps=con.prepareStatement(
                    "INSERT INTO Vote (voter_id, election_id, candidate_id) VALUES (?, ?, ?)"
            );
            ps.setInt(1, voterId);
            ps.setInt(2, electionId);
            ps.setInt(3, candidateId);
            ps.executeUpdate();

            PreparedStatement update=con.prepareStatement(
                    "UPDATE Candidate SET votes = votes + 1 WHERE id = ?"
            );
            update.setInt(1, candidateId);
            update.executeUpdate();

            con.commit();

        } catch (Exception ex) {
            con.rollback();
            throw ex;
        }
    }
}
