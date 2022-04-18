/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Culture;
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
import services.CultureService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class CultureFrontFXMLController implements Initializable {

    @FXML
    private ScrollPane Scrollepane;
    
    
    CultureService cs= new CultureService();
    @FXML
    private VBox pnl_scrollO;

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

        List<Culture> listC = cs.recupererList();
        Node[] nodes = new Node[listC.size()];
        int i = 0;

        for (Culture each : listC) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemCulture.fxml"));
            ItemCultureController cont = new ItemCultureController();
            try {
                cont.c = each;
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