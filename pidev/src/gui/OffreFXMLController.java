/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Culture;
import entities.Offre;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.CultureService;
import services.OffreService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class OffreFXMLController implements Initializable {

    @FXML
    private Button btnAddO;
    @FXML
    private ListView listOffres;
    @FXML
    private Button btnUpdateO;
    @FXML
    private Button btnDeleteO;
    @FXML
    private TextField tfRemiseO;
    @FXML
    private TextField tfDescO;
    @FXML
    private TextField tfTitreO;
    @FXML
    private DatePicker tfDateExpO;
    @FXML
    private TextField tfImageO;
    @FXML
    private Button brImageO;
    @FXML
    private CheckBox cbExpireO;

    
    OffreService os= new OffreService();

    ObservableList<Offre> offres;
 
    
    /**
     * Initializes the controller class.
     */
    
    public void UpdateLO() {
        offres = (ObservableList<Offre>) os.recuperer();
        listOffres.getItems().setAll(offres);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UpdateLO();
         final FileChooser fileChooser = new FileChooser();
        final Desktop desktop = Desktop.getDesktop();
        brImageO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tfImageO.clear();
                fileChooser.setTitle("choisir photo Offre");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Documents\\NetBeansProjects\\pidev\\src\\uploads"));
                File file = fileChooser.showOpenDialog(null);

                if (file != null) {
                    List<File> files = Arrays.asList(file);
                    tfImageO.setText(file.getName());
                }
            }
        });
    }    

    @FXML
    private void addOffre(ActionEvent event) {
        
        if((tfTitreO.getText().equals("")) || (tfDescO.getText().equals(""))||(tfRemiseO.getText().equals(""))||(tfImageO.getText().equals(""))||(tfDateExpO.toString().equals(""))){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("obligatoire");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire de remplir tous les champs !");
            alert.showAndWait();
             
         }
        else if((Float.parseFloat(tfRemiseO.getText())>100)||((Float.parseFloat(tfRemiseO.getText()))<5)){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("reference");
            alert.setHeaderText(null);
            alert.setContentText("La remise doit etre entre 5% et 100%!");
            alert.showAndWait();
             
         }
                    else {
             try {
           Offre o = new Offre();
        o.setTitre(tfTitreO.getText());
        o.setDescription(tfDescO.getText());
        o.setRemise(Float.valueOf(tfRemiseO.getText()));
        o.setImage(tfImageO.getText());
        o.setExp_date(Date.valueOf(tfDateExpO.getValue()));
        o.setExpire(Boolean.valueOf(cbExpireO.getText()));
        os.ajouter(o);
        listOffres.getItems().addAll(os.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Ajout");
            alert1.setHeaderText(null);
            alert1.setContentText("offre ajoutée avec succes !");
            alert1.showAndWait();
             UpdateLO();}
             
           catch (Exception ex) {

            ex.printStackTrace();
            
            

        }

        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
        Offre selecteditem = (Offre) listOffres.getSelectionModel().getSelectedItem();
        tfTitreO.setText(selecteditem.getTitre());
        tfDescO.setText(selecteditem.getDescription());
        tfRemiseO.setText(String.valueOf(selecteditem.getRemise()));
        tfImageO.setText(String.valueOf(selecteditem.getImage()));
       // tfDateExpO.setD(Date.valueOf(selecteditem.getExp_date()));
        //cbExpireO.set
    }

    @FXML
    private void updateOffre(ActionEvent event) {
          if((tfTitreO.getText().equals("")) || (tfDescO.getText().equals(""))||(tfRemiseO.getText().equals(""))||(tfImageO.getText().equals(""))||(tfDateExpO.toString().equals(""))){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("obligatoire");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire de remplir tous les champs !");
            alert.showAndWait();
             
         }
        else if((Float.parseFloat(tfRemiseO.getText())>100)||((Float.parseFloat(tfRemiseO.getText()))<5)){
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("reference");
            alert.setHeaderText(null);
            alert.setContentText("La remise doit etre entre 5% et 100%!");
            alert.showAndWait();
             
         }
                    else { try{
            Offre o  = new Offre() ;
            Offre selecteditem = (Offre) listOffres.getSelectionModel().getSelectedItem();
            selecteditem.setTitre(tfTitreO.getText());
            selecteditem.setDescription(tfDescO.getText());
            selecteditem.setRemise(Float.valueOf(tfRemiseO.getText()));
            selecteditem.setImage(tfImageO.getText());
            selecteditem.setExp_date(Date.valueOf(tfDateExpO.getValue()));
            os.modifier(selecteditem);
            listOffres.getItems().addAll(os.recuperer());
          
            
            UpdateLO();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modif");
            alert.setHeaderText(null);
            alert.setContentText("Culture modifiée avec succes !");
            alert.showAndWait();        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
    }
    @FXML
    private void deleteOffre(ActionEvent event) { try {
            Offre selecteditem = (Offre) listOffres.getSelectionModel().getSelectedItem();
       
            os.supprimer(selecteditem.getId());
            listOffres.getItems().addAll(os.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Suppression avec succes");
            alert1.setHeaderText(null);
            alert1.setContentText("offre supprimée avec succes!");
            alert1.showAndWait();
             UpdateLO();}
             
           catch (Exception ex) {

            ex.printStackTrace();
    }
    }

    @FXML
    private void fronto(ActionEvent event) {
          try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("OffreFrontFXML.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();
        }  
    }

    @FXML
    private void cultureb(ActionEvent event) {
          try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("CultureFXML.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace(); 
        }  
    }
   
}
    

