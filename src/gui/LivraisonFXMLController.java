/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Livraison;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javafx.event.EventHandler;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.LivraisonService;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author allan
 */
public class LivraisonFXMLController implements Initializable {

    
    @FXML
    private TextField tfadresse;
    @FXML
    private DatePicker tfdate_h_l;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField tfnum_tel;
    @FXML
    private TextField tfetat;
    @FXML
    private TableView<Livraison> tvLivraison;
    @FXML
    private TableColumn<Livraison, Integer> colpersonnel_id;
    @FXML
    private TableColumn<Livraison, Date> coldate_h_l;
    @FXML
    private TableColumn<Livraison, String> coladresse;
    @FXML
    private TableColumn<Livraison, String> colnum_tel;
    @FXML
    private TableColumn<Livraison, String> coletat;
    @FXML
    private Button btnafficher;

    ObservableList list;
    @FXML
    private TextField tfp;
    
    LivraisonService ls = new LivraisonService();
    @FXML
    private Button btnimprimer;
    @FXML
    private TextField search;
    @FXML
    private Button btncommande;
    @FXML
    private Button btnlivraison;
    @FXML
    private Button btnajouter12;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        
        // TODO
    }    
    
    /* private void getListLivraisons() {
        LivraisonService ls = new LivraisonService();
        ObservableList<Livraison> livraison;
        colpersonnel_id.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("personnel_id"));
        coldate_h_l.setCellValueFactory(new PropertyValueFactory<Livraison,String>("date_h_l"));
        coladresse.setCellValueFactory(new PropertyValueFactory<Livraison,String>("adresse"));
        colnum_tel.setCellValueFactory(new PropertyValueFactory<Livraison,String>("num_tel"));
        coletat.setCellValueFactory(new PropertyValueFactory<Livraison,String>("etat"));
        
        tvLivraison.setItems((ObservableList<Livraison>) ls.recuperer());
    }
    
   @FXML
    private void Ajouter(ActionEvent event) {
        livraison.add(new Livraison(1, "22-05-2016", "Ezzahra", "23543607", "Livré"));
    }*/

    @FXML
    private void Supprimer(ActionEvent event) {
        final Livraison selectedItem = tvLivraison.getSelectionModel().getSelectedItem();
        Livraison l= ls.GetById(selectedItem.getId());
        ls.supprimer(l.getId());
        list.remove(selectedItem);
        tvLivraison.setItems(FXCollections.observableArrayList(ls.recuperer()));
        tvLivraison.refresh();
    }

    @FXML
    private void Modifier(ActionEvent event) {
        final Livraison selectedItem = tvLivraison.getSelectionModel().getSelectedItem();
        Livraison l = ls.GetById(selectedItem.getId());
        l.setPersonnel_id(Integer.parseInt(tfp.getText()));
        l.setAdresse(tfadresse.getText());
        l.setDate_h_l(Date.valueOf(tfdate_h_l.getValue()));
        l.setNum_tel(tfnum_tel.getText());
        l.setEtat(tfetat.getText());
        ls.modifier(l);
        tvLivraison.setItems(FXCollections.observableArrayList(ls.recuperer()));
        tvLivraison.refresh();
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        Livraison l = new Livraison();
        l.setPersonnel_id(Integer.parseInt(tfp.getText()));
        l.setAdresse(tfadresse.getText());
        l.setDate_h_l(Date.valueOf(tfdate_h_l.getValue()));
        l.setNum_tel(tfnum_tel.getText());
        l.setEtat(tfetat.getText());
        LivraisonService ls = new LivraisonService();
        ls.ajouter(l);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Livraison ajoutée");
        a.show();
    }

    @FXML
    private void Afficher(ActionEvent event) {
        LivraisonService ls = new LivraisonService();
        List<Livraison> livraison = ls.recuperer();
        list = FXCollections.observableList(livraison);
        tvLivraison.setItems(list);
        colpersonnel_id.setCellValueFactory(new PropertyValueFactory<Livraison,Integer>("personnel_id"));
        coldate_h_l.setCellValueFactory(new PropertyValueFactory<Livraison, Date>("date_h_l"));
        coladresse.setCellValueFactory(new PropertyValueFactory<Livraison,String>("adresse"));
        colnum_tel.setCellValueFactory(new PropertyValueFactory<Livraison,String>("num_tel"));
        coletat.setCellValueFactory(new PropertyValueFactory<Livraison,String>("etat"));
        
        FilteredList filteredData = new FilteredList<>(list,b -> true);
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
		sortedData.comparatorProperty().bind(tvLivraison.comparatorProperty());
//		
//		// 5. Add sorted (and filtered) data to the table.
		tvLivraison.setItems(sortedData);
    }

    @FXML
    private void Imprimer(ActionEvent event) {
        btnimprimer.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					Printer printer = Printer.getDefaultPrinter();
					PrinterJob job = PrinterJob.createPrinterJob();
					PageLayout pageLayout = job.getPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE,
							Printer.MarginType.DEFAULT);
					 Stage dialogStage = new Stage(StageStyle.DECORATED);
					job.getJobSettings().setPageLayout(pageLayout);
                                        if (job != null) {
						boolean successPrintDialog = job.showPrintDialog(dialogStage.getOwner());
						if (successPrintDialog) {
							boolean success = job.printPage(pageLayout, tvLivraison);
							if (success) {
								job.endJob();
								Stage stage = (Stage) btnimprimer.getScene().getWindow();
								stage.close();
                                                                				}
							
						}
					}
				}
                                });
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

    private void Recherche(ActionEvent event) {
        FilteredList filteredData = new FilteredList<>(list,b -> true);
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
		sortedData.comparatorProperty().bind(tvLivraison.comparatorProperty());
//		
//		// 5. Add sorted (and filtered) data to the table.
		tvLivraison.setItems(sortedData);
    }
    }

    

   
    

    
    

