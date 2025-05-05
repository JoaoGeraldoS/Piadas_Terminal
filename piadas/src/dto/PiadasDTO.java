package dto;

public class PiadasDTO {
    private int id;
    private String type;
    private String setup;
    private String punchline;
 
    public void setId(int id) {
       this.id = id;
    }
 
    public int getId() {
       return this.id;
    }
 
    public void setType(String type) {
       this.type = type;
    }
 
    public String getType(){
       return this.type;
    }
 
    public void setSetup(String setup){
       this.setup = setup;
    }
 
    public String getSetup(){
       return this.setup;
    }
 
    public void setPunchline(String punchline) {
       this.punchline = punchline;
    }
    
    public String getPunchline() {
       return this.punchline;
    }

    
}
