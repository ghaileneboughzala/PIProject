/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Role;
import entites.User;
import gestionuserpidev.FXMain;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author ghail
 */
public class FXMLloginController implements Initializable {
    
    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField pfpassword;
    ServiceUser su=new ServiceUser();
    public static int idglobal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public String controleDeSaisie(){
        String erreur="";
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +"[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if(tfemail.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Email\n";
        }
        if(pfpassword.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Mot De Passe\n";
        }
        if (!pattern.matcher(tfemail.getText().trim()).matches()) {
            erreur+="-Veuillez insérer un email valide\n";
        } 
        return erreur;
        
    }
    @FXML
    private void login(ActionEvent event) {
        if(controleDeSaisie().length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur Login");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            
            User u=su.getUserByEmail(tfemail.getText());
            
            
            if(BCrypt.checkpw( pfpassword.getText(),u.getPassword())){
                if(u.getRoles()==Role.ROLE_USER){
                    idglobal=u.getId();
                    Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    closestage.close();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLfront.fxml"));
                        Scene scene = new Scene(root);
                        Stage primaryStage=new Stage();
                        primaryStage.setTitle("Signup");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    idglobal=u.getId();
                    Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    closestage.close();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLadmin.fxml"));
                        Scene scene = new Scene(root);
                        Stage primaryStage=new Stage();
                        primaryStage.setTitle("Signup");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur Login");
                alert.setContentText("invalid Email or Password");
                alert.showAndWait();
            }
        }
        
    }

    @FXML
    private void signup(ActionEvent event) {
        Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
        closestage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLsignup.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Signup");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
