/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuserpidev;

import entites.CarteFidelite;
import entites.Role;
import entites.User;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import org.mindrot.jbcrypt.BCrypt;
import services.ServiceCarteFidelite;
import services.ServiceUser;
import utils.BCryptPass;
import utils.Myconnexion;

/**
 *
 * @author ghail
 */
public class GestionUserPidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Date d =new Date(2022-1900, 5, 5);
        User u=new User("testuser-carte@gmail.com",Role.ROLE_ADMIN,"test","2233355","test","test","test",false,"666");
        CarteFidelite c=new CarteFidelite("10", 20, "5 mois", d);
        ServiceUser su=new ServiceUser();
        ServiceCarteFidelite sc=new ServiceCarteFidelite();
        System.out.println(sc.sortByPoints());
        //sc.ajouter(c);
        //sc.supprimer(112);
        //sc.modifier(c, 111);
        //System.out.println(sc.afficher());
        //su.ajouter(u);
        //System.out.println(su.afficher());
        
        
        
        
        //su.supprimer(121);
        //su.modifier(u, 119);
        
        
        
    }
    
}
