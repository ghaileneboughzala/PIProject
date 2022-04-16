/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.CarteFidelite;
import entites.Role;
import entites.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Myconnexion;

/**
 *
 * @author ghail
 */
public class ServiceCarteFidelite implements IService<CarteFidelite>{
    Connection cnx;
    public ServiceCarteFidelite(){
        cnx=Myconnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(CarteFidelite t) {
        try {
            String query="INSERT INTO `cartefidelite`"
                    + "(`num`, `nbpts`,"
                    + " `periodevalidation`, `dateexpiration`) "
                    + "VALUES ('"+t.getNum()+"','"+t.getNbpts()+"',"
                    + "'"+t.getPeriodevalidation()+"','"+t.getDateexpiration()+"')";
            Statement st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCarteFidelite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String query="DELETE FROM `cartefidelite` WHERE id="+id;
            Statement st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCarteFidelite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(CarteFidelite u, int id_amodifier) {
        try {
            String query="UPDATE `cartefidelite` SET "
                    + "`num`='"+u.getNum()+"',"
                    + "`nbpts`='"+u.getNbpts()+"',"
                    + "`periodevalidation`='"+u.getPeriodevalidation()+"',"
                    + "`dateexpiration`='"+u.getDateexpiration()+"'"
                    + " WHERE id="+id_amodifier;
            Statement st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCarteFidelite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<CarteFidelite> afficher() {
        List<CarteFidelite> lc=new ArrayList<>();
        try {
            String query="SELECT * FROM `cartefidelite`";
            Statement st;
            st=cnx.createStatement();
            
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                CarteFidelite c=new CarteFidelite();
                c.setId(rs.getInt("id"));
                c.setNbpts(rs.getInt("nbpts"));
                c.setNum(rs.getString("num"));
                c.setPeriodevalidation(rs.getString("periodevalidation"));
                c.setDateexpiration(rs.getDate("dateexpiration"));
                lc.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lc;
    }
    
}
