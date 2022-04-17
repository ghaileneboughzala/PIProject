/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Fonction;
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
public class FonctionService implements Iservice<Fonction>{

    Connection connection;
    public FonctionService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Fonction t) {
        try {
//            String req = "insert into personnel(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "insert into fonction(nom_f,salaire,nb_heure) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            
            ps.setString(1, t.getNom_f());
            ps.setFloat(2, t.getSalaire());
            ps.setInt(3, t.getNb_heure());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Fonction t) {
try {
//            String req = "insert into personnel(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "update fonction set  nom_f = ? , salaire = ? , nb_heure = ? where id = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            
            ps.setString(1, t.getNom_f());
            ps.setFloat(2, t.getSalaire());
            ps.setInt(3, t.getNb_heure());
            ps.setInt(4, t.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(int id) {
        try {
String req1 = "delete from fonction where id = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, id);
          
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      }

    @Override
    public ObservableList<Fonction> recuperer() {
        ObservableList<Fonction> list = FXCollections.observableArrayList();
        try {
            String req = "select * from fonction";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Fonction f = new Fonction();
                f.setId(rs.getInt("id"));
                f.setNom_f(rs.getString("nom_f"));
                f.setSalaire(rs.getFloat("salaire"));
                f.setNb_heure(rs.getInt("nb_heure"));  
                list.add(f);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
}
