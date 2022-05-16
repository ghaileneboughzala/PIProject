/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;



/**
 *
 * @author allan
 */
public class Commande {
    
    private int id, livraisons_id, num_c;
    private Date date_c;
    private Float payement;

    public Commande() {
    }

    public Commande(int id, int livraisons_id, int num_c, Date date_c, Float payement) {
        this.id = id;
        this.livraisons_id = livraisons_id;
        this.num_c = num_c;
        this.date_c = date_c;
        this.payement = payement;
    }

    public Commande(int livraisons_id, int num_c, Date date_c, Float payement) {
        this.livraisons_id = livraisons_id;
        this.num_c = num_c;
        this.date_c = date_c;
        this.payement = payement;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_c() {
        return num_c;
    }

    public void setNum_c(int num_c) {
        this.num_c = num_c;
    }

    public Float getPayement() {
        return payement;
    }

    public void setPayement(Float payement) {
        this.payement = payement;
    }

    public Date getDate_c() {
        return date_c;
    }

    public void setDate_c(Date date_c) {
        this.date_c = date_c;
    }

    public int getLivraisons_id() {
        return livraisons_id;
    }

    public void setLivraisons_id(int livraisons_id) {
        this.livraisons_id = livraisons_id;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", num_c=" + num_c + ", date_c=" + date_c + ", payement=" + payement + ", livraisons_id=" + livraisons_id + '}';
    }
    
   
    
    
}
