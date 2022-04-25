/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Depenses;
import entities.Fonction;
import entities.Personnel;
import java.sql.Connection;
import java.sql.Date;
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
public class DepensesService implements Iservice<Depenses>{

    Connection connection;
    public DepensesService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Depenses t) {
        try {
//            String req = "insert into personnel(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "insert into depense(type,montant,date) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            
            ps.setString(1, t.getType());
            ps.setInt(2, t.getMontant());
             ps.setDate(3, (Date) t.getDate());
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Depenses t) {
try {
//            String req = "insert into personnel(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "update depense set  type = ? , montant = ? , date = ?  where id = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            
            ps.setString(1, t.getType());
            ps.setFloat(2, t.getMontant());
            ps.setDate(3, (Date) t.getDate());
            ps.setInt(4, t.getId());
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    @Override
    public void supprimer(int id) {
        try {
String req1 = "delete from depense where id = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, id);
          
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      }

    @Override
    public ObservableList<Depenses> recuperer() {
        ObservableList<Depenses> list = FXCollections.observableArrayList();
        try {
            String req = "select * from depense";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Depenses d = new Depenses();
                d.setId(rs.getInt("id"));
                d.setType(rs.getString("type"));
                d.setMontant(rs.getInt("montant"));
                d.setDate(rs.getDate("date"));
                  
                list.add(d);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
    public Integer facture(){
        
        ObservableList<Depenses> listFactures = FXCollections.observableArrayList();
        
        try {
            String req = "select * from depense where UPPER(type) LIKE 'FACTURE'";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Depenses d = new Depenses();
                d.setId(rs.getInt("id"));
                d.setType(rs.getString("type"));
                d.setMontant(rs.getInt("montant"));
                d.setDate(rs.getDate("date"));
                  
                listFactures.add(d);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listFactures.size();
    }
    
    public Integer salaire(){
        
        ObservableList<Depenses> listSalaires = FXCollections.observableArrayList();
        
        try {
            String req = "select * from depense where UPPER(type) LIKE 'SALAIRE'";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Depenses d = new Depenses();
                d.setId(rs.getInt("id"));
                d.setType(rs.getString("type"));
                d.setMontant(rs.getInt("montant"));
                d.setDate(rs.getDate("date"));
                  
                listSalaires.add(d);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listSalaires.size();
    }
    
}
