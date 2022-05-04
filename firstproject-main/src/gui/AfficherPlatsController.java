/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Plat;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import services.PlatService;

/**
 * FXML Controller class
 *
 * @author masmoudi
 */
public class AfficherPlatsController implements Initializable {

    

      @FXML
    private AnchorPane Scrollepane;
    @FXML
    private VBox pnl_scroll;
PlatService ps= new PlatService();
    @FXML
    private TextField rech;
    @FXML
    private Button recherche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshNodes();
    }  
     private void refreshNodes() {
        pnl_scroll.getChildren().clear();

        List<Plat> listp = ps.getAll();
        Node[] nodes = new Node[listp.size()];
        int i = 0;

        for (Plat each : listp) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("itemPlatFXML.fxml"));
            ItemPlatFXMLController cont = new ItemPlatFXMLController();
            try {
                cont.p = each;
                loader.setController(cont);

                nodes[i] = (Node) loader.load();

                // nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                pnl_scroll.getChildren().add(nodes[i]);

            } catch (IOException ex) {
                Logger.getLogger(AfficherPlatsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
    }

    public void recherche() throws Exception {
        // username.setText(Authentication.authenticatedUser.getUsername());
        
        
            
        
        
        PlatService PlatService = new PlatService();
        ArrayList<Plat> magazins = PlatService.recherche(rech.getText());
        System.out.println(magazins);
         
        
         if(magazins.isEmpty())
         {
             //med.start();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Attention");
            alert.setContentText("Pas de résultat!!!!!");
            alert.showAndWait();
         }
         else{pnl_scroll.getChildren().clear();

        List<Plat> listp = ps.getAll();
        Node[] nodes = new Node[listp.size()];
        int i = 0;

        for (Plat each : listp) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("itemPlatFXML.fxml"));
            ItemPlatFXMLController cont = new ItemPlatFXMLController();
            try {
                cont.p = each;
                loader.setController(cont);

                nodes[i] = (Node) loader.load();

                // nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                pnl_scroll.getChildren().add(nodes[i]);

            } catch (IOException ex) {
                Logger.getLogger(AfficherPlatsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        };
        
        //System.out.println(magazinsObs);
        //magazinListTable.setItems(magazinsObs);}
        }
        
    } 
     
    
    
}
