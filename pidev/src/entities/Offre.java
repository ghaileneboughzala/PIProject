/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author lenovo
 */
public class Offre {
    private int id;
    private String titre;
    private String description;
    private float remise;
    private String image;
    private Date exp_date;
    private boolean expire;

    public Offre(String titre, String description, float remise, String image, Date exp_date, boolean expire) {
        this.titre = titre;
        this.description = description;
        this.remise = remise;
        this.image = image;
        this.exp_date = exp_date;
        this.expire = expire;
    }

    public Offre(int id, String titre, String description, float remise, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.remise = remise;
        this.image = image;
    }

    public Offre(String titre, String description, float remise) {
        this.titre = titre;
        this.description = description;
        this.remise = remise;
    }
    
    public Offre(String titre, String description, float remise,String image) {
        this.titre = titre;
        this.description = description;
        this.remise = remise;
        this.image = image;
    }
    
    public Offre(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRemise() {
        return remise;
    }

    public void setRemise(float remise) {
        this.remise = remise;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getExp_date() {
        return exp_date;
    }

    public void setExp_date(Date exp_date) {
        this.exp_date = exp_date;
    }

    public boolean isExpire() {
        return expire;
    }

    public void setExpire(boolean expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "Offre{  titre=" + titre + ", description=" + description + ", remise=" + remise + ", image=" + image + ", exp_date=" + exp_date + ", expire=" + expire + '}';
    }
    
    
    
    
    
}
