/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Plat;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.PlatService;

/**
 * FXML Controller class
 *
 * @author masmoudi
 */
public class PlatFXMLController implements Initializable {
 List<String> lstFile;
    @FXML
    private TextField nom_p;
    @FXML
    private TextField prix;
    @FXML
    private TextField img_p;
 
    @FXML
    private TextField dispo;
    @FXML
    private Button ajouterP;
    @FXML
    private Button modifierP;
    @FXML
    private Button supprimerP;
    @FXML
    private ListView listPlats;
    @FXML
    private Button butimg;
PlatService cs= new PlatService();

 ObservableList<Plat> plats;
    @FXML
    private TextField description;
    @FXML
    private Button front;
    @FXML
    private Button coin;
    @FXML
    private Button reservation;
    public void UpdateList() {
        plats = (ObservableList<Plat>) cs.recuperer();
        listPlats.getItems().setAll(plats);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UpdateList();
         final FileChooser fileChooser = new FileChooser();
        final Desktop desktop = Desktop.getDesktop();
        butimg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                img_p.clear();
                fileChooser.setTitle("Select Photo Plat");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Documents\\NetBeansProjects\\firstproject-main\\src\\uploads"));
                File file = fileChooser.showOpenDialog(null);

                if (file != null) {
                    List<File> files = Arrays.asList(file);
                    img_p.setText(file.getName());
                }
            }
        });
    }    


    private void getSelected(MouseEvent event) {
        Plat selecteditem = (Plat) listPlats.getSelectionModel().getSelectedItem();
        prix.setText(String.valueOf(selecteditem.getPrix()));
        nom_p.setText(selecteditem.getNom_p());
        description.setText(selecteditem.getDescription());
        img_p.setText(selecteditem.getImg_p());
        dispo.setText(String.valueOf(selecteditem.getDispo()));
        
    }


 @FXML
    private void supprimerPlat(ActionEvent event) {
         try {
            Plat selecteditem = (Plat) listPlats.getSelectionModel().getSelectedItem();
       
        cs.supprimer(selecteditem.getId());
        listPlats.getItems().addAll(cs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Suppression avec succes?");
            alert1.setHeaderText(null);
            alert1.setContentText(" Le Plat est supprimé avec succes!");
            alert1.showAndWait();
             UpdateList();}
             
           catch (Exception ex) {

            ex.printStackTrace();
    }
    
}

 @FXML
    private void ajouterPlat(ActionEvent event) throws Exception {
         if((nom_p.getText().equals("")) || (prix.getText().equals(""))||(img_p.getText().equals(""))||(description.getText().equals(""))||(dispo.getText().equals(""))){
              MediaFX med = new MediaFX("file:///C:/Users/masmoudi/Documents/NetBeansProjects/firstproject-main/b.mp3");
        med.start();
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("obligatoire");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire de remplir tous les champs !");
            alert.showAndWait();
             
         }else if((Float.parseFloat(prix.getText())>500)||(Float.parseFloat(prix.getText())<1)){Alert alert = new Alert(Alert.AlertType.INFORMATION);
         MediaFX med = new MediaFX("file:///C:/Users/masmoudi/Documents/NetBeansProjects/firstproject-main/b.mp3");
        med.start();
            alert.setTitle("champ");
            alert.setHeaderText(null);
            alert.setContentText("verifier le prix !");
          alert.showAndWait();}
                    else {
             try {
           Plat c = new Plat();
        c.setPrix(Float.parseFloat(prix.getText()));
        c.setNom_p(nom_p.getText());
        c.setDescription(description.getText());
        c.setImg_p(img_p.getText());
        c.setDispo(Boolean.parseBoolean(dispo.getText()));
        cs.ajouter(c);
        listPlats.getItems().addAll(cs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Ajout");
            alert1.setHeaderText(null);
            alert1.setContentText("plat ajouté avec succes !");
            alert1.showAndWait();
             UpdateList();}
             
           catch (Exception ex) {

            ex.printStackTrace();
            
            

        }

        }
     
    }

 @FXML
    private void modifierPlat(ActionEvent event) {try{
            Plat c  = new Plat() ;
            Plat selecteditem = (Plat) listPlats.getSelectionModel().getSelectedItem();
               c.setPrix(Float.parseFloat(prix.getText()));
        c.setNom_p(nom_p.getText());
        c.setDescription(description.getText());
        c.setImg_p(img_p.getText());
        c.setDispo(Boolean.parseBoolean(dispo.getText()));
            cs.modifier(selecteditem);
            listPlats.getItems().addAll(cs.recuperer());
          
            
            UpdateList();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modif");
            alert.setHeaderText(null);
            alert.setContentText("plat modifié avec succes !");
            alert.showAndWait();        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
    

@FXML
    private void front(ActionEvent event) {try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("afficherPlats.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();}
    }

    @FXML
    private void coin(ActionEvent event) {
    try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("CoinFXML.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();}}

    @FXML
    private void reservation(ActionEvent event) {
    try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("ReservationFXML.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();}}

    

    

}
