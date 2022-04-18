 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class MyDB {

    private String url = "jdbc:mysql://localhost:3306/pidev";
    private String username = "root";
    private String password = "root";
    private Connection connection;
    static MyDB instance;

    private MyDB() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static MyDB getInstance(){
        if(instance == null){
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
    
    

}
