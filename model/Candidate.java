package onlineVotingSystem.model;

public class Candidate {
    private int id;
    private int electionId;
    private String name;
    private String party;
    private int votes;
// Constrctor with id,electionId,name,party,votes
    public Candidate(int id,int electionId,String name,String party,int votes) {
        this.id=id;
        this.electionId=electionId;
        this.name=name;
        this.party=party;
        this.votes=votes;
    }
//    Setter
    public void setId(int id){
        this.id=id;
    }

    public void setElectionId(int electionId) {
        this.electionId = electionId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setParty(String party){
        this.party = party;
    }

    public void setVotes(int votes){
        this.votes = votes;
    }
//Getter
    public int getId(){
        return id;
    }
    public int getElectionId(){
        return electionId;
    }
    public String getName(){
        return name;
    }
    public String getParty() {
        return party;
    }
    public int getVotes() {
        return votes;
    }

}
