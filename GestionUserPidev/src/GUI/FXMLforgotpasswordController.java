/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.User;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServiceUser;
import utils.Mailapi;

/**
 * FXML Controller class
 *
 * @author ghail
 */
public class FXMLforgotpasswordController implements Initializable {

    @FXML
    private TextField tfemail_tel;
    @FXML
    private PasswordField pfnew_password;
    @FXML
    private PasswordField pfconfirm;
    @FXML
    private Button btupdate;
    @FXML
    private Button btsearch;
    @FXML
    private TextField tfverificationcode;
    ServiceUser su=new ServiceUser();
    int n;
    User u =new User();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btupdate.setVisible(false);
        pfconfirm.setVisible(false);
        pfnew_password.setVisible(false);
        tfverificationcode.setVisible(false);
        Random rand =new Random();
        n=rand.nextInt(99999);
    }    

    @FXML
    private void update(ActionEvent event) {
        if(tfverificationcode.getText().equals(String.valueOf(n)) && pfconfirm.getText().equals(pfnew_password.getText())){
            u.setPassword(pfnew_password.getText());
            su.modifier(u,u.getId());
            try {
                Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();

                stageclose.close();
                Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXMLlogin.fxml"));
                Stage stage =new Stage();

                Scene scene = new Scene(root);

                stage.setTitle("signup");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLforgotpasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void search(ActionEvent event) {
        u=su.getUserByEmail(tfemail_tel.getText());
        if(u!=null){
            
            Mailapi.send("restaurantly9@gmail.com", "restaurantly123", u.getEmail(), "Mot de passe oublié ?", "Cher "+u.getPrenom()+" "+u.getNom()+", \n Vous venez d'essayer de vous accéder à votre compte avec l'adresse suivante : "+u.getEmail()+"\n Veuillez entrer le code de vérification suivant pour pouvoir modifier votre mot de passe : "+n);
            tfemail_tel.setVisible(false);
            btsearch.setVisible(false);
            btupdate.setVisible(true);
            pfconfirm.setVisible(true);
            pfnew_password.setVisible(true);
            tfverificationcode.setVisible(true);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid email");
            alert.setContentText("Email doesn't exist");
            alert.showAndWait();
        }
    }
    
    
}
