/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Plat;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.PlatService;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author masmoudi
 */
public class ItemPlatFXMLController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Label labeldescription;
    @FXML
    private Label labelprix;
    
    @FXML
    private Label id;
    @FXML
    private ImageView img_p;
    @FXML
    private Label prix;
    @FXML
    private Label description;
    

    /**
     * Initializes the controller class.
     */
    PlatService ps= new PlatService();
Plat p ;
 ObservableList<Plat> plats;
    
    @FXML
    private Label nom_p;
    @FXML
    private Label section;
    @FXML
    private Label score;
    @FXML
    private Label labelpays;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      updatelist();
    }    
    private void updatelist (){
     File file;
        nom_p.setText(p.getNom_p());
        prix.setText(String.valueOf(p.getPrix()));
        description.setText(p.getDescription());
        
      file = new File("src/uploads/" + p.getImg_p());
      img_p.setImage(new Image(file.toURI().toString()));
      System.out.println("test plat");
                
}
  
    
}
