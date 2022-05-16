/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import utils.Myconnexion;
import javafx.scene.control.Tooltip;

/**
 * FXML Controller class
 *
 * @author ghail
 */
public class FXMLstatController implements Initializable {

    @FXML
    private PieChart piechart;
     ObservableList<PieChart.Data>data=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        stat();
    }    
    private void stat()
    {
          
           Connection cnx = Myconnexion.getInstance().getCnx();
      try {
           
          String query = "select count(*) As value,is_verified As block from user group by is_verified" ;
       
         PreparedStatement PreparedStatement = cnx.prepareStatement(query);
          ResultSet rs = PreparedStatement.executeQuery();
             rs.next();
             data.add(new PieChart.Data("Bloqués",rs.getInt("value"))) ;
             rs.next();
             data.add(new PieChart.Data("Non bloqués",rs.getInt("value"))) ;
                 
        } catch (SQLException ex) {
            Logger.getLogger(FXMLstatController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        piechart.setTitle("Statistiques de bloquage des utilisateurs ");
        piechart.setLegendSide(Side.LEFT);
        
        piechart.setData(data);
       
    
    }
}
