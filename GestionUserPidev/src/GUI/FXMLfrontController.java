/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.CarteFidelite;
import entites.User;
import gestionuserpidev.FXMain;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import services.MyListener;
import services.ServiceCarteFidelite;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author ghail
 */
public class FXMLfrontController implements Initializable {

    @FXML
    private GridPane grid;
    ServiceUser su=new ServiceUser();
    ServiceCarteFidelite sc=new ServiceCarteFidelite();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refresh();
    }    

    @FXML
    private void profile(ActionEvent event) {
        Stage closestage=(Stage)((Node)event.getSource()).getScene().getWindow();
        closestage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXMLprofile.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage=new Stage();
            primaryStage.setTitle("Signup");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
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
     MyListener myListener;
    public void refresh(){
        grid.getChildren().clear();
        List<User> users =new ArrayList<>();
        List<CarteFidelite> cards=sc.sortByPoints();
        if(cards.size()>=3){
            users.add(su.getUserByCard(cards.get(cards.size()-1).getId()));
            users.add(su.getUserByCard(cards.get(cards.size()-2).getId()));
            users.add(su.getUserByCard(cards.get(cards.size()-3).getId()));
        }
        else if(cards.size()==2){
            users.add(su.getUserByCard(cards.get(cards.size()-1).getId()));
            users.add(su.getUserByCard(cards.get(cards.size()-2).getId()));
        }
        else{
            users.add(su.getUserByCard(cards.get(cards.size()-1).getId()));
        }
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < users.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/FXMLuser.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                FXMLuserController itemController = fxmlLoader.getController();
                itemController.lrole.setVisible(false);
                itemController.letat.setVisible(false);
                itemController.lcin.setVisible(false);
                itemController.lemail.setVisible(false);
                itemController.trole.setVisible(false);
                itemController.tetat.setVisible(false);
                itemController.tcin.setVisible(false);
                itemController.temail.setVisible(false);
                //itemController.image.setVisible(false);
                System.out.println(users.get(i));
                itemController.setData(users.get(i),myListener);

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
    
}
