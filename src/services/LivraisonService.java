/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Livraison;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyDB;

/**
 *
 * @author allani
 */
public  class LivraisonService implements Iservice<Livraison> {

    Connection connection;
    public LivraisonService(){
         connection = MyDB.getInstance().getConnection();
     }
    
    @Override
    public void ajouter(Livraison t) {
       try {
             String req1 = "INSERT INTO livraison(personnel_id, date_h_l, adresse, num_tel, etat) values (?,?,?,?,?)";
             PreparedStatement cs = connection.prepareStatement(req1);
             cs.setInt(1, t.getPersonnel_id());
             cs.setDate(2, t.getDate_h_l());
             cs.setString(3,t.getAdresse());
             cs.setString(4, t.getNum_tel());
             cs.setString(5, t.getEtat());
             cs.executeUpdate();
             }catch (SQLException ex) {
                     System.out.println(ex.getMessage());
                     }  
    }

    @Override
    public void modifier(Livraison t) {
       try {
             Statement st = connection.createStatement();
             String req = "UPDATE livraison SET  `personnel_id`='"+t.getPersonnel_id()+"',`date_h_l`='"+t.getDate_h_l()+"', `adresse`='"+t.getAdresse()+"', `num_tel`='"+t.getNum_tel()+"', `etat`='"+t.getEtat()+"'";
             System.out.println("Livraison modifié");
       } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
    }

    @Override
    public void supprimer(int id) {
        try{
            Statement st = connection.createStatement();
            String req = "DELETE from livraison WHERE id='"+id+"'";
            st.executeUpdate(req);
            System.out.println("Livraison supprimé");
           
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
    }

    @Override
    public List<Livraison> recuperer() {
         ObservableList<Livraison> livraison = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM livraison";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Livraison l = new Livraison();
                l.setId(rs.getInt("id"));
                l.setPersonnel_id(rs.getInt("Personnel_id"));
                l.setDate_h_l(rs.getDate("Date_h_l"));
                l.setAdresse(rs.getString("Adresse"));
                l.setNum_tel(rs.getString("Num_tel"));
                l.setEtat(rs.getString("Etat"));
                livraison.add(l);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return livraison;
    }

    
    public  Livraison GetById(int id) {
        return recuperer().stream().filter(e -> e.getId() == id).findFirst().get();

    }


    
   

 
     
    
}
