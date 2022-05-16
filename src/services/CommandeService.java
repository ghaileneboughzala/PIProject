/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
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
 * @author allan
 */
public class CommandeService implements Iservice<Commande> {
    
     Connection connection;
     public CommandeService(){
         connection = MyDB.getInstance().getConnection();
     }
     
     public void ajouter(Commande t){
         try {
             String req1 = "INSERT INTO commande(livraisons_id, num_c, date_c, payement) values (?,?,?,?)";
             PreparedStatement cs = connection.prepareStatement(req1);
             cs.setInt(1,t.getLivraisons_id());
             cs.setInt(2, t.getNum_c());
             cs.setDate(3,t.getDate_c());
             cs.setFloat(4, t.getPayement());
             cs.executeUpdate();
             }catch (SQLException ex) {
                     System.out.println(ex.getMessage());
                     }
         
     }

    @Override
    public void modifier(Commande t) {
         try {
             Statement st = connection.createStatement();
             String req = "UPDATE commande SET `livraisons_id`='"+t.getLivraisons_id()+"',`num_c`='"+t.getNum_c()+"', `date_c`='"+t.getDate_c()+"', `payement`='"+t.getPayement()+"'";
             st.executeUpdate(req);
             System.out.println("Commande modifié");
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
         }
    }

    @Override
    public void supprimer(int id) {
        try{
            Statement st = connection.createStatement();
            String req = "DELETE from commande WHERE id='"+id+"'";
            st.executeUpdate(req);
           
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }
    }

    @Override
    public List<Commande> recuperer() {
       ObservableList<Commande> commandes = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM commande";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Commande c = new Commande();
                c.setId(rs.getInt("id"));
                c.setLivraisons_id(rs.getInt("livraisons_id"));
                c.setNum_c(rs.getInt("num_c"));
                c.setDate_c(rs.getDate("date_c"));
                c.setPayement(rs.getFloat("payement"));
                commandes.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return commandes;
    }
    
    public  Commande GetById(int id) {
        return recuperer().stream().filter(e -> e.getId() == id).findFirst().get();

    }
     
     
    
}
