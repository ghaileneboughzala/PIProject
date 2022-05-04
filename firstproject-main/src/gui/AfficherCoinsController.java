/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Coin;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import services.CoinService;

/**
 * FXML Controller class
 *
 * @author masmoudi
 */
public class AfficherCoinsController implements Initializable {

    @FXML
    private AnchorPane Scrollepane;
    @FXML
    private VBox pnl_scroll;
CoinService ps= new CoinService();
    @FXML
    private TextField rechpays;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshNodes();
    }  
     private void refreshNodes() {
        pnl_scroll.getChildren().clear();

        List<Coin> listp = ps.getAll();
        Node[] nodes = new Node[listp.size()];
        int i = 0;

        for (Coin each : listp) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("itemCoinFXML.fxml"));
            ItemCoinFXMLController cont = new ItemCoinFXMLController();
            try {
                cont.c = each;
                loader.setController(cont);

                nodes[i] = (Node) loader.load();

                // nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                pnl_scroll.getChildren().add(nodes[i]);

            } catch (IOException ex) {
                Logger.getLogger(AfficherCoinsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
    }

    @FXML
    private void rechercher(ActionEvent event) {
        
         pnl_scroll.getChildren().clear();

        List<Coin> listp = ps.recherche(rechpays.getText());
        Node[] nodes = new Node[listp.size()];
        int i = 0;

        for (Coin each : listp) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("itemCoinFXML.fxml"));
            ItemCoinFXMLController cont = new ItemCoinFXMLController();
            try {
                cont.c = each;
                loader.setController(cont);

                nodes[i] = (Node) loader.load();

                // nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                pnl_scroll.getChildren().add(nodes[i]);

            } catch (IOException ex) {
                Logger.getLogger(AfficherCoinsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
        
    }
    
    
}
