package onlineVotingSystem.Services;
import onlineVotingSystem.model.Voter;
import onlineVotingSystem.dao.*;
public class VoterServices {
    public static boolean register(String name, String cnic, String password) throws Exception {
        Voter v=new Voter(name, cnic, password);

        boolean ok=VoterDao.register(v);
        if (ok) {
            AuditDao.log("Register", cnic);
        }

        return ok;
    }

    public static boolean login(String cnic, String password) throws Exception {
        boolean ok=VoterDao.verifyLogin(cnic, password);

        if (ok) {
            AuditDao.log("Voter Login", cnic);
        }
        return ok;
    }

    public static void vote(String cnic, int electionId, int candidateId) throws Exception {

        int voterId=VoterDao.getVoterId(cnic);
        VoteDao.castVote(voterId, electionId, candidateId);
        VoterDao.markVoted(voterId);

        AuditDao.log("Cast Vote", cnic);
    }
}
