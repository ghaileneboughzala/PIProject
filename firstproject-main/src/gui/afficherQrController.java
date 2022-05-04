/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.ReservationService;

/**
 * FXML Controller class
 *
 * @author masmoudi
 */
public class afficherQrController implements Initializable {

    @FXML 
private ImageView i;

ReservationService fd = new ReservationService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void initData(int id) {
        String s = fd.findQr(id);
        Image im = new Image(s);
        i.setImage(im);
        
       
    }  
    
}
