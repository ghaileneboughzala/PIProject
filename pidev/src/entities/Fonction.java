/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author bhk
 */
public class Fonction {
    private int id;
    private String nom_f;
    private Float salaire;
    private int nb_heure ;
    public Fonction() {
    }
    
    

    public Fonction( String nom_f, Float salaire ,int nb_heure) {
        this.nb_heure = nb_heure;
        this.nom_f = nom_f;
        this.salaire = salaire;
    }

    public Fonction(int id, String nom_f, Float salaire, int nb_heure) {
        this.id = id;
        this.nom_f = nom_f;
        this.salaire = salaire;
        this.nb_heure = nb_heure;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb_heure() {
        return nb_heure;
    }

    public void setNb_heure(int nb_heure) {
        this.nb_heure = nb_heure;
    }

    public String getNom_f() {
        return nom_f;
    }

    public void setNom_f(String nom_f) {
        this.nom_f = nom_f;
    }

    public Float getSalaire() {
        return salaire;
    }

    public void setSalaire(Float salaire) {
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "Foncton{" + "id=" + id + ", nb_heure=" + nb_heure + ", nom_f=" + nom_f + ", salaire=" + salaire + '}';
    }
    
    
    
    
    
    
}
