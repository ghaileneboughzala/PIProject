/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Coin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import util.MyDB;
import javafx.collections.FXCollections;

/**
 *
 * @author masmoudi
 */
public class CoinService implements Iservice<Coin> {
    
Connection connection;
    public CoinService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Coin c) {
        try {
//            String req = "insert into coin(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "insert into coin(nb_places,pays,img,description_c) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, c.getNb_places());
            ps.setString(2, c.getPays());
            ps.setString(3, c.getImg());
            ps.setString(4, c.getDescription_c());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Coin c) {
try {
//            String req = "insert into personnel(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "update coin set nb_places = ? , pays = ? , img = ?, description_c = ? where id = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            
            ps.setInt(1, c.getNb_places());
            ps.setString(2, c.getPays());
            ps.setString(3, c.getImg());
            ps.setString(4, c.getDescription_c());
            ps.setInt(5, c.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

     public void supprimer(int id) {
        try {
String req1 = "delete from coin where id = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, id);
          
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      }

    @Override
    public ObservableList<Coin> recuperer() {
        ObservableList<Coin> list =FXCollections.observableArrayList();
        try {
            String req = "select * from coin";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
               Coin c= new Coin();
                c.setId(rs.getInt("id"));
                 c.setNb_places(rs.getInt("nb_places"));
                c.setPays(rs.getString("pays"));
                c.setImg(rs.getString("img"));
                c.setDescription_c(rs.getString("description_c"));  
                list.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    public List<Coin> getAll() {
        List<Coin> list = new ArrayList();
        try {
            String req = "select * from coin";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Coin p = new Coin();
                p.setId(rs.getInt("id"));
                p.setNb_places(rs.getInt("nb_places"));
                p.setPays(rs.getString("pays"));
                p.setImg(rs.getString("img"));
                p.setDescription_c(rs.getString("description_c"));  
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    public void saveQr(int a, String b){
        String  query  = "INSERT INTO qr (id, path) VALUES (?,?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt (1, a);
            preparedStmt.setString (2, b);
            
            preparedStmt.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        }
     public ArrayList<Coin> recherche(String pays){
        ArrayList<Coin> plats = new ArrayList(); 
        String query = "SELECT * FROM coin where pays LIKE '%' ? '%' " ;
        //"SELECT * FROM magazin where id_mag LIKE ? or location like ? or capacity = ? or capacityRest = ? or category_id = ? or magazinier_id= ?" ;
        PreparedStatement preparedStmt;
        try{
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, "%"+pays+"%");
            
             //preparedStmt.setInt(3, (int) Float.parseFloat(id));
             //preparedStmt.setInt(4, (int) Float.parseFloat(id));
             
             
             
            ResultSet rs = preparedStmt.executeQuery();
            
            
            while ( rs.next() ){
               Coin p = new Coin();
                p.setId(rs.getInt("id"));
                p.setNb_places(rs.getInt("nb_places"));
                p.setPays(rs.getString("pays"));
                p.setImg(rs.getString("img"));
                p.setDescription_c(rs.getString("description_c")); 
                
                plats.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return plats;
    }
}

