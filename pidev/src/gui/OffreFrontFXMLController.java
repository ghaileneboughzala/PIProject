/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Offre;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.OffreService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class OffreFrontFXMLController implements Initializable {

    @FXML
    private ScrollPane Scrollepane;
    @FXML
    private VBox pnl_scrollO;

    OffreService os = new OffreService();
    @FXML
    private Button calcul;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshNodes();
    }    
    
    private void refreshNodes() {
        pnl_scrollO.getChildren().clear();

        List<Offre> listO = os.recupererList();
        Node[] nodes = new Node[listO.size()];
        int i = 0;

        for (Offre each : listO) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemOffre.fxml"));
            ItemOffreController cont = new ItemOffreController();
            try {
                cont.o = each;
                loader.setController(cont);

                nodes[i] = (Node) loader.load();

                // nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                pnl_scrollO.getChildren().add(nodes[i]);

            } catch (IOException ex) {
                Logger.getLogger(ItemOffreController.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
    }

    @FXML
    private void Calculer(ActionEvent event) {
        try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("CalculatriceFXML.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();
        }  
    }
    
    
    
}
