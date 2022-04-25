/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Personnel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyDB;

/**
 *
 * @author Skander
 */
public class PersonnelService implements Iservice<Personnel>{

    Connection connection;
    public PersonnelService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Personnel t) {
        try {
//            String req = "insert into personnel(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "insert into personnel(fonction_id,nom,prenom,photo) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, t.getFonction_id());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getPrenom());
            ps.setString(4, t.getPhoto());
          
           ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Personnel t) {
try {
//            String req = "insert into personnel(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "update personnel set fonction_id= ? , nom = ? , prenom = ? , photo = ? where id = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, t.getFonction_id());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getPrenom());
            ps.setString(4, t.getPhoto());
            ps.setInt(5, t.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(int id) {
        try {
String req1 = "delete from personnel where id = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, id);
          
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      }

    @Override
    public ObservableList<Personnel> recuperer() {
        ObservableList<Personnel> list = FXCollections.observableArrayList();
        try {
            String req = "select * from personnel";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Personnel p = new Personnel();
                p.setId(rs.getInt("id"));
                p.setFonction_id(rs.getInt("fonction_id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setPhoto(rs.getString("photo"));  
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    public List<Personnel> getAll() {
        List<Personnel> list = new ArrayList();
        try {
            String req = "select * from personnel";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Personnel p = new Personnel();
                p.setId(rs.getInt("id"));
                p.setFonction_id(rs.getInt("fonction_id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setPhoto(rs.getString("photo"));  
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
    
     public List<Personnel> trinom() {
        List<Personnel> list = new ArrayList();
        try {
            String req = "select * from personnel order by nom";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Personnel p = new Personnel();
                p.setId(rs.getInt("id"));
                p.setFonction_id(rs.getInt("fonction_id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setPhoto(rs.getString("photo"));  
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
     
      public List<Personnel> triprenom() {
        List<Personnel> list = new ArrayList();
        try {
            String req = "select * from personnel order by prenom";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Personnel p = new Personnel();
                p.setId(rs.getInt("id"));
                p.setFonction_id(rs.getInt("fonction_id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setPhoto(rs.getString("photo"));  
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
}
