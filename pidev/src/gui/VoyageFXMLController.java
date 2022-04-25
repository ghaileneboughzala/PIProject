
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Voyage;
import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import services.VoyageService;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class VoyageFXMLController implements Initializable {

    @FXML
    private Button btnAddV;
    @FXML
    private ListView listVoyages;
    @FXML
    private Button btnUpdateV;
    @FXML
    private Button btnDeleteV;
    @FXML
    private TextField tfDestinationV;
    @FXML
    private TextField tfIdUserV;
    @FXML
    private TextField tfIdOffreV;
    @FXML
    private DatePicker tfDateDepV;

    
    VoyageService vs= new VoyageService();
    ObservableList<Voyage> voyages;
    @FXML
    private ComboBox<String> cbIdOffreV;
 
    /**
     * Initializes the controller class.
     */
    
    public void UpdateLV() {
        voyages = (ObservableList<Voyage>) vs.recuperer();
        listVoyages.getItems().setAll(voyages);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UpdateLV();
         final FileChooser fileChooser = new FileChooser();
        final Desktop desktop = Desktop.getDesktop();
//        brImageO.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                tfImageO.clear();
//                fileChooser.setTitle("choisir photo Offre");
//                fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Documents\\NetBeansProjects\\pidev\\src\\uploads"));
//                File file = fileChooser.showOpenDialog(null);
//
//                if (file != null) {
//                    List<File> files = Arrays.asList(file);
//                    tfImageO.setText(file.getName());
//                }
//            }
//        });
            remplirCB();
    }    

    @FXML
    private void addVoyage(ActionEvent event) {
        if((tfIdUserV.getText().equals("")) || (tfDestinationV.getText().equals(""))||(tfDateDepV.toString().equals(""))){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("obligatoire");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire de remplir tous les champs !");
            alert.showAndWait();
    }else {
            
            
                String titOffre =  cbIdOffreV.getValue();
                String sql1="select id from offre where titre='"+titOffre+"'";
                int idO=0;
        
             try {
           Voyage v = new Voyage();
        v.setOffre_id(3);
        v.setId_u(Integer.valueOf(tfIdUserV.getText()));
        v.setDestination(tfDestinationV.getText());
        v.setDate_dep(Date.valueOf(tfDateDepV.getValue()));
        //v.setDone(false);
        
            Statement ste;
                Connection cnx;
                cnx = MyDB.getInstance().getConnection();
                ste = cnx.prepareStatement(sql1);
                ResultSet rs = ste.executeQuery(sql1);
                if(rs.next())
                {
                  idO =rs.getInt("id");
                    
                }
       v.setOffre_id(idO);
        
        
        vs.ajouter(v);
        listVoyages.getItems().addAll(vs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Ajout");
            alert1.setHeaderText(null);
            alert1.setContentText("voyage ajouté avec succes !");
            alert1.showAndWait();
             UpdateLV();}
             
           catch (Exception ex) {

            ex.printStackTrace();
            
            

        }

        }
        
    }

    @FXML
    private void getSelected(MouseEvent event) {
        
        
        Voyage selecteditem = (Voyage) listVoyages.getSelectionModel().getSelectedItem();
        tfIdOffreV.setText(String.valueOf(selecteditem.getOffre_id()));
        tfIdUserV.setText(String.valueOf(selecteditem.getId_u()));
        tfDestinationV.setText(selecteditem.getDestination());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tfDateDepV.setValue(LocalDate.parse(sdf.format(selecteditem.getDate_dep())));
    }

    @FXML
    private void updateVoyage(ActionEvent event) {
        try{
            Voyage v  = new Voyage() ;
            Voyage selecteditem = (Voyage) listVoyages.getSelectionModel().getSelectedItem();
            selecteditem.setOffre_id(Integer.valueOf(tfIdOffreV.getText()));
            selecteditem.setId_u(Integer.valueOf(tfIdUserV.getText()));
            selecteditem.setDestination(tfDestinationV.getText());
            selecteditem.setDate_dep(Date.valueOf(tfDateDepV.getValue()));
            vs.modifier(selecteditem);
            listVoyages.getItems().addAll(vs.recuperer());
          
            
            UpdateLV();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modif");
            alert.setHeaderText(null);
            alert.setContentText("Culture modifiée avec succes !");
            alert.showAndWait();        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    @FXML
    private void deleteVoyage(ActionEvent event) {
         try {
            Voyage selecteditem = (Voyage) listVoyages.getSelectionModel().getSelectedItem();
       
            vs.supprimer(selecteditem.getId());
            listVoyages.getItems().addAll(vs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Suppression avec succes");
            alert1.setHeaderText(null);
            alert1.setContentText("voyage supprimé avec succes!");
            alert1.showAndWait();
             UpdateLV();}
             
           catch (Exception ex) {

            ex.printStackTrace();
    }
    }
    
    
    
public void remplirCB() {
          
        try {
            String sql="select titre from offre ";
            
            
            List<String> nm =new ArrayList<String>();
            PreparedStatement ste;
            Statement st;
            Connection cnx;
            cnx = MyDB.getInstance().getConnection();
            st = cnx.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                
                nm.add(rs.getString("titre"));
                   cbIdOffreV.setItems(FXCollections.observableArrayList(nm));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VoyageFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }


    
}