/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reservation;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ReservationService;
import services.QrCode;

/**
 * FXML Controller class
 *
 * @author masmoudi
 */
public class ReservationFXMLController implements Initializable {

    @FXML
    private Button plat;
    @FXML
    private Button QrCode;
    @FXML
    private ImageView qr;
    @FXML
    private TextField nb_personnes;
    @FXML
    private TextField heure;
    @FXML
    private DatePicker date;
    @FXML
    private Button ajouterR;
    @FXML
    private Button supprimerR;
    @FXML
    private Button modifierR;
    @FXML
    private ListView listReservations;
    ReservationService rs= new ReservationService();
QrCode q = new QrCode();
 ObservableList<Reservation> reservations;
    @FXML
    private TextField nom;
public void UpdateList() {
        reservations = (ObservableList<Reservation>) rs.recuperer();
        listReservations.getItems().setAll(reservations);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UpdateList();
         final FileChooser fileChooser = new FileChooser();
        final Desktop desktop = Desktop.getDesktop();
       
    }    
  


    private void getSelected(MouseEvent event) {
        Reservation selecteditem = (Reservation) listReservations.getSelectionModel().getSelectedItem();
        nb_personnes.setText(String.valueOf(selecteditem.getNb_personnes()));
       date.getValue();
        //heure.setText(String.valueOf(selecteditem.getHeure()));
        
    }


 @FXML
    private void supprimerReservation(ActionEvent event) {
         try {
            Reservation selecteditem = (Reservation) listReservations.getSelectionModel().getSelectedItem();
       
        rs.supprimer(selecteditem.getNum());
        listReservations.getItems().addAll(rs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Suppression avec succes?");
            alert1.setHeaderText(null);
            alert1.setContentText(" La reservation est supprimée avec succes!");
            alert1.showAndWait();
             UpdateList();}
             
           catch (Exception ex) {

            ex.printStackTrace();
    }
    
}

 @FXML
    private void ajouterReservation(ActionEvent event) {
         if((nb_personnes.getText().equals("")) || (date.getValue().equals(""))||(heure.getText().equals(""))){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("obligatoire");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire de remplir tous les champs !");
            alert.showAndWait();
             
         }else if((Integer.parseInt(nb_personnes.getText())>20)||(Integer.parseInt(nb_personnes.getText())<1)){Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("champ");
            alert.setHeaderText(null);
            alert.setContentText("verifier le nombre de personnes !");
          alert.showAndWait();}
                    else {
             try {
           Reservation c = new Reservation();
        c.setNb_personnes(Integer.parseInt(nb_personnes.getText()));
       c.setDate(Date.valueOf(date.getValue()));
        //c.setHeure(Date.valueOf(heure.getValue()));
        rs.ajouter(c);
        listReservations.getItems().addAll(rs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Ajout");
            alert1.setHeaderText(null);
            alert1.setContentText("reservation ajoutée avec succes !");
            alert1.showAndWait();
             UpdateList();}
             
           catch (Exception ex) {

            ex.printStackTrace();
            
            

        }

        }
     
    }

 @FXML
    private void modifierReservation(ActionEvent event) {try{
             Reservation c = new Reservation();
            Reservation selecteditem = (Reservation) listReservations.getSelectionModel().getSelectedItem();
        c.setNb_personnes(Integer.parseInt(nb_personnes.getText()));
       c.setDate(Date.valueOf(date.getValue()));
        //c.setHeure(heure.getText());
            rs.modifier(selecteditem);
            listReservations.getItems().addAll(rs.recuperer());
          
            
            UpdateList();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modif");
            alert.setHeaderText(null);
            alert.setContentText("reservation modifiée avec succes !");
            alert.showAndWait();        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
    



    @FXML
    private void plat(ActionEvent event) {
        try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("PlatFXML.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();}
    }

    @FXML
    private void genererQRCode(ActionEvent event) throws Exception { q.CreateQrCode(nom.getText());
        QrCode.setDisable(true);
        MediaFX med = new MediaFX("file:///C:/Users/masmoudi/Documents/NetBeansProjects/firstproject-main/a.mp3");
        med.start();
        String pat = "C:\\Users\\masmoudi\\Documents\\NetBeansProjects\\firstproject-main\\"+nom.getText()+".png";
        File fff = new File(pat);
                System.out.println(fff.toURI().toString());
Image i = new Image(fff.toURI().toString());  
//c.saveQr(pays.getText(), fff.toURI().toString());
        qr.setImage(i);
        
    }
    

    

}
