package mediaplayerproject;

public class PlayerModel {
    public int id;
    public String fileName;
    
    public PlayerModel(int id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }  

}








