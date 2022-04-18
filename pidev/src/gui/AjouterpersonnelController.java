/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Personnel;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.PersonnelService;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class AjouterpersonnelController implements Initializable {
 List<String> lstFile;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfImage;
    @FXML
    private TextField tfFonction;
    @FXML
    private ListView lid;
PersonnelService ps= new PersonnelService();
 ObservableList<Personnel> personnels;
    @FXML
    private Button btnTelecharger;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button afficherpers;
  public void UpdateList() {
        personnels = (ObservableList<Personnel>) ps.recuperer();
        lid.getItems().setAll(personnels);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateList();
         final FileChooser fileChooser = new FileChooser();
        final Desktop desktop = Desktop.getDesktop();
        btnTelecharger.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tfImage.clear();
                fileChooser.setTitle("Select Photo Personnel");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Documents\\NetBeansProjects\\pidev\\src\\uploads"));
                File file = fileChooser.showOpenDialog(null);

                if (file != null) {
                    List<File> files = Arrays.asList(file);
                    tfImage.setText(file.getName());
                }
            }
        });
    }    

    @FXML
    private void AjouterPersonnel(ActionEvent event) 
    { if((tfNom.getText().equals("")) || (tfPrenom.getText().equals(""))||(tfImage.getText().equals(""))||(tfFonction.getText().equals(""))){
              Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("champs");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire de remplir tous les champs !!!");
            alert.showAndWait();
             
         } 
                    else {
             try {
           Personnel p = new Personnel();
        p.setNom(tfNom.getText());
        p.setPrenom(tfPrenom.getText());
        p.setPhoto(tfImage.getText());
        p.setFonction_id(Integer.parseInt(tfFonction.getText()));
        ps.ajouter(p);
        lid.getItems().addAll(ps.recuperer());
            Alert alert1 = new Alert(AlertType.INFORMATION);
            alert1.setTitle("Ajout avec succes");
            alert1.setHeaderText(null);
            alert1.setContentText("Personnel ajouté avec succes!");
            alert1.showAndWait();
             UpdateList();}
             
           catch (Exception ex) {

            ex.printStackTrace();
            
            

        }

        }
                            } 


    @FXML
    private void ModifierPersonnel(ActionEvent event) {try {

                   
            Personnel p  = new Personnel() ;
            Personnel selecteditem = (Personnel) lid.getSelectionModel().getSelectedItem();
            selecteditem.setFonction_id(Integer.parseInt(tfFonction.getText()));
            selecteditem.setNom(tfNom.getText());
            selecteditem.setPhoto(tfImage.getText());
           ps.modifier(selecteditem);
        lid.getItems().addAll(ps.recuperer());
          
            
            UpdateList();
Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ma Modification");
            alert.setHeaderText(null);
            alert.setContentText("Personnel bien modifié,vérifiez !");
            alert.showAndWait();        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    @FXML
    private void getSelected(MouseEvent event) {Personnel selecteditem = (Personnel) lid.getSelectionModel().getSelectedItem();
        tfNom.setText(selecteditem.getNom());
        tfImage.setText(selecteditem.getPhoto());
        tfPrenom.setText(selecteditem.getPrenom());
        tfFonction.setText(String.valueOf(selecteditem.getFonction_id()));
    }

    @FXML
    private void SupprimerPersonnel(ActionEvent event) {
            try {
            Personnel selecteditem = (Personnel) lid.getSelectionModel().getSelectedItem();
       
        ps.supprimer(selecteditem.getId());
        lid.getItems().addAll(ps.recuperer());
            Alert alert1 = new Alert(AlertType.INFORMATION);
            alert1.setTitle("Suppression avec succes?");
            alert1.setHeaderText(null);
            alert1.setContentText("Personnel supprimé avec succes!");
            alert1.showAndWait();
             UpdateList();}
             
           catch (Exception ex) {

            ex.printStackTrace();
            
            

        }
    }



    @FXML
    private void afficherpers(ActionEvent event) {
        
     try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("afficherpersonnel.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();
        }  
        
        
    }

    @FXML
    private void afficherfonction(ActionEvent event) {
         try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("Fonction.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();
        }  
    }
    
    }
    

