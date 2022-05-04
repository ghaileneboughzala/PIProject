/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Coin;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.CoinService;
import services.QrCode;

/**
 * FXML Controller class
 *
 * @author masmoudi
 */
public class CoinFXMLController implements Initializable {
 List<String> lstFile;
    @FXML
    private TextField nb_places;
    @FXML
    private TextField pays;
    @FXML
    private TextField img;
    @FXML
    private TextField description_c;
    @FXML
    private Button ajouterC;
    @FXML
    private Button supprimerC;

    /**
     * Initializes the controller class.
     */
    CoinService cs= new CoinService();

 ObservableList<Coin> coins;
   
    @FXML
    private Button butimg;
    @FXML
    private ListView listCoins;
    @FXML
    private Button modifierC;
    @FXML
    private Button plat;
     QrCode q = new QrCode();
    Coin c = new Coin();
    @FXML
    private Button reservation;
    /**
     * Initializes the controller class.
     */
    
    public void UpdateList() {
        coins = (ObservableList<Coin>) cs.recuperer();
        listCoins.getItems().setAll(coins);
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
                img.clear();
                fileChooser.setTitle("Select Photo Coin");
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Documents\\NetBeansProjects\\firstproject-main\\src\\uploads"));
                File file = fileChooser.showOpenDialog(null);

                if (file != null) {
                    List<File> files = Arrays.asList(file);
                    img.setText(file.getName());
                }
            }
        });
    }    


    private void getSelected(MouseEvent event) {
        Coin selecteditem = (Coin) listCoins.getSelectionModel().getSelectedItem();
        nb_places.setText(String.valueOf(selecteditem.getNb_places()));
        pays.setText(selecteditem.getPays());
        description_c.setText(selecteditem.getDescription_c());
        img.setText(selecteditem.getImg());
        
    }


    @FXML
    private void supprimerCoin(ActionEvent event) {
         try {
            Coin selecteditem = (Coin) listCoins.getSelectionModel().getSelectedItem();
       
        cs.supprimer(selecteditem.getId());
        listCoins.getItems().addAll(cs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Suppression avec succes?");
            alert1.setHeaderText(null);
            alert1.setContentText(" Le Coin est supprimé avec succes!");
            alert1.showAndWait();
             UpdateList();}
             
           catch (Exception ex) {

            ex.printStackTrace();
    }
    
}

    @FXML
    private void ajouterCoin(ActionEvent event) throws Exception {
         if((nb_places.getText().equals("")) || (pays.getText().equals(""))||(img.getText().equals(""))||(description_c.getText().equals(""))){
             MediaFX med = new MediaFX("file:///C:/Users/masmoudi/Documents/NetBeansProjects/firstproject-main/b.mp3");
        med.start();
              Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("obligatoire");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire de remplir tous les champs !");
            alert.showAndWait();
             
         }
         else if((Integer.parseInt(nb_places.getText())>20)||(Integer.parseInt(nb_places.getText())<1)){
             MediaFX med = new MediaFX("file:///C:/Users/masmoudi/Documents/NetBeansProjects/firstproject-main/b.mp3");
        med.start();
              Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("champ");
            alert.setHeaderText(null);
            alert.setContentText("Verifier le nombre de places  !");
            alert.showAndWait();
             
         }
         
                    else {
             try {
           Coin c = new Coin();
        c.setNb_places(Integer.parseInt(nb_places.getText()));
        c.setPays(pays.getText());
        c.setDescription_c(description_c.getText());
        c.setImg(img.getText());
        cs.ajouter(c);
        listCoins.getItems().addAll(cs.recuperer());
            Alert alert1 = new Alert(AlertType.INFORMATION);
            alert1.setTitle("Ajout");
            alert1.setHeaderText(null);
            alert1.setContentText("coin ajoutée avec succes !");
            alert1.showAndWait();
             UpdateList();}
             
           catch (Exception ex) {

            ex.printStackTrace();
            
            

        }

        }
     
    }

    @FXML
    private void modifierCoin(ActionEvent event) {try{
            Coin c  = new Coin() ;
            Coin selecteditem = (Coin) listCoins.getSelectionModel().getSelectedItem();
               nb_places.setText(String.valueOf(selecteditem.getNb_places()));
        pays.setText(selecteditem.getPays());
        description_c.setText(selecteditem.getDescription_c());
        img.setText(selecteditem.getImg());
            cs.modifier(selecteditem);
            listCoins.getItems().addAll(cs.recuperer());
          
            
            UpdateList();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Modif");
            alert.setHeaderText(null);
            alert.setContentText("coin modifiée avec succes !");
            alert.showAndWait();        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    @FXML
    private void front(ActionEvent event) {try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("afficherCoins.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();}
    }

    @FXML
    private void plat(ActionEvent event) {try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("PlatFXML.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();}
    }

    @FXML
    private void reservation(ActionEvent event) {try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("ReservationFXML.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();}
        
    }

    

    

    
}
