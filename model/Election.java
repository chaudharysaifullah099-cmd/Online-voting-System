package onlineVotingSystem.model;

public class Election {
    private int id;
    private String title;
    private String status;

    // Constructor with id, title, status
    public Election(int id, String title, String status){
        this.id=id;
        this.title=title;
        this.status=status;
    }

    // Setters
    public void setId(int id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setStatus(String status){
        this.status = status;
    }
    // Getters
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getStatus(){
        return status;
    }

}
