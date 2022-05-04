/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author masmoudi
 */
public class Plat {
    private int id;
    private float prix;
    private String nom_p,description,img_p;
    private Boolean dispo;

    public Plat() {
    }

    public Plat(int id, float prix, String nom_p, String description, String img_p, Boolean dispo) {
        this.id = id;
        this.prix = prix;
        this.nom_p = nom_p;
        this.description = description;
        this.img_p = img_p;
        this.dispo = dispo;
    }

    public Plat(float prix, String nom_p, String description, String img_p, Boolean dispo) {
        this.prix = prix;
        this.nom_p = nom_p;
        this.description = description;
        this.img_p = img_p;
        this.dispo = dispo;
    }

   

   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_p() {
        return img_p;
    }

    public void setImg_p(String img_p) {
        this.img_p = img_p;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDispo() {
        return dispo;
    }

    public void setDispo(Boolean dispo) {
        this.dispo = dispo;
    }

    @Override
    public String toString() {
        return "Plat{" + "id=" + id + ", prix=" + prix + ", nom_p=" + nom_p + ", description=" + description + ", dispo=" + dispo + '}';
    }
    
    
}
