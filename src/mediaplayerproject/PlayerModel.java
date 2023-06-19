/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mediaplayerproject;

/**
 *
 * @author Py0095
 */
class PlayerModel {
//    private static int counter = 0; // Pour l'ID qui s'incrémente
    public int id;
    public String fileName;
    public String absolutePath;
    
   
        public PlayerModel(int id ,String fileName, String absolutePath) {
//        this.id = counter++;
        this.id = id; 
        this.fileName = fileName;
        this.absolutePath = absolutePath;
    }

    public int getId() {
        return id;
    }
    
     public void setId(int id) {
        this.id = id;
    }
    
    public String getNom() {
        return fileName;
    }

    public void setNom(String fileName) {
        this.fileName = fileName;
    }

    public String getAbsolutepath() {
        return absolutePath;
    }

    public void Absolutepath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    
}
