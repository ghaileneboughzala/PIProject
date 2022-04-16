/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Date;

/**
 *
 * @author ghail
 */
public class CarteFidelite {
    private int id;
    private String num;
    private int nbpts;
    private String periodevalidation;
    private Date dateexpiration;

    public CarteFidelite() {
    }

    public CarteFidelite(String num, int nbpts, String periodevalidation, Date dateexpiration) {
        this.num = num;
        this.nbpts = nbpts;
        this.periodevalidation = periodevalidation;
        this.dateexpiration = dateexpiration;
    }

    public CarteFidelite(int id, String num, int nbpts, String periodevalidation, Date dateexpiration) {
        this.id = id;
        this.num = num;
        this.nbpts = nbpts;
        this.periodevalidation = periodevalidation;
        this.dateexpiration = dateexpiration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getNbpts() {
        return nbpts;
    }

    public void setNbpts(int nbpts) {
        this.nbpts = nbpts;
    }

    public String getPeriodevalidation() {
        return periodevalidation;
    }

    public void setPeriodevalidation(String periodevalidation) {
        this.periodevalidation = periodevalidation;
    }

    public Date getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(Date dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

    @Override
    public String toString() {
        return "CarteFidelite{" + "id=" + id + ", num=" + num + ", nbpts=" + nbpts + ", periodevalidation=" + periodevalidation + ", dateexpiration=" + dateexpiration + '}'+"\n";
    }
    
}
