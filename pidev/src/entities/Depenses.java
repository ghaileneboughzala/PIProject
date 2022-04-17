/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;




/**
 *
 * @author mondh
 */
public class Depenses {
    int id ;
    String type;
    Date date;
    int montant ;
    
     public Depenses() {
    }


    public Depenses(String type, int montant) {
        this.type = type;
        this.montant = montant;
    }

    public Depenses(String type, Date date, int montant) {
        this.type = type;
        this.date = date;
        this.montant = montant;
    }

    public Depenses(int id, String type, int montant) {
        this.id = id;
        this.type = type;
        this.montant = montant;
    }

    public Depenses(int id, String type, Date date, int montant) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.montant = montant;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Depenses{" + "id=" + id + ", type=" + type + ", date=" + date + ", montant=" + montant + '}';
    }
    
    
    
}
