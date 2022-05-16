/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commande;
import entities.Livraison;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import services.CommandeService;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author allan
 */
public class CommandeFXMLController implements Initializable {

    
    ObservableList list;
    @FXML
    private TextField tfnumc;
    @FXML
    private TextField tfpayement;
    @FXML
    private DatePicker tfdatec;
    @FXML
    private TextField tflivraisons_id;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnajouter;
    @FXML
    private TableView<Commande> tvCommandes;
    @FXML
    private TableColumn<Commande, Integer> colnum_c;
    @FXML
    private TableColumn<Commande, Float> colpayement;
    @FXML
    private TableColumn<Commande, Integer> colidlivraison;
    @FXML
    private TableColumn<Commande, Date> coldate_c;
    @FXML
    private Button btnafficher;
    CommandeService cs=new CommandeService();
    @FXML
    private Button btncommande;
    @FXML
    private Button btnlivraison;
    @FXML
    private Button btnajouter12;
    @FXML
    private TextField search;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    /*private void getListCommandes() {
        CommandeService cs = new CommandeService();
        ObservableList<Commande> commandes;
        
        
        colidlivraison.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("livraisons_id"));
        colnum_c.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("num_c"));
        coldate_c.setCellValueFactory(new PropertyValueFactory<Commande,String>("date_c"));
        colpayement.setCellValueFactory(new PropertyValueFactory<Commande,Float>("payement"));
        
        tvCommandes.setItems((ObservableList<Commande>)cs.recuperer());
    }*/

    @FXML
    private void Supprimer(ActionEvent event) {
        final Commande selectedItem = tvCommandes.getSelectionModel().getSelectedItem();
        Commande c = cs.GetById(selectedItem.getId());
        cs.supprimer(c.getId());
        list.remove(selectedItem);
        tvCommandes.setItems(FXCollections.observableArrayList(cs.recuperer()));
        tvCommandes.refresh();
    }
    
    @FXML
    private void Modifier(ActionEvent event) {
        
        final Commande selectedItem = tvCommandes.getSelectionModel().getSelectedItem();
        Commande c = cs.GetById(selectedItem.getId());
        c.setLivraisons_id(Integer.parseInt(tflivraisons_id.getText()));
        c.setNum_c(Integer.parseInt(tfnumc.getText()));
        c.setDate_c(java.sql.Date.valueOf(tfdatec.getValue()));
        c.setPayement(Float.parseFloat(tfpayement.getText()));
        cs.modifier(c);
        tvCommandes.setItems(FXCollections.observableArrayList(cs.recuperer()));
        tvCommandes.refresh();

    }

    @FXML
    private void Ajouter(ActionEvent event) {
        Commande c = new Commande();
        c.setLivraisons_id(Integer.parseInt(tflivraisons_id.getText()));
        c.setNum_c(Integer.parseInt(tfnumc.getText()));
        c.setDate_c(java.sql.Date.valueOf(tfdatec.getValue()));
        c.setPayement(Float.parseFloat(tfpayement.getText()));
        CommandeService cs = new CommandeService();
        cs.ajouter(c);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Commande ajoutée");
        a.show();
    }

    @FXML
    private void Afficher(ActionEvent event) {
        CommandeService cs = new CommandeService();
        List<Commande> commande = cs.recuperer();
        list = FXCollections.observableList(commande);
        tvCommandes.setItems(list);
        colidlivraison.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("livraisons_id"));
        colnum_c.setCellValueFactory(new PropertyValueFactory<Commande,Integer>("num_c"));
        coldate_c.setCellValueFactory(new PropertyValueFactory<Commande,Date>("date_c"));
        colpayement.setCellValueFactory(new PropertyValueFactory<Commande,Float>("payement"));
        
        /* FilteredList filteredData = new FilteredList<>(list,b -> true);
//		
//		// 2. Set the filter Predicate whenever the filter changes.
	search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(test -> {
//				// If filter text is empty, display all persons.
//								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
//				
//				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
                                String upperCase=newValue.toUpperCase();
				
				if (String.valueOf(test).contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (String.valueOf(test).contains(upperCase)) {
					return true; // Filter matches last name.
				}
				 
                                 
                               
                                
				     else  
				    	 return false; // Does not match.
			});
		});
//		
//		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Livraison> sortedData = new SortedList<>(filteredData);
//		
//		// 4. Bind the SortedList comparator to the TableView comparator.
//		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tvCommandes.comparatorProperty());
//		
//		// 5. Add sorted (and filtered) data to the table.
		tvCommandes.setItems(sortedData);*/
    }

    @FXML
    private void Commande(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../gui/CommandeFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Livraison(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../gui/LivraisonFXML.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
}
