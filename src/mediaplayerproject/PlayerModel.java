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
    private int id;
    private String nom;
    private String Absolutepath;
        public PlayerModel(int id, String nom, String Absolutepath) {
        this.id = id;
        this.nom = nom;
        this.Absolutepath = Absolutepath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAbsolutepath() {
        return Absolutepath;
    }

    public void Absolutepath(String Absolutepath) {
        this.Absolutepath = Absolutepath;
    }

    
}
