/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author ghail
 */
public class BCryptPass {
    

    
    public static String HashPass(String pass){
        return BCrypt.hashpw(pass ,BCrypt.gensalt(10));
    }
    public static boolean checkPass(String pass,String password){
        return BCrypt.checkpw(pass, password);
    }
    
    
    
}
