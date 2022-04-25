/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Personnel;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import services.PersonnelService;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class AfficherpersonnelController implements Initializable {

    @FXML
    private ScrollPane Scrollepane;
    @FXML
    private VBox pnl_scroll;
PersonnelService ps= new PersonnelService();
    @FXML
    private ComboBox<String> combobox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshNodes();
        
          ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Nom",
        "Prenom"
    );
    combobox.setItems(options);
    }  
     private void refreshNodes() {
        pnl_scroll.getChildren().clear();

        List<Personnel> listp = ps.getAll();
        Node[] nodes = new Node[listp.size()];
        int i = 0;

        for (Personnel each : listp) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("itempersonnel.fxml"));
            ItempersonnelController cont = new ItempersonnelController();
            try {
                cont.p = each;
                loader.setController(cont);

                nodes[i] = (Node) loader.load();

                // nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                pnl_scroll.getChildren().add(nodes[i]);

            } catch (IOException ex) {
                Logger.getLogger(AfficherpersonnelController.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
    }

    @FXML
    private void trier(ActionEvent event) {
        if (combobox.getValue().equals("Nom")  )  
                {
                    pnl_scroll.getChildren().clear();

        List<Personnel> listp = ps.trinom();
        Node[] nodes = new Node[listp.size()];
        int i = 0;

        for (Personnel each : listp) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("itempersonnel.fxml"));
            ItempersonnelController cont = new ItempersonnelController();
            try {
                cont.p = each;
                loader.setController(cont);

                nodes[i] = (Node) loader.load();

                // nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                pnl_scroll.getChildren().add(nodes[i]);

            } catch (IOException ex) {
                Logger.getLogger(AfficherpersonnelController.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
                
                }
       else if (combobox.getValue().equals("Prenom")  )  
                {
                       pnl_scroll.getChildren().clear();

        List<Personnel> listp = ps.triprenom();
        Node[] nodes = new Node[listp.size()];
        int i = 0;

        for (Personnel each : listp) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("itempersonnel.fxml"));
            ItempersonnelController cont = new ItempersonnelController();
            try {
                cont.p = each;
                loader.setController(cont);

                nodes[i] = (Node) loader.load();

                // nodes[i] = (Node)FXMLLoader.load(getClass().getResource("Item.fxml"));
                pnl_scroll.getChildren().add(nodes[i]);

            } catch (IOException ex) {
                Logger.getLogger(AfficherpersonnelController.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }
                
                }
                
                }
    
}
