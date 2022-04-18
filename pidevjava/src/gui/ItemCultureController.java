/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Culture;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import javafx.scene.image.Image;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.CultureService;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ItemCultureController implements Initializable {

    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private Label section;
    @FXML
    private Label score;
    
    CultureService cs= new CultureService();
    Culture c;
    ObservableList<Culture> cultures;
    @FXML
    private Label itemPaysC;
    @FXML
    private Label itemTexteC;
    @FXML
    private Label itemDateC;
    @FXML
    private ImageView itemFlagC;
    @FXML
    private Label itemRefC;
    @FXML
    private Label culture;
    @FXML
    private Label labelPaysC;
    @FXML
    private Label labelDateC;
    @FXML
    private Label labelTexteC;
    @FXML
    private Label labelRefC;

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
        itemRefC.setText(c.getRef());
        itemPaysC.setText(c.getPays());
        itemTexteC.setText(c.getTexte());
        itemDateC.setText(c.getDate_ajout().toString());
        
      file = new File("src/uploads/" + c.getFlag());
      itemFlagC.setImage(new Image(file.toURI().toString()));
      System.out.println("culture");
                
    }
    
}
