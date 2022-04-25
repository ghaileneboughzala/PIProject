/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Fonction;
import entities.Fonction;
import entities.Fonction;
import entities.Fonction;
import entities.Fonction;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.FonctionService;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class FonctionController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private TextField tfNomf;
    @FXML
    private TextField tfsalaire;
    @FXML
    private TextField tfnbheure;
    @FXML
    private ListView lid;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button afficherpers;
        

    
    FonctionService fs= new FonctionService();
 ObservableList<Fonction> fonctions;
 public void UpdateList() {
        fonctions = (ObservableList<Fonction>) fs.recuperer();
        lid.getItems().setAll(fonctions);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateList();
    }    

    @FXML
    private void AjouterFonction(ActionEvent event) {
          
     if((tfNomf.getText().equals("")) || (tfsalaire.getText().equals("")) || ((tfnbheure.getText().equals("")))){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("champs");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire de remplir tous les champs !!!");
            alert.showAndWait();
             
         } 
     else if ((Float.parseFloat(tfsalaire.getText())) < 400 || (Float.parseFloat(tfsalaire.getText()))  > 2000 ) 
     {   
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("champs");
            alert.setHeaderText(null);
            alert.setContentText("Les salaires doivent être entre 400 et 2000 DT");
            alert.showAndWait(); }
     
     else if ((Integer.parseInt(tfnbheure.getText())) < 20 || (Integer.parseInt(tfnbheure.getText()))  > 70 ) 
     {   
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("champs");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre d'heure par semaine doit être entre 20 H et 70 H");
            alert.showAndWait(); }
     
           else {
             try {
           Fonction f = new Fonction();
        f.setNom_f(tfNomf.getText());
        f.setSalaire(Float.parseFloat(tfsalaire.getText()));
        f.setNb_heure(Integer.parseInt(tfnbheure.getText()));
        fs.ajouter(f);
        lid.getItems().addAll(fs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Ajout avec succes");
            alert1.setHeaderText(null);
            alert1.setContentText("Fonction ajouté avec succes!");
            alert1.showAndWait();
             UpdateList();}
             
           catch (Exception ex) {

            ex.printStackTrace();
            
            

        }

        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
        Fonction selecteditem = (Fonction) lid.getSelectionModel().getSelectedItem();
        tfNomf.setText(selecteditem.getNom_f());
        tfsalaire.setText(String.valueOf(selecteditem.getSalaire()));
        tfnbheure.setText(String.valueOf(selecteditem.getNb_heure()));
    }

    @FXML
    private void ModifierFonction(ActionEvent event) {
         if((tfNomf.getText().equals("")) || (tfsalaire.getText().equals("")) || ((tfnbheure.getText().equals("")))){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("champs");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire de remplir tous les champs !!!");
            alert.showAndWait();
             
         } 
     else if ((Float.parseFloat(tfsalaire.getText())) < 400 || (Float.parseFloat(tfsalaire.getText()))  > 2000 ) 
     {   
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("champs");
            alert.setHeaderText(null);
            alert.setContentText("Les salaires doivent être entre 400 et 2000 DT");
            alert.showAndWait(); }
     
     else if ((Integer.parseInt(tfnbheure.getText())) < 20 || (Integer.parseInt(tfnbheure.getText()))  > 70 ) 
     {   
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("champs");
            alert.setHeaderText(null);
            alert.setContentText("Le nombre d'heure par semaine doit être entre 20 H et 70 H");
            alert.showAndWait(); }
     
           else {
        try {

                   
            Fonction p  = new Fonction() ;
            Fonction selecteditem = (Fonction) lid.getSelectionModel().getSelectedItem();
            selecteditem.setNom_f((tfNomf.getText()));
            selecteditem.setSalaire(Float.parseFloat(tfsalaire.getText()));
            selecteditem.setNb_heure(Integer.parseInt(tfnbheure.getText()));
           fs.modifier(selecteditem);
        lid.getItems().addAll(fs.recuperer());
          
            
            UpdateList();
Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ma Modification");
            alert.setHeaderText(null);
            alert.setContentText("Fonction bien modifié,vérifiez !");
            alert.showAndWait();        } 
        catch (Exception ex) {
            ex.printStackTrace();

        }
    }}

    @FXML
    private void SupprimerFonction(ActionEvent event) {
             try {
            Fonction selecteditem = (Fonction) lid.getSelectionModel().getSelectedItem();
       
        fs.supprimer(selecteditem.getId());
        lid.getItems().addAll(fs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Suppression avec succes?");
            alert1.setHeaderText(null);
            alert1.setContentText("Fonction supprimé avec succes!");
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
            Parent root = FXMLLoader.load(getClass().getResource("ajouterpersonnel.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();
        }  
        
    }
    
}
