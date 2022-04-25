/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Culture;
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
 * @author lenovo
 */
public class CultureService {

    Connection connection;
    public CultureService(){
        connection = MyDB.getInstance().getConnection();
        System.out.println("cnx");
    }
    
    
    public void ajouter(Culture c){
        
        try {
            //String req = "insert intro personne(id,nom,prenom,age)"
              //  +"values("(""+p.getId()+,'"+p.getNom()+"','"+p.getPrenom()+"',"+p.getAge()+")";
            //Statement st = connection.createStatement();
            //st.executeUpdate(req);
                
                
            String req1 = "insert into culture(ref,pays,texte,flag,date_ajout) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1,c.getRef());
            ps.setString(2,c.getPays());
            ps.setString(3,c.getTexte());
            ps.setString(4,c.getFlag());
             ps.setDate(5,(Date)c.getDate_ajout());
            ps.executeUpdate();
                
                
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    
//    public void modifier(Culture p) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
//    public  void modifier(String oldref,String ref,String pays,String texte,String flag){
//       try{
//           String sql="UPDATE offre SET ref='"+ref
//                   +"', pays="+pays
//                   +", texte="+texte
//                   +", flag="+flag
//                   +" WHERE ref="+oldref;
//           PreparedStatement ste = connection.prepareStatement(sql);
//           ste.executeUpdate(sql);
//           System.out.println("culture modifiée");
//           
//       }catch(SQLException e){
//           System.out.println(e.getMessage());
//       }
//    }
    public void update(String ref,Culture c){
        try{
             String req1 = "update culture set  ref = ? , pays = ? , texte = ? , flag = ? , date_ajout = ? where ref = ? ";
            PreparedStatement ps = connection.prepareStatement(req1);
            
            ps.setString(1, c.getRef());
            ps.setString(2, c.getPays());
            ps.setString(3, c.getTexte());
            ps.setString(4, c.getFlag());
            ps.setDate(5, (Date) c.getDate_ajout());
            ps.setString(6, ref);
            
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
           
    }
    //@Override
    public void supprimer(String ref) {
        try{
           String sql="DELETE FROM culture WHERE ref='"+ref+"'"; 
           PreparedStatement ste = connection.prepareStatement(sql);
           ste.executeUpdate(sql);
           System.out.println("culture supprimée");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

     
    public ObservableList<Culture> recuperer(){
        ObservableList<Culture> list = FXCollections.observableArrayList();
        try {
            String req = "select * from culture";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
                    
            while(rs.next()){
                Culture c = new Culture();
                c.setRef(rs.getString("ref"));
                c.setPays(rs.getString("pays"));
                c.setTexte(rs.getString("texte"));
                c.setFlag(rs.getString("flag"));
                c.setDate_ajout(rs.getDate("date_ajout"));
                
                list.add(c);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }
    public List<Culture> recupererList(){
        List<Culture> list = new ArrayList();
        try {
            String req = "select * from culture";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
                    
            while(rs.next()){
                Culture c = new Culture();
                c.setRef(rs.getString("ref"));
                c.setPays(rs.getString("pays"));
                c.setTexte(rs.getString("texte"));
                c.setFlag(rs.getString("flag"));
                c.setDate_ajout(rs.getDate("date_ajout"));
                
                list.add(c);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

//    @Override
//    public List<Personne> recuperer() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
