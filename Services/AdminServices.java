package onlineVotingSystem.Services;
import java.util.*;
import onlineVotingSystem.dao.*;


public class AdminServices{
    public static boolean login(String username, String password) throws Exception {
        String realPassword=AdminDao.getPasswordHash(username);

        if (realPassword==null){
            return false;
        }


        boolean ok=password.equals(realPassword);

        if (ok){
            AuditDao.log("Admin Login", username);
        }

        return ok;
    }

    public static int createElection(String title) throws Exception {
        int id=ElectionDao.createElection(title);
        AuditDao.log("Create Election", title);
        return id;
    }

    public static void addCandidate(int electionId, String name, String party) throws Exception {
        CandidateDao.addCandidate(electionId, name, party);
        AuditDao.log("Add Candidate", name);
    }

}
