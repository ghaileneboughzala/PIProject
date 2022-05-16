/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.CarteFidelite;
import entites.Role;
import entites.User;
import gestionuserpidev.FXMain;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.MyListener;
import services.ServiceUser;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.util.Duration;
import services.ServiceCarteFidelite;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author ghail
 */
public class FXMLadminController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfcin;
    @FXML
    private Label tfimgurl;
    @FXML
    private RadioButton rbadmin;
    @FXML
    private ToggleGroup g1;
    @FXML
    private RadioButton rbuser;
    @FXML
    private TextField tfrecherche;
    @FXML
    private PasswordField pfpass;
    
    @FXML
    private Label idgetter;
    @FXML
    private ImageView image;
    @FXML
    private AnchorPane anchore;
    @FXML
    private TextField tfpts;
    ServiceUser su=new ServiceUser();
    ServiceCarteFidelite sc=new ServiceCarteFidelite();
    @FXML
    private Button stat;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rechercheavance();
        tfimgurl.setVisible(false);
       
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
            erreur+="-Veuillez insérer votre photo\n";
        }
        if(tfnom.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Nom\n";
        }
        if(tfprenom.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Prenom\n";
        }
        if(pfpass.getText().trim().isEmpty()){
            erreur+="-Veuillez remplir le champ Mot De Passe\n";
        }
        if(!tfcin.getText().trim().matches("[0-9]+") &&tfcin.getText().trim().length()!=8 ){
            erreur+="-Veuillez insérer un numéro de carte correct\n";
        }
        if (!pattern.matcher(tfemail.getText().trim()).matches()) {
            erreur+="-Veuillez insérer un email correct\n";
        } 
        return erreur;
        
    }
    public void fillData(User u){
        tfcin.setText(u.getCin());
        tfemail.setText(u.getEmail());
        tfimgurl.setText(u.getPhoto());
        tfnom.setText(u.getNom());
        tfprenom.setText(u.getPrenom());
        if(u.getRoles().equals(Role.ROLE_ADMIN)){
            rbadmin.setSelected(true);
            rbuser.setSelected(false);
        }
        else{
            rbadmin.setSelected(false);
            rbuser.setSelected(true);
        }
        File file =new File(u.getPhoto());
        Image img = new Image(file.toURI().toString());
        image.setImage(img);
    }
    @FXML
    private void ajouter(ActionEvent event) {
        
            User u=new User(tfemail.getText(), Role.ROLE_USER,
                pfpass.getText(), tfcin.getText(),
                tfnom.getText(), tfprenom.getText(),
                tfimgurl.getText(), true, "1234");
            if(rbadmin.isSelected()){
                u.setRoles(Role.ROLE_ADMIN);
            }
            else{
                u.setRoles(Role.ROLE_USER);
            }
        if(controleDeSaisie().length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout user");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            try{
                su.ajouter(u);
              
                
                
                CarteFidelite c=sc.getCarteFideliteById(su.afficher().get(su.afficher().size()-1).getCarte_id());
                
                c.setNbpts(Integer.parseInt(tfpts.getText()));
                sc.modifier(c, c.getId());
                refresh(su.afficher());
                TrayNotification tray = new TrayNotification();
            
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("Succés d'ajout de l'utilisateur");
                tray.setMessage("L'utilisateur a été ajouté avec succès");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(1000));
                throw new SQLIntegrityConstraintViolationException();  
            } catch(SQLIntegrityConstraintViolationException  e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ajout utilisateur");
                alert.setContentText("Utilisateur ajouté avec succés");
                alert.showAndWait();
            }
        }
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        User u=new User(tfemail.getText(), Role.ROLE_USER,
                pfpass.getText(), tfcin.getText(),
                tfnom.getText(), tfprenom.getText(),
                tfimgurl.getText(), true, "1234");
        if(rbadmin.isSelected()){
            u.setRoles(Role.ROLE_ADMIN);
        }
        else{
                u.setRoles(Role.ROLE_USER);
        }
        User user=su.getUserById(Integer.parseInt(idgetter.getText()));
        u.setCarte_id(user.getCarte_id());
        if(controleDeSaisie().length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ajout user");
            alert.setContentText(controleDeSaisie());
            alert.showAndWait();
        }
        else{
            Stage stage=(Stage) anchore.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.CONFIRMATION;
            Alert alert=new Alert(type,"");
            alert.getDialogPane().setContentText("Voulez vous vraiment modifier cet utilisateur?");
            alert.getDialogPane().setHeaderText("Confirmer la modification");
            Optional<ButtonType> result =alert.showAndWait();
            if(result.get()==ButtonType.OK)
            {
                su.modifier(u, Integer.parseInt(idgetter.getText()));
                CarteFidelite c=sc.getCarteFideliteById(u.getCarte_id());
                c.setNbpts(Integer.parseInt(tfpts.getText()));
                sc.modifier(c, c.getId());
                refresh(su.afficher());
                //**********NOTIFICATION******************
                TrayNotification tray = new TrayNotification();
                AnimationType typea = AnimationType.POPUP;
                tray.setAnimationType(typea);
                tray.setTitle("Modification avec succès");
                tray.setMessage("L'utilisateur a été modifié avec succès");
                tray.setNotificationType(NotificationType.INFORMATION);
                tray.showAndDismiss(Duration.millis(1000));
                //**********NOTIFICATION******************
            }
            
            
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
            Stage stage=(Stage) anchore.getScene().getWindow();
            Alert.AlertType type=Alert.AlertType.CONFIRMATION;
            Alert alert=new Alert(type,"");
            alert.getDialogPane().setContentText("Voulez vous vraiment supprimer cet utilisateur?");
            alert.getDialogPane().setHeaderText("Confirmer la suppression");
            Optional<ButtonType> result =alert.showAndWait();
            if(result.get()==ButtonType.OK)
            {
                su.supprimer(Integer.parseInt(idgetter.getText()));
                refresh(su.afficher());
                TrayNotification tray = new TrayNotification();
                AnimationType typea = AnimationType.POPUP;
                tray.setAnimationType(typea);
                tray.setTitle("Suppression avec succès");
                tray.setMessage("L'utilisateur a été supprimé avec succès");
                tray.setNotificationType(NotificationType.WARNING);
                tray.showAndDismiss(Duration.millis(1000));
                   
            }
        
    }

    @FXML
    private void uploadimg(ActionEvent event) {
        FileChooser open = new FileChooser();
        Stage stage = (Stage) anchore.getScene().getWindow();
        File file = open.showOpenDialog(stage);
        if (file != null) {
            String path = file.getAbsolutePath();
            path = path.replace("\\", "\\\\\\\\\\\\\\\\\\\\\\");
            tfimgurl.setText(path);
            Image img = new Image(file.toURI().toString());
            image.setImage(img);
        } else {
            System.out.println("NO DATA EXIST!");
        }
    }
    MyListener myListener;
    public void selectedUser(User u){
        idgetter.setText(String.valueOf(u.getId()));
        fillData(u);
        System.out.println(idgetter.getText());
    }
    public void refresh(List<User> ls){
        grid.getChildren().clear();
       
        if(ls.size() > 0){
            selectedUser(ls.get(0));
            myListener = new MyListener() {
                @Override
                public void onclickListener(User u) {
                    selectedUser(u);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < ls.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/FXMLuser.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                FXMLuserController itemController = fxmlLoader.getController();
                itemController.setData(ls.get(i),myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    @FXML
    private void logout(ActionEvent event) {
        Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
        closestage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLlogin.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Signup");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void rechercheavance(){
        refresh(su.afficher());
        ObservableList<User> users=FXCollections.observableArrayList(su.afficher());
        
        FilteredList<User> filterddata=new FilteredList<>(users,b->true);
        tfrecherche.textProperty().addListener(
                (observable,oldValue,newValue)->{
                    filterddata.setPredicate(
                        u->{
                            if(u.getNom().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getCin().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getEmail().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getPrenom().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            else if(u.getRoles().toString().toLowerCase().indexOf(newValue.toLowerCase())!=-1){
                                return true;
                            }
                            
                            else{
                                return false;
                            }
                            
                        }
                            
                    );
                    refresh(filterddata);
                }
                
        );
        
    }

    @FXML
    private void block(ActionEvent event) {
        User u=su.getUserById(Integer.parseInt(idgetter.getText()));
        int x;
        if(u.isIs_verified()){
            x=0;
        }
        else{
            x=1;
        }
        su.modifierBloc(x, Integer.parseInt(idgetter.getText()));
        refresh(su.afficher());
    }

    @FXML
    private void activer(ActionEvent event) {
        User u=su.getUserById(Integer.parseInt(idgetter.getText()));
        CarteFidelite c=sc.getCarteFideliteById(u.getCarte_id());
        Date date = new Date(LocalDate.now().getYear()-1900,LocalDate.now().getMonthValue()-1, LocalDate.now().getDayOfMonth());
        if(c.getDateexpiration().compareTo(date)>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation de la carte");
            alert.setContentText("Pas besoin de l'activer, cette carte est déja valide.");
            alert.showAndWait();
        }
        else{
            LocalDate ld=LocalDateTime.now().plusYears(3).toLocalDate();
            Date d1=Date.valueOf(ld);
            c.setDateexpiration(d1);
            sc.modifier(c, c.getId());
        }
        refresh(su.afficher());
    }

    @FXML
    private void stat(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLstat.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Stat");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
