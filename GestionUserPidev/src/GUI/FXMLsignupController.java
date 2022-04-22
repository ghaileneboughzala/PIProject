/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Role;
import entites.User;
import gestionuserpidev.FXMain;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author ghail
 */
public class FXMLsignupController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfcin;
    @FXML
    private PasswordField pfpassword;
    ServiceUser su=new ServiceUser();
    @FXML
    private ImageView image;
    @FXML
    private Label tfimgurl;
    @FXML
    private AnchorPane anchore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfimgurl.setVisible(false);
    }    
    @FXML
    private void uploadimg(ActionEvent event) {
        FileChooser open = new FileChooser();
        Stage stage = (Stage) anchore.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if (file != null) {
            String path = file.getAbsolutePath();
            path = path.replace("\\", "\\\\\\\\\\");
            tfimgurl.setText(path);
            Image img = new Image(file.toURI().toString());
            image.setImage(img);
        } else {
            System.out.println("NO DATA EXIST!");
        }
    }
    public String controleDeSaisie(){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +"[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        String erreur="";
        if(tfcin.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ CIN\n";
        }
        if(tfemail.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Email\n";
        }
        if(tfimgurl.getText().trim().isEmpty()){
            erreur+="-Veuillez insérer une image\n";
        }
        if(tfnom.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Nom\n";
        }
        if(tfprenom.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Prenom\n";
        }
        if(pfpassword.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Mot De Passe\n";
        }
        if(!tfcin.getText().trim().matches("[0-9]+") || tfcin.getText().trim().length()!=8 ){
            erreur+="Veuillez insérer un numero de CIN valide";
        }
        if (!pattern.matcher(tfemail.getText().trim()).matches()) {
            erreur+="-Veuillez insérer un email valide\n";
        } 
        return erreur;
        
    }

    @FXML
    private void signup(ActionEvent event) {
        User u=new User(tfemail.getText(), Role.ROLE_USER,
                pfpassword.getText(), tfcin.getText(),
                tfnom.getText(), tfprenom.getText(),
                tfimgurl.getText(), true, "1234");
        if(controleDeSaisie().length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur Signup");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            try{
                su.ajouter(u);
                throw new SQLIntegrityConstraintViolationException();
                } catch(SQLIntegrityConstraintViolationException  e){
                System.out.println(e.getMessage());
            }
                Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
                closestage.close();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLlogin.fxml"));
                    Scene scene = new Scene(root);
                    Stage primaryStage=new Stage();
                    primaryStage.setTitle("Login");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) {
                    Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
                } 
                  
            
            
            
        }
        
    }

    @FXML
    private void login(ActionEvent event) {
        Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
        closestage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLlogin.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
}
