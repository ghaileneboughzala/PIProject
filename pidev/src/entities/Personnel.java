/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mondh
 */
public class Personnel {

    private int id, fonction_id ;
    private String nom, prenom, photo;

    public Personnel() {
    }

    public Personnel( int fonction_id, String nom, String prenom, String photo) {
       
        this.fonction_id = fonction_id;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
    }

    public Personnel(int id, int fonction_id, String nom, String prenom, String photo) {
        this.id = id;
        this.fonction_id = fonction_id;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFonction_id() {
        return fonction_id;
    }

    public void setFonction_id(int fonction_id) {
        this.fonction_id = fonction_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Personnel{" + "id=" + id + ", fonction_id=" + fonction_id + ", nom=" + nom + ", prenom=" + prenom + ", photo=" + photo + '}';
    }
    
 
}
