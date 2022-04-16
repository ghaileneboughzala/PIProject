/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ghail
 */
public class Myconnexion {
    final String URL="jdbc:mysql://127.0.0.1:3306/pidev";
    final String USER="root";
    final String PWD="";
    private static Connection cnx;
    private static Myconnexion instance;

    private Myconnexion() {
        try{
            cnx=DriverManager.getConnection(URL, USER,PWD);
        }catch(SQLException ex){
                System.out.println(ex.getMessage());
        }
    }
    public static Myconnexion getInstance(){
        if(instance==null){
            instance=new Myconnexion();
        }
        else{
            System.out.println("deja connecte");
        }
        return instance;
    }

    public static Connection getCnx() {
        return cnx;
    }
    
    
    
}
