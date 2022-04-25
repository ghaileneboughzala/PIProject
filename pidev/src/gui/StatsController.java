/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Depenses;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import services.DepensesService;

/**
 * FXML Controller class
 *
 * @author mondh
 */
public class StatsController implements Initializable {
 @FXML
 private PieChart pieChart;
 DepensesService fs= new DepensesService();
  ObservableList<Depenses> depenses;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Integer sal = fs.salaire();
        Integer fac = fs.facture();
        ObservableList<PieChart.Data> pieChartData =
                
                FXCollections.observableArrayList(
                        new PieChart.Data("Factures", fac),
                        new PieChart.Data("Salaires", sal));

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " amount: ", data.pieValueProperty()
                        )
                )
        );

        pieChart.getData().addAll(pieChartData);
    }    

    @FXML
    private void back(ActionEvent event) {
         try {
            Stage nouveauStage;
            Parent root = FXMLLoader.load(getClass().getResource("depenses.fxml"));
            nouveauStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            nouveauStage.setScene(scene);

        } catch (IOException ex) {
            ex.printStackTrace();
        } 
    }
    
}
