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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
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
                Logger.getLogger(ItemCultureController.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
    }
    
}
