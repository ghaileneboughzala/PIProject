/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Plat;
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
 * @author masmoudi
 */
public class PlatService implements Iservice<Plat>{

    Connection connection;
    public PlatService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Plat p) {
        try {
//            String req = "insert into personne(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "insert into personne(nom,prenom,age) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, p.getNom_p());
            ps.setFloat(2, p.getPrix());
            ps.setString(3, p.getDescription());
            ps.setBoolean(4, p.getDispo());
            ps.setString(5, p.getImg_p());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Plat p) {
try {
//            String req = "insert into personnel(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "update plat set nom_p = ? , prix = ? , description = ?, dispo = ? , img_p = ? where id = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, p.getNom_p());
            ps.setFloat(2, p.getPrix());
            ps.setString(3, p.getDescription());
            ps.setBoolean(4, p.getDispo());
            ps.setString(5, p.getImg_p());
            ps.setInt(6, p.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
     public void supprimer(int id) {
        try {
String req1 = "delete from plat where id = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, id);
          
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      }

    @Override
    public ObservableList<Plat> recuperer() {
        ObservableList<Plat> list =FXCollections.observableArrayList();
        try {
            String req = "select * from plat";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Plat p = new Plat();
                p.setId(rs.getInt("id"));
                p.setNom_p(rs.getString("nom_p"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));  
                p.setDispo(rs.getBoolean("dispo"));  
                p.setImg_p(rs.getString("img_p"));  
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
     public List<Plat> getAll() {
        List<Plat> list = new ArrayList();
        try {
            String req = "select * from plat";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Plat p = new Plat();
               p.setId(rs.getInt("id"));
                p.setNom_p(rs.getString("nom_p"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));  
                p.setDispo(rs.getBoolean("dispo"));  
                p.setImg_p(rs.getString("img_p"));  
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
      public ArrayList<Plat> recherche(String id){
        ArrayList<Plat> plats = new ArrayList(); 
        String query = "SELECT * FROM plat where nom_p LIKE ?" ;
        //"SELECT * FROM magazin where id_mag LIKE ? or location like ? or capacity = ? or capacityRest = ? or category_id = ? or magazinier_id= ?" ;
        PreparedStatement preparedStmt;
        try{
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, "%"+id+"%");
            
             //preparedStmt.setInt(3, (int) Float.parseFloat(id));
             //preparedStmt.setInt(4, (int) Float.parseFloat(id));
             
             
             
            ResultSet rs = preparedStmt.executeQuery();
            
            
            while ( rs.next() ){
                Plat magazin = new Plat();
                magazin.setId(rs.getInt("id"));
                magazin.setNom_p(rs.getString("nom_p"));
                magazin.setPrix(rs.getFloat("prix"));
                magazin.setDispo(rs.getBoolean("dispo"));
                magazin.setImg_p(rs.getString("img_p")); 
                
                plats.add(magazin);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return plats;
    }
    
}
