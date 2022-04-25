/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Depenses;
import entities.Fonction;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
// import javax.swing.text.Document;
import services.DepensesService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author mondh
 */
public class DepensesController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private ListView lid;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;

    /**
     * Initializes the controller class.
     */
       DepensesService fs= new DepensesService();
 ObservableList<Depenses> depenses;
    @FXML
    private ComboBox<String> tftype;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfmontant;
 public void UpdateList() {
        depenses = (ObservableList<Depenses>) fs.recuperer();
        lid.getItems().setAll(depenses);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Salaire",
        "Facture"
    );
    tftype.setItems(options);
                UpdateList();

    }    
    int n = 0;

    @FXML
    private void AjouterDepense(ActionEvent event) {
        if((tftype.getValue().equals("")) || (tfmontant.getText().equals("")) ){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("champs");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire de remplir tous les champs !!!");
            alert.showAndWait();
             
         } 
         else {
             try {
           Depenses d = new Depenses();
        d.setType(tftype.getValue());
        d.setDate(Date.valueOf(tfdate.getValue()));
        d.setMontant(Integer.parseInt(tfmontant.getText()));
        fs.ajouter(d);
        lid.getItems().addAll(fs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Ajout avec succes");
            alert1.setHeaderText(null);
            alert1.setContentText("Depense ajouté avec succes!");
            alert1.showAndWait();
             UpdateList();}
             
           catch (Exception ex) {

            ex.printStackTrace();
            
            

        }

        }

    }

    @FXML
    private void getSelected(MouseEvent event) {
        Depenses selecteditem = (Depenses) lid.getSelectionModel().getSelectedItem();
        tftype.setValue(selecteditem.getType());
        tfmontant.setText(String.valueOf(selecteditem.getMontant()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tfdate.setValue(LocalDate.parse(sdf.format(selecteditem.getDate())));
    }



    @FXML
    private void afficherfonction(ActionEvent event) {
        
         try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("Fonction.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    }

    

    @FXML
    private void ModifierDepense(ActionEvent event) {
        
         if((tftype.getValue().equals("")) || (tfmontant.getText().equals("")) ){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("champs");
            alert.setHeaderText(null);
            alert.setContentText("Il est obligatoire de remplir tous les champs !!!");
            alert.showAndWait();
             
         } 
         else {
        try {

                   
           
            Depenses selecteditem = (Depenses) lid.getSelectionModel().getSelectedItem();
            selecteditem.setType(tftype.getValue());
            selecteditem.setDate(Date.valueOf(tfdate.getValue()));
            selecteditem.setMontant(Integer.parseInt(tfmontant.getText()));
           fs.modifier(selecteditem);
        lid.getItems().addAll(fs.recuperer());
          
            
            UpdateList();
Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ma Modification");
            alert.setHeaderText(null);
            alert.setContentText("Depense bien modifié,vérifiez !");
            alert.showAndWait();        } 
        catch (Exception ex) {
            ex.printStackTrace();

        }
    }
    }

    @FXML
    private void SupprimerDepense(ActionEvent event) {
        try {
            Depenses selecteditem = (Depenses) lid.getSelectionModel().getSelectedItem();
       
        fs.supprimer(selecteditem.getId());
        lid.getItems().addAll(fs.recuperer());
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Suppression avec succes?");
            alert1.setHeaderText(null);
            alert1.setContentText("Fonction supprimé avec succes!");
            alert1.showAndWait();
             UpdateList();}
             
           catch (Exception ex) {

            ex.printStackTrace();
            
            

        }
    }

    @FXML
    private void print(ActionEvent event) {
    /*     depenses = (ObservableList<Depenses>) fs.recuperer();
        lid.getItems().setAll(depenses);
Printer printer = Printer.getDefaultPrinter();
     Node node = new Circle(400, 800, 800);
 PrinterJob job = PrinterJob.createPrinterJob(printer);
 if (job != null) {
    boolean success = job.printPage(lid);
    if (success) {
        job.endJob();
    }
 }

   */ n++;
            Depenses selecteditem = (Depenses) lid.getSelectionModel().getSelectedItem();

     OutputStream file = null;
        try {
            file = new FileOutputStream(new File(selecteditem.getType()+n+".pdf"));
 
            // Create a new Document object
            Document document = new Document();
 
            // You need PdfWriter to generate PDF document
            PdfWriter.getInstance(document, file);
 
            // Opening document for writing PDF
            document.open();
 
            // Writing content
            Image img = Image.getInstance("C:\\Users\\mondh\\Documents\\NetBeansProjects\\pidev\\src\\uploads\\150604_1799_bildbank2028129-CMSTemplate_x0KP7he.jpg");
            img.scaleAbsoluteHeight(200);
            img.scaleAbsoluteWidth(500);
            img.setAlignment(Image.ALIGN_CENTER);
            document.add(img);
            
            document.add(new Paragraph("-------------------------------------------------- Depense ------------------------------------------------------------"));
            
            PdfPTable table = new PdfPTable(3); 
            table.setWidthPercentage(100);
            
            PdfPCell Cell ;
            
            Cell= new PdfPCell (new Phrase  ("Type " , FontFactory.getFont("Comic Sans Ms", 12 )));
            Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(Cell);
            
            Cell= new PdfPCell (new Phrase  ("Date " , FontFactory.getFont("Comic Sans Ms", 12 )));
            Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(Cell);
            
            Cell= new PdfPCell (new Phrase  ("Montant " , FontFactory.getFont("Comic Sans Ms", 12 )));
            Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(Cell);
            
             Cell= new PdfPCell (new Phrase  (selecteditem.getType() , FontFactory.getFont("Comic Sans Ms", 12 )));
            Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(Cell);
            
             Cell= new PdfPCell (new Phrase  (selecteditem.getDate().toString() , FontFactory.getFont("Comic Sans Ms", 12 )));
            Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(Cell);
            
            String s = Float.toString(selecteditem.getMontant());
            Cell= new PdfPCell (new Phrase  ( s , FontFactory.getFont("Comic Sans Ms", 12 )));
            Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(Cell);
            
            document.add(table);
            
 
                   // Add meta data information to PDF file
            document.addCreationDate();
            document.addAuthor("Javarevisited");
            document.addTitle("How to create PDF document in Java");
            document.addCreator("Thanks to iText, writing into PDF is easy");
 
 
            // close the document
            document.close();
 
            System.out.println("Your PDF File is succesfully created");
 
        } catch (Exception e) {
            e.printStackTrace();
 
        } finally {
 
            // closing FileOutputStream
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException io) {/*Failed to close*/
 
            }
 
        }
 
    }

    @FXML
    private void stats(ActionEvent event) {
        
         try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("stats.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    }
    
    
    }
    
    
    

