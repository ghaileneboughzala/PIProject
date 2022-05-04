/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Reservation;
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

public class ReservationService implements Iservice<Reservation> {
    Connection connection;
    public ReservationService() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Reservation c) {
        try {
//            String req = "insert into coin(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "insert into reservation(nb_personnes,date,heure,nom) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, c.getNb_personnes());
            ps.setDate(2, c.getDate());
            ps.setString(3, c.getHeure());
            ps.setString(4, c.getNom());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reservation c) {
try {
//            String req = "insert into personnel(nom,prenom,age) "
//                    + "values('"+ t.getNom()+"','"+ t.getPrenom()+"',"+ t.getAge()+")";
//            Statement st = connection.createStatement();
//            st.executeUpdate(req);
            
            
            String req1 = "update coin set nb_personnes = ? , date = ? , heure = ?, heure LIKE ? where num = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            
            ps.setInt(1, c.getNb_personnes());
            ps.setDate(2, c.getDate());
            ps.setString(3, c.getHeure());
            ps.setInt(4, c.getNum());
            ps.setString(5, c.getNom());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

     public void supprimer(int num) {
        try {
String req1 = "delete from reservation where num = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, num);
          
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      }

    @Override
    public ObservableList<Reservation> recuperer() {
        ObservableList<Reservation> list =FXCollections.observableArrayList();
        try {
            String req = "select * from reservation";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
               Reservation r= new Reservation();
                r.setNum(rs.getInt("num"));
                 r.setNb_personnes(rs.getInt("nb_personnes"));
                r.setDate(rs.getDate("date"));
                r.setHeure(rs.getString("heure"));
                r.setNom(rs.getString("nom"));
                
                list.add(r);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    public List<Reservation> getAll() {
        List<Reservation> list = new ArrayList();
        try {
            String req = "select * from reservation";
            Statement st = connection.createStatement();
            ResultSet rs =st.executeQuery(req);
            
            while(rs.next()){
                Reservation p = new Reservation();
                p.setNum(rs.getInt("id"));
                p.setNb_personnes(rs.getInt("nb_personnes"));
                p.setDate(rs.getDate("date"));
                p.setHeure(rs.getString("heure"));
                p.setNom(rs.getString("nom"));
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    public void saveQr(String a, String b){
        String  query  = "INSERT INTO qr (id, path) VALUES (?,?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, a);
            preparedStmt.setString (2, b);
            
            preparedStmt.execute();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        }
     public String findQr(int a){
        String query = "SELECT * FROM qr WHERE id = ?";
        PreparedStatement preparedStmt;
        String nature = null;
        try{
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1,a);
            ResultSet rs = preparedStmt.executeQuery();
            while ( rs.next() ){
                
                nature=rs.getString("path");
               
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nature;
    }
}
