package onlineVotingSystem.model;

public class Voter {
    private int id;
    private String name;
    private String cnic;
    private String password;   // plain text password (no hashing)
    private boolean hasVoted;

    // Constructor for loading voter from database
    public Voter(String name, String cnic, String password,int id,boolean hasVoted) {
        this.id=id;
        this.name=name;
        this.cnic=cnic;
        this.password=password;
        this.hasVoted=hasVoted;
    }

    public Voter(String name, String cnic, String password) {
        this.name=name;
        this.cnic=cnic;
        this.password=password;
    }

    // Getters
    public int getId(){
        return id;
    }
    public String getName(){

        return name;
    }
    public String getCnic(){

        return cnic;
    }
    public String getPassword(){
        return password;
    }
    public boolean isHasVoted(){
        return hasVoted;
    }

    // Setters
    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setCnic(String cnic){
        this.cnic=cnic;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setHasVoted(boolean hasVoted){
        this.hasVoted=hasVoted;
    }
}
