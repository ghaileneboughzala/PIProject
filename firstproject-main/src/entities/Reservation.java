/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.sql.Date;
/**
 *
 * @author masmoudi
 */
public class Reservation {
    private int num,nb_personnes;
    private Date date;
    private String heure;
    private String nom;

    public Reservation(int num, int nb_personnes, Date date, String heure, String nom) {
        this.num = num;
        this.nb_personnes = nb_personnes;
        this.date = date;
        this.heure = heure;
        this.nom = nom;
    }

    public Reservation(int nb_personnes, Date date, String heure, String nom) {
        this.nb_personnes = nb_personnes;
        this.date = date;
        this.heure = heure;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNb_personnes() {
        return nb_personnes;
    }

    public void setNb_personnes(int nb_personnes) {
        this.nb_personnes = nb_personnes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Reservation() {
    }

    public Reservation(int num, int nb_personnes, Date date, String heure) {
        this.num = num;
        this.nb_personnes = nb_personnes;
        this.date = date;
        this.heure = heure;
    }

    public Reservation(int nb_personnes, Date date, String heure) {
        this.nb_personnes = nb_personnes;
        this.date = date;
        this.heure = heure;
    }

    @Override
    public String toString() {
        return "Reservation{" + "num=" + num + ", nb_personnes=" + nb_personnes + ", date=" + date + ", heure=" + heure + ", nom=" + nom + '}';
    }

    
    
}
