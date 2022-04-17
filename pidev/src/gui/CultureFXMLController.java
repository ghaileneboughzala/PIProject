/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Culture;
import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import services.CultureService;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class CultureFXMLController implements Initializable {

    @FXML
    private Button btnAddC;
    @FXML
    private ListView listCultures;
    @FXML
    private Button btnUpdateC;
    @FXML
    private Button btnDeleteC;
    @FXML
    private TextField tfTexteC;
    @FXML
    private TextField tfPaysC;
    @FXML
    private TextField tfRefC;
    @FXML
    private DatePicker tfDateAjoutC;
    @FXML
    private Button brImageC;
    
   
    
CultureService cs= new CultureService();

 ObservableList<Culture> cultures;
    @FXML
    private TextField tfFlagC;
    /**
     * Initializes the controller class.
     */
    
    public void UpdateL() {
        cultures = (ObservableList<Culture>) cs.recuperer();
        listCultures.getItems().setAll(cultures);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UpdateL();
         final FileChooser fileChooser = new FileChooser();
        final Desktop desktop = Desktop.getDesktop();
        brImageC.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tfFlagC.clear();
                fileChooser.setTitle("Select Photo Personnel");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Documents\\NetBeansProjects\\pidev\\src\\uploads"));
                File file = fileChooser.showOpenDialog(null);

                if (file != null) {
                    List<File> files = Arrays.asList(file);
                    tfFlagC.setText(file.getName());
                }
            }
        });
    }    


    @FXML
    private void getSelected(MouseEvent event) {
        Culture selecteditem = (Culture) listCultures.getSelectionModel().getSelectedItem();
        tfRefC.setText(selecteditem.getRef());
        tfPaysC.setText(selecteditem.getPays());
        tfTexteC.setText(selecteditem.getTexte());
        tfFlagC.setText(String.valueOf(selecteditem.getFlag()));
        //tfDateAjoutC.setDate(Date.valueOf(selecteditem.getDate_ajout()));
    }


    private void SupprimerCulture(ActionEvent event) {
         try {
            Culture selecteditem = (Culture) listCultures.getSelectionModel().getSelectedItem();
       
        cs.supprimer(selecteditem.getRef());
        listCultures.getItems().addAll(cs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Suppression avec succes?");
            alert1.setHeaderText(null);
            alert1.setContentText("Personnel supprimé avec succes!");
            alert1.showAndWait();
             UpdateL();}
             
           catch (Exception ex) {

            ex.printStackTrace();
    }
    
}

    @FXML
    private void addCulture(ActionEvent event) {
         if((tfRefC.getText().equals("")) || (tfPaysC.getText().equals(""))||(tfTexteC.getText().equals(""))||(tfFlagC.getText().equals(""))||(tfDateAjoutC.toString().equals(""))){
              Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("obligatoire");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire de remplir tous les champs !");
            alert.showAndWait();
             
         }
                    else {
             try {
           Culture c = new Culture();
        c.setRef(tfRefC.getText());
        c.setPays(tfPaysC.getText());
        c.setTexte(tfTexteC.getText());
        c.setFlag(tfFlagC.getText());
        c.setPays(tfPaysC.getText());
        c.setDate_ajout(Date.valueOf(tfDateAjoutC.getValue()));
        cs.ajouter(c);
        listCultures.getItems().addAll(cs.recuperer());
            Alert alert1 = new Alert(AlertType.INFORMATION);
            alert1.setTitle("Ajout");
            alert1.setHeaderText(null);
            alert1.setContentText("culture ajoutée avec succes !");
            alert1.showAndWait();
             UpdateL();}
             
           catch (Exception ex) {

            ex.printStackTrace();
            
            

        }

        }
     
    }

    @FXML
    private void updateCulture(ActionEvent event) {try{
            Culture c  = new Culture() ;
            Culture selecteditem = (Culture) listCultures.getSelectionModel().getSelectedItem();
            String ref = selecteditem.getRef();
            selecteditem.setRef(tfRefC.getText());
            selecteditem.setPays(tfPaysC.getText());
            selecteditem.setTexte(tfTexteC.getText());
            selecteditem.setFlag(tfFlagC.getText());
            selecteditem.setDate_ajout(Date.valueOf(tfDateAjoutC.getValue()));
            cs.update(ref,selecteditem);
            listCultures.getItems().addAll(cs.recuperer());
          
            
            UpdateL();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Modif");
            alert.setHeaderText(null);
            alert.setContentText("Culture modifiée avec succes !");
            alert.showAndWait();        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    @FXML
    private void deleteCulture(ActionEvent event) {
        try {
            Culture selecteditem = (Culture) listCultures.getSelectionModel().getSelectedItem();
       
            cs.supprimer(selecteditem.getRef());
            listCultures.getItems().addAll(cs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Suppression avec succes?");
            alert1.setHeaderText(null);
            alert1.setContentText("Personnel supprimé avec succes!");
            alert1.showAndWait();
             UpdateL();}
             
           catch (Exception ex) {

            ex.printStackTrace();
    }
    }
}
