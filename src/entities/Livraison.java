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
public class Livraison {
    
    private int id,personnel_id;
    private Date date_h_l;
    private String adresse,num_tel;
    private String etat;
    
    public Livraison() {
    }

   
    public Livraison(int id, int personnel_id, Date date_h_l, String adresse, String num_tel, String etat) {
        this.id = id;
        this.personnel_id = personnel_id;
        this.date_h_l = date_h_l;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.etat = etat;
    }

    public Livraison(int personnel_id, Date date_h_l, String adresse, String num_tel, String etat) {
        this.personnel_id = personnel_id;
        this.date_h_l = date_h_l;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.etat = etat;
    }

     public int getPersonnel_id() {
        return personnel_id;
    }

    public void setPersonnel_id(int personnel_id) {
        this.personnel_id = personnel_id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_h_l() {
        return date_h_l;
    }

    public void setDate_h_l(Date date_h_l) {
        this.date_h_l = date_h_l;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", personnel_id=" + personnel_id + ", date_h_l=" + date_h_l + ", adresse=" + adresse + ", num_tel=" + num_tel + ", etat=" + etat + '}';
    }

   
    
    
    
}
