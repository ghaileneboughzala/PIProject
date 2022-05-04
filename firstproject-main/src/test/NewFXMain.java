/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.stage.Stage;

/**
 *
 * @author mondh
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try{ Parent  root;
        root= FXMLLoader.load(getClass().getResource("../gui/CoinFXML.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Interface back coin");
        primaryStage.setScene(scene);
Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ma Connexion");
            alert.setHeaderText(null);
            alert.setContentText("La connexion à la base de données est établie!");
            alert.showAndWait();        
            primaryStage.show();
     
    }
    catch(IOException ex)
    {
        ex.printStackTrace();
    }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
