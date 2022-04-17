/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.CarteFidelite;
import entites.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import services.MyListener;
import services.ServiceCarteFidelite;

/**
 * FXML Controller class
 *
 * @author ghail
 */
public class FXMLuserController implements Initializable {

    @FXML
    public ImageView image;
    @FXML
    public Label lnom;
    @FXML
    public Label lemail;
    @FXML
    public Label lrole;
    @FXML
    public Label lprenom;
    @FXML
    public Label lcin;
    @FXML
    public Label letat;
    private User u;
    private MyListener myListener;
    @FXML
    private Label cartenum;
    @FXML
    private Label cartepts;
    @FXML
    private Label carteperiode;
    @FXML
    private Label cartedateexp;
    ServiceCarteFidelite sc=new ServiceCarteFidelite();
    @FXML
    public Text tnom;
    @FXML
    public Text tprenom;
    @FXML
    public Text temail;
    @FXML
    public Text tcin;
    @FXML
    public Text trole;
    @FXML
    public Text tetat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setData(User u,MyListener myListener){
        this.u=u;
        this.myListener=myListener;
        lcin.setText(u.getCin());
        lemail.setText(u.getEmail());
        lnom.setText(u.getNom());
        lprenom.setText(u.getPrenom());
        lrole.setText(u.getRoles().toString());
        if(u.isIs_verified()){
            letat.setText("Verifier");
        }
        else{
            letat.setText("Non Verifier");
        }
        File file =new File(u.getPhoto());
        Image img = new Image(file.toURI().toString());
        image.setImage(img);
        CarteFidelite c=sc.getCarteFideliteById(u.getCarte_id());
        cartedateexp.setText(c.getDateexpiration().toString());
        cartenum.setText(c.getNum());
        carteperiode.setText(c.getPeriodevalidation());
        cartepts.setText(String.valueOf(c.getNbpts()));
    }
    @FXML
    private void selectionuser(MouseEvent event) {
        myListener.onclickListener(u);
    }
    
}
