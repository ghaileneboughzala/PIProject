/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author lenovo
 */
public class Voyage {
    
    private int id;
    private int offre_id;
    private int id_u;
    private Date date_dep;
    private Date heure_dep;
    private String destination;
    private boolean done;

    public Voyage() {
    }

    public Voyage(int id, int offre_id, int id_u, String destination, boolean done) {
        this.id = id;
        this.offre_id = offre_id;
        this.id_u = id_u;
        this.destination = destination;
        this.done = done;
    }
    
    
    
    public Voyage(int offre_id, int id_u, String destination, boolean done) {
        this.offre_id = offre_id;
        this.id_u = id_u;
        this.destination = destination;
        this.done = done;
    }

    public Voyage(int offre_id, int id_u, Date date_dep, String destination, boolean done) {
        this.offre_id = offre_id;
        this.id_u = id_u;
        this.date_dep = date_dep;
        this.destination = destination;
        this.done = done;
    }

    public Voyage(int id, int offre_id, int id_u, Date date_dep, String destination, boolean done) {
        this.id = id;
        this.offre_id = offre_id;
        this.id_u = id_u;
        this.date_dep = date_dep;
        this.destination = destination;
        this.done = done;
    }
    
    
    
    
    
    

    
//    public Voyage(int id, int id_u, Date date_dep, Date heure_dep, String destination, boolean done) {
//        this.id = id;
//        this.id_u = id_u;
//        this.date_dep = date_dep;
//        this.heure_dep = heure_dep;
//        this.destination = destination;
//        this.done = done;
//    }
    
    

//    public Voyage(int id, int id_u, String destination, boolean done) {
//        this.id = id;
//        this.id_u = id_u;
//        this.destination = destination;
//        this.done = done;
//    }

    public Voyage(int id, String destination, boolean done) {
        this.id = id;
        this.destination = destination;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOffre_id() {
        return offre_id;
    }

    public void setOffre_id(int offre_id) {
        this.offre_id = offre_id;
    }
    

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public Date getDate_dep() {
        return date_dep;
    }

    public void setDate_dep(Date date_dep) {
        this.date_dep = date_dep;
    }

    public Date getHeure_dep() {
        return heure_dep;
    }

    public void setHeure_dep(Date heure_dep) {
        this.heure_dep = heure_dep;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Voyage{" + "id=" + id + ", offre_id=" + offre_id + ", id_u=" + id_u + 
                ", date_dep=" + date_dep + ", heure_dep=" + heure_dep + ", destination=" + destination + ", done=" + done + '}';
    }

    
    
    
    
}
