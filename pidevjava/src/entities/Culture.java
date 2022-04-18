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
public class Culture {
    
    private String ref;
    private String pays,texte;
    private Date date_ajout;
    private String flag;

    public Culture() {
    }

    public Culture(String ref, String pays, String texte, Date date_ajout, String flag) {
        this.ref = ref;
        this.pays = pays;
        this.texte = texte;
        this.date_ajout = date_ajout;
        this.flag = flag;
    }
    
    public Culture(String ref, String pays, String texte, String flag) {
        this.ref = ref;
        this.pays = pays;
        this.texte = texte;
        this.flag = flag;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Culture{" + "ref=" + ref + ", pays=" + pays + ", texte=" + texte + ", date_ajout=" + date_ajout + ", flag=" + flag + '}';
    }
      
    
}
