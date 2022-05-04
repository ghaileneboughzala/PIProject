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
public class Coin {
     private int id,nb_places;
    private String pays,img,description_c;
    
 
    
      public Coin() {
    }
      
    public Coin(int id, int nb_places, String pays, String img, String description_c) {
        this.id = id;
        this.nb_places = nb_places;
        this.pays = pays;
        this.img = img;
        this.description_c = description_c;
    }

    

 
    public Coin(int nb_places, String pays, String img, String description_c) {
        this.nb_places = nb_places;
        this.pays = pays;
        this.img = img;
        this.description_c = description_c;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription_c() {
        return description_c;
    }

    public void setDescription_c(String description_c) {
        this.description_c = description_c;
    }

    @Override
    public String toString() {
        return "Coin{" + "id=" + id + ", nb_places=" + nb_places + ", pays=" + pays + ", img=" + img + ", description_c=" + description_c + '}';
    }
    
}
