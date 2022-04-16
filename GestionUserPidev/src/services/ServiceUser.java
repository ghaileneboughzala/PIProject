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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Myconnexion;

/**
 *
 * @author ghail
 */
public class ServiceUser implements IService<User>{
    Connection cnx;
    ServiceCarteFidelite sc=new ServiceCarteFidelite();
    public ServiceUser(){
        cnx=Myconnexion.getInstance().getCnx();
    }

    @Override
    public void ajouter(User t) {
        LocalDate ld=LocalDateTime.now().plusYears(3).toLocalDate();
        Date d1=Date.valueOf(ld);
        Random rand =new Random();
        int random=rand.nextInt(99999999+1-10000000)+10000000;
        
        CarteFidelite c1=new CarteFidelite(String.valueOf(random), 0, "3 ans", d1);
        sc.ajouter(c1);
        List<CarteFidelite> lc=new ArrayList<>(sc.afficher());
        
        int x;
        if(t.isIs_verified()){
            x=1;
        }
        else{
            x=0;
        }
        try {
            Statement st;
            String query="INSERT INTO `user`"
                    + "(`carte_id`, `email`, `roles`,"
                    + " `password`, `cin`, `nom`"
                    + ", `prenom`, `photo`, "
                    + "`is_verified`, `code`) "
                    + "VALUES ('"+lc.get(lc.size()-1).getId()+"','"+t.getEmail()+"','"+t.getRoles()+"',"
                    + "'"+t.getPassword()+"','"+t.getCin()+"','"+t.getNom()+"',"
                    + "'"+t.getPrenom()+"','"+t.getPhoto()+"','"+x+"',"
                    + "'"+t.getCode()+"')";
            st=cnx.createStatement();
            st.executeUpdate(query);
            
            } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String query="DELETE FROM `user` WHERE id="+id;
            Statement st;
            st=cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(User t, int id_amodifier) {
        int x;
        if(t.isIs_verified()){
            x=1;
        }
        else{
            x=0;
        }
        try {
            Statement st;
            String query="UPDATE `user` SET `carte_id`='"+t.getCarte_id()+"',"
                    + "`email`='"+t.getEmail()+"',"
                    + "`roles`='"+t.getRoles()+"',"
                    + "`password`='"+t.getPassword()+"',"
                    + "`cin`='"+t.getCin()+"',"
                    + "`nom`='"+t.getNom()+"',"
                    + "`prenom`='"+t.getPrenom()+"',"
                    + "`photo`='"+t.getPhoto()+"',"
                    + "`is_verified`='"+x+"',"
                    + "`code`='"+t.getCode()+"' WHERE id="+id_amodifier;
            st=cnx.createStatement();
            st.executeUpdate(query);
            } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public List<User> afficher() {
        List<User> lu=new ArrayList<>();
        try {
            String query="SELECT * FROM `user`";
            Statement st;
            st=cnx.createStatement();
            
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                User u =new User();
                u.setId(rs.getInt("id"));
                u.setCarte_id(rs.getInt("carte_id"));
                u.setEmail(rs.getString("email"));
                u.setRoles(Role.valueOf(rs.getString("roles")));
                u.setPassword(rs.getString("password"));
                u.setCin(rs.getString("cin"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setPhoto(rs.getString("photo"));
                u.setIs_verified(rs.getBoolean("is_verified"));
                u.setCode(rs.getString("code"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
    
}
