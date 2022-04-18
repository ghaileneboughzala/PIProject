/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Culture;
import services.CultureService;

/**
 *
 * @author lenovo
 */
public class Main {
    
    public static void main(String[] args) {
        CultureService cs= new CultureService();
        cs.ajouter(new Culture("TNNNN","Maroc","morrrrrooocoo","TN5.png"));
    }
    
}
