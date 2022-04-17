/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Offre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyDB;

/**
 *
 * @author lenovo
 */
public class OffreService {
    
    Connection connection;
    public OffreService(){
        connection = MyDB.getInstance().getConnection();
        System.out.println("cnx");
    }
    
    public void ajouter(Offre o) {
        
        try {
                 
            String req1 = "insert into offre(titre,description,remise,image) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1,o.getTitre());
            ps.setString(2,o.getDescription());
            ps.setFloat(3,o.getRemise());
            ps.setString(4,o.getImage());
            ps.executeUpdate();
                
                
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
//    public void modifier(Offre o) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
//    public  void modifier(int id,String titre,String description,float remise,String image){
//       try{
//           String sql="UPDATE offre SET titre='"+titre
//                   +", description="+description
//                   +", remise="+remise
//                   +"', image="+image
//                   +" WHERE id="+id;
//           PreparedStatement ste = connection.prepareStatement(sql);
//           ste.executeUpdate(sql);
//           System.out.println("culture modifiée");
//           
//       }catch(SQLException e){
//           System.out.println(e.getMessage());
//       }
//    }
    
    
    public void modifier(Offre o){
        try{
            String sql="UPDATE offre SET titre='"+o.getTitre()
                    +"', description='"+o.getDescription()
                    +"', remise="+o.getRemise()
                    +", image='"+o.getImage()
                    +"' WHERE id="+o.getId();
            PreparedStatement ste = connection.prepareStatement(sql);
            ste.executeUpdate(sql);
            System.out.println("offre modifiée");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    

    //@Override
    public void supprimer(int id) {
        try{
           String sql="DELETE FROM offre WHERE id="+id; 
           PreparedStatement ste = connection.prepareStatement(sql);
           ste.executeUpdate(sql);
           System.out.println("offre supprimée");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public List<Offre> recuperer(){
        List<Offre> list = new ArrayList<>();
        try {
            String req = "select * from offre";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
                    
            while(rs.next()){
                Offre o = new Offre();
                o.setId(rs.getInt("id"));
                o.setTitre(rs.getString("titre"));
                o.setDescription(rs.getString("description"));
                o.setRemise(rs.getFloat("remise"));
                o.setImage(rs.getString("image"));
                
                list.add(o);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return list;
    }

    
}
