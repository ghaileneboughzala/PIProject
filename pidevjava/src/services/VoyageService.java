/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Voyage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author lenovo
 */
public class VoyageService {
    
    Connection connection;
    public VoyageService(){
        connection = MyDB.getInstance().getConnection();
        System.out.println("cnx");
    }
    
    public void ajouter(Voyage v) throws SQLException{
        
        try {
                 
            String req1 = "insert into voyage(offre_id,id_u,destination,done) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1,v.getOffre_id());
            ps.setInt(2,v.getId_u());
            ps.setString(3,v.getDestination());
            ps.setBoolean(4,v.isDone());
            ps.executeUpdate();     
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    public void modifier(Voyage v){
        try{
            String sql="UPDATE voyage SET offre_id="+v.getOffre_id()
                    +"' id_u="+v.getId_u()
                    +", destination='"+v.getDestination()
                    +"', done="+v.isDone()
                    +" WHERE id="+v.getId();
            PreparedStatement ste = connection.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("voyage modifié");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    

    //@Override
    public void supprimer(int id) {
        try{
           String sql="DELETE FROM voyage WHERE id="+id; 
           PreparedStatement ste = connection.prepareStatement(sql);
           ste.executeUpdate(sql);
           System.out.println("voyage supprimé");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public ObservableList<Voyage> recuperer(){
        ObservableList<Voyage> list = FXCollections.observableArrayList();
        try {
            String req = "select * from voyage";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
                    
            while(rs.next()){
                Voyage v = new Voyage();
                v.setOffre_id(rs.getInt("offre_id"));
                v.setId(rs.getInt("id"));
                v.setId_u(rs.getInt("id_u"));
                v.setDestination(rs.getString("destination"));
                v.setDone(rs.getBoolean("done"));
                
                list.add(v);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    
}
