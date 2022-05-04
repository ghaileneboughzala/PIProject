/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Plat;
import entities.Coin;
import services.PlatService;
import services.CoinService;

/**
 *
 * @author masmoudi
 */
public class Main {
    public static void main(String[] args) {
        PlatService ps=new PlatService();
        CoinService cs=new CoinService();
        ps.ajouter(new Plat());
        cs.ajouter(new Coin(12,"iii","iii","kkk"));
        System.out.println(cs.recuperer());
    }
    
}
