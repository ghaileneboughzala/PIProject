/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Culture;
import entities.Depenses;
import entities.Fonction;
import entities.Offre;
import entities.Personnel;
import entities.Voyage;
import services.CultureService;
import services.DepensesService;
import services.PersonnelService;
import services.FonctionService;
import services.OffreService;
import services.VoyageService;
/**
 *
 * @author Skander
 */
public class Main {

    public static void main(String[] args) {
        PersonnelService ps = new PersonnelService();
        FonctionService fs = new FonctionService();
      //  fs.ajouter(new Fonction ("rsthsdhtr", 750f, 53));
    //    fs.modifier(new Fonction(19,"fsgbdfwhbdrfhbfg",750f,52));
        //ps.ajouter(new Personnel(1, "Ben", "Ali","aziz.jpg"));
        System.out.println(ps.recuperer());
        ps.modifier(new Personnel(29,1, "Bennnn", "Aliiiii","aziz.jpg"));
        //ps.supprimer(28);
        
        CultureService cs= new CultureService();
        ///cs.ajouter(new Culture("gvgsdgvsdgv","fbggdfb","sfgvqfsgb","TN5.png"));
       // cs.update("gvgsdgvsdgv", new Culture("dfdsgfsgr","gvgsdgvsdgv","sdgfsg","TN5.png"));
        //cs.supprimer("Mr");
        OffreService os= new OffreService();
        //os.ajouter(new Offre ("menuisier", "fevre", 40f,"efvtr"));
       // os.modifier(new Offre (5,"hhhhh","ggggg",50f,"kkkkk"));
        System.out.println(os.recuperer());
        VoyageService vs= new VoyageService();
       //vs.ajouter(new Voyage (3,30,"paris",true));
        vs.modifier(new Voyage(4,3,15,"pariiis",true));
       DepensesService ds = new DepensesService();
       //ds.ajouter(new Depenses("facture",400));
       //ds.modifier(new Depenses(9,"salaire",2000));
       //ds.supprimer(9);
       System.out.println(ds.recuperer());
    }

}
