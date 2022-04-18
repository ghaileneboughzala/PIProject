/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Offre;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.OffreService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ItemOffreController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;
    
    
    @FXML
    private Label offre;
    @FXML
    private Label labelRemiseO;
    @FXML
    private Label labelDateO;
    @FXML
    private Label labelDescO;
    @FXML
    private Label itemRemiseO;
    @FXML
    private Label itemDescO;
    @FXML
    private Label itemDateO;
    @FXML
    private Label section;
    @FXML
    private Label score;
    @FXML
    private Label labelTitreO;
    @FXML
    private ImageView itemImageO;
    @FXML
    private Label itemTitreO;
    
    OffreService os = new OffreService();
    Offre o;
    ObservableList<Offre> offres;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateList();
    }    
    
    private void updateList (){
     File file;
        itemTitreO.setText(o.getTitre());
        itemRemiseO.setText(String.valueOf(o.getRemise()));
        itemDescO.setText(o.getDescription());
        //itemDateO.setText(o.getExp_date().toString());
        
      file = new File("src/uploads/" + o.getImage());
      itemImageO.setImage(new Image(file.toURI().toString()));
      System.out.println("offre");
                
    }
}
