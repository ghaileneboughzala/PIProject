/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Coin;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.CoinService;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author masmoudi
 */
public class ItemCoinFXMLController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Label pays;
    @FXML
    private Label nb_places;
    @FXML
    private Label description_c;
    
    @FXML
    private Label id;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
       
CoinService ps= new CoinService();
Coin c ;
 ObservableList<Coin> coins;
    @FXML
    private Label labeldescription;
    @FXML
    private Label labelnb_places;
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
        pays.setText(c.getPays());
        nb_places.setText(String.valueOf(c.getNb_places()));
        description_c.setText(c.getDescription_c());
        
      file = new File("src/uploads/" + c.getImg());
      img.setImage(new Image(file.toURI().toString()));
      System.out.println("test coin");
                
}
    
}
