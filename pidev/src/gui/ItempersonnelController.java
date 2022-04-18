/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Personnel;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.PersonnelService;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class ItempersonnelController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Label role;
    @FXML
    private Label labelNom;
    @FXML
    private Label labelPrenom;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    private Label phone;
    @FXML
    private Label section;
    @FXML
    private Label score;
    @FXML
    private Label id;
    @FXML
    private ImageView img;
PersonnelService ps= new PersonnelService();
Personnel p ;
 ObservableList<Personnel> personnels;
    @FXML
    private Label fonction;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      updatelist();
    }    
    private void updatelist (){
     File file;
        nom.setText(p.getNom());
        prenom.setText(p.getPrenom());
        if (p.getFonction_id()== 1)
        fonction.setText("chef");
        else if (p.getFonction_id()== 4)
             fonction.setText("gérant");
         else if (p.getFonction_id()== 7)
             fonction.setText("caissier");
        else if (p.getFonction_id()== 12)
             fonction.setText("patissier");
         else if (p.getFonction_id()== 17)
             fonction.setText("ouvrier");
        
      file = new File("src/uploads/" + p.getPhoto());
      img.setImage(new Image(file.toURI().toString()));
      System.out.println("test personnel");
                
}
    
}
